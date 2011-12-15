package br.zero.controlefinanceiro.actions;

import java.util.ArrayList;
import java.util.List;

import br.zero.controlefinanceiro.commandlineparser.ExtratoAnalyseSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.controlefinanceiro.model.ExtratoBalanceLine;
import br.zero.controlefinanceiro.model.ExtratoLine;
import br.zero.controlefinanceiro.model.ExtratoLineParser;
import br.zero.controlefinanceiro.model.ExtratoTransactionLine;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;
import br.zero.controlefinanceiro.model.extrato.ExtratoLancamento;
import br.zero.controlefinanceiro.model.extrato.ExtratoLancamentoDAO;
import br.zero.controlefinanceiro.utils.ControleFinanceiroException;
import br.zero.controlefinanceiro.utils.ControleFinanceiroFormatters;
import br.zero.controlefinanceiro.utils.ExtratoLineParserException;
import br.zero.textgrid.TextGrid;
import br.zero.textgrid.TextGridColumnAlignment;
import br.zero.textgrid.TextGridException;
import br.zero.textgrid.TextGridFormattedColumn;
import br.zero.tinycontroller.Action;

public class ExtratoAnalyseAction implements Action {

	public class ExtratoLineAnalyseResult {

		private Character tipo;
		private String original;
		private Conta conta;
		private Lancamento lancamento;

		public Character getTipo() {
			return tipo;
		}

		public void setTipo(Character tipo) {
			this.tipo = tipo;
		}

		public String getOriginal() {
			return original;
		}

		public void setOriginal(String original) {
			this.original = original;
		}

		public Conta getConta() {
			return conta;
		}

		public void setConta(Conta conta) {
			this.conta = conta;
		}

		public Lancamento getLancamento() {
			return lancamento;
		}

		public void setLancamento(Lancamento lancamento) {
			this.lancamento = lancamento;
		}

	}

	private LancamentoDAO lancamentoDAO;
	private Conta banco;
	private ExtratoAnalyseSwitches switches;

	private class ExtratoAnalyseException extends ControleFinanceiroException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1702349740543718569L;

		public ExtratoAnalyseException(String message) {
			super("extrato analyse: " + message);
		}

		public ExtratoAnalyseException(Exception e) {
			super(e);
		}

	}

	@Override
	public void run(Object param) throws ExtratoAnalyseException {
		switches = checkParamValid(param);

		banco = getConta(switches.getNomeBanco());

		List<Lancamento> lancamentoSemExtratoList = getLancamentoSemExtratoList();

		List<ExtratoLancamento> extratoLancamentoOrfao = getExtratoLancamentoOrfao(banco);

		if (extratoLancamentoOrfao.size() == 0) {
			System.out.println();
			System.out.println("Nenhum item de extrato órfão encontrado para o banco \"" + banco.getNome() + "\".");

			return;
		}

		ExtratoLineParser parser = banco.getParser();

		if (parser == null) {
			throw new ExtratoAnalyseException("Nenhum parser registrado para o banco: \"" + banco.getNome() + "\".");
		}

		try {
			makeSync(lancamentoSemExtratoList, extratoLancamentoOrfao, parser);
		} catch (Exception e) {
			throw new ExtratoAnalyseException(e);
		}
	}

	private void makeSync(List<Lancamento> lancamentoSemExtratoList, List<ExtratoLancamento> extratoLancamentoOrfao, ExtratoLineParser parser) throws ExtratoLineParserException, TextGridException {
		TextGrid grid = createGrid();

		List<ExtratoLineAnalyseResult> statuses = new ArrayList<ExtratoLineAnalyseResult>();

		ContaDAO contaDAO = new ContaDAO();

		for (ExtratoLancamento linhaExtrato : extratoLancamentoOrfao) {

			parser.parse(linhaExtrato.getOriginal());

			ExtratoLine line = parser.getLine();

			ExtratoLineAnalyseResult analyseResult;

			if (line instanceof ExtratoBalanceLine) {
				analyseResult = syncBalanceLine();
			} else if (line instanceof ExtratoTransactionLine) {
				analyseResult = syncTransactionLine(lancamentoSemExtratoList, contaDAO, linhaExtrato, (ExtratoTransactionLine) line);
			} else {
				analyseResult = showUnknownLine();
			}

			analyseResult.setOriginal(line.getOriginal());

			statuses.add(analyseResult);
		}

		grid.setValues(statuses);

		grid.show();
	}

	private ExtratoLineAnalyseResult syncBalanceLine() {
		ExtratoLineAnalyseResult result = new ExtratoLineAnalyseResult();
		result.setTipo('B');

		return result;
	}

	private ExtratoLineAnalyseResult syncTransactionLine(List<Lancamento> lancamentoSemExtratoList, ContaDAO contaDAO, ExtratoLancamento linhaExtrato, ExtratoTransactionLine line) {
		ExtratoLineAnalyseResult result = new ExtratoLineAnalyseResult();
		result.setTipo('T');

		Conta contaExtrato = contaDAO.resolveExtratoLine(banco, line.getReferencia());

		if (contaExtrato == null) {
			// TODO A resolução de referências por linha de comando deve ser
			// implementada aqui!
			return result;
		}
		
		result.setConta(contaExtrato);

		for (Lancamento lancamentoSemExtrato : lancamentoSemExtratoList) {
			if (lancamentoSemExtrato.getExtrato() != null) {
				// Ignora estas linhas. Elas podem ocorrer pois os itens da
				// lista lancamentoSemExtratoList são alterados neste loop
				continue;
			}

			Conta contaOrigemEsperada;
			Conta contaDestinoEsperada;

			if (lancamentoSemExtrato.getValor() > 0) {
				// Dinheiro saiu da conta
				contaOrigemEsperada = banco;
				contaDestinoEsperada = contaExtrato;
			} else {
				// Dinheiro entrou na conta
				contaOrigemEsperada = contaExtrato;
				contaDestinoEsperada = banco;
			}

			if (extratoLineMatch(lancamentoSemExtrato, line, contaOrigemEsperada, contaDestinoEsperada)) {
				lancamentoSemExtrato.setExtrato(linhaExtrato);

				if (switches.getRealize()) {
					lancamentoDAO.alterar(lancamentoSemExtrato);
				}

				result.setLancamento(lancamentoSemExtrato);

				break;
			}

		}

		return result;
	}

	private ExtratoLineAnalyseResult showUnknownLine() {
		ExtratoLineAnalyseResult result = new ExtratoLineAnalyseResult();

		result.setTipo('?');

		return result;
	}

	private TextGrid createGrid() {
		TextGrid grid = new TextGrid();

		grid.getData().setHeaderSeparatorChar('=');
		grid.getData().setTitle("Sincronização de Extrato banco \"" + banco.getNome() + "\"");

		TextGridFormattedColumn.createFormattedColumn(grid, "Tipo", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.CENTER, "getTipo");
		TextGridFormattedColumn.createFormattedColumn(grid, "Original", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.LEFT, "getOriginal");
		TextGridFormattedColumn.createFormattedColumn(grid, "Conta", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.LEFT, "getConta");
		TextGridFormattedColumn.createFormattedColumn(grid, "Lancamento", ControleFinanceiroFormatters.LANCAMENTO_FORMATTER, TextGridColumnAlignment.LEFT, "getLancamento");

		return grid;
	}

	private boolean extratoLineMatch(Lancamento lancto, ExtratoTransactionLine line, Conta contaOrigemEsperada, Conta contaDestinoEsperada) {
		boolean origemOk = lancto.getContaOrigem().equals(contaOrigemEsperada);
		boolean destinoOk = lancto.getContaDestino().equals(contaDestinoEsperada);
		boolean valorOk = lancto.getValor().equals(line.getValor());

		return origemOk && destinoOk && valorOk;
	}

	private Conta getConta(String nomeConta) throws ExtratoAnalyseException {
		ContaDAO dao = new ContaDAO();

		Conta conta = dao.getByNome(nomeConta);

		if (conta == null) {
			throw new ExtratoAnalyseException("Conta \"" + nomeConta + "\" não encontrada...");
		}

		return conta;
	}

	private List<ExtratoLancamento> getExtratoLancamentoOrfao(Conta banco) {
		ExtratoLancamentoDAO dao = new ExtratoLancamentoDAO();

		return dao.listarOrfaos(banco);
	}

	private List<Lancamento> getLancamentoSemExtratoList() {
		lancamentoDAO = new LancamentoDAO();

		return lancamentoDAO.listarSemExtrato();
	}

	private ExtratoAnalyseSwitches checkParamValid(Object param) throws ExtratoAnalyseException {
		if (!(param instanceof ExtratoAnalyseSwitches)) {
			throw new ExtratoAnalyseException("Parametro deve ser um ExtratoAnalyseSwitches.");
		}

		ExtratoAnalyseSwitches switches = (ExtratoAnalyseSwitches) param;

		if (switches.getNomeBanco() == null) {
			throw new ExtratoAnalyseException("Nome do banco deve ser informado.");
		}

		return switches;
	}

}
