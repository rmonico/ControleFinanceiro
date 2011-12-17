package br.zero.controlefinanceiro.actions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.zero.controlefinanceiro.commandlineparser.ExtratoAnalyseSwitches;
import br.zero.controlefinanceiro.commandlineparser.ManualReference;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.controlefinanceiro.model.ExtratoLancamentoBalance;
import br.zero.controlefinanceiro.model.ParsedExtratoLancamento;
import br.zero.controlefinanceiro.model.ExtratoLineParser;
import br.zero.controlefinanceiro.model.ExtratoLancamentoTransaction;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;
import br.zero.controlefinanceiro.model.ExtratoLancamentoUnknown;
import br.zero.controlefinanceiro.model.extrato.ExtratoLancamento;
import br.zero.controlefinanceiro.model.extrato.ExtratoLancamentoDAO;
import br.zero.controlefinanceiro.utils.ControleFinanceiroException;
import br.zero.controlefinanceiro.utils.ControleFinanceiroFormatters;
import br.zero.controlefinanceiro.utils.ExtratoLineParserException;
import br.zero.textgrid.TextGrid;
import br.zero.textgrid.TextGridColumnAlignment;
import br.zero.textgrid.TextGridException;
import br.zero.textgrid.TextGridFormattedColumn;
import br.zero.textgrid.formatters.ToStringFormatter;
import br.zero.tinycontroller.Action;

public class ExtratoAnalyseAction implements Action {

	public enum StatusLinha {
		BALANCE("B"), TRANSACTION("T"), UNKNOWN("?");

		private String toString;

		private StatusLinha(String toString) {
			this.toString = toString;
		}

		@Override
		public String toString() {
			return toString;
		}
	}

	public enum ContaStatus {
		NOT_FOUND("!"), DONT_APPLY("-"), OK(" ");

		private String toString;

		private ContaStatus(String toString) {
			this.toString = toString;
		}

		@Override
		public String toString() {
			return toString;
		}

	}

	public enum LancamentoStatus {
		DONT_APPLY("-"), FOUND("U"), NOT_RELATED("?"), NEW("*");

		private String toString;

		private LancamentoStatus(String toString) {
			this.toString = toString;
		}

		@Override
		public String toString() {
			return toString;
		}
	}

	public class InternalManualReference {
		private Conta conta;
		private String regex;

		public Conta getConta() {
			return conta;
		}

		public void setConta(Conta conta) {
			this.conta = conta;
		}

		public String getRegex() {
			return regex;
		}

		public void setRegex(String regex) {
			this.regex = regex;
		}

	}

	public class ExtratoLineAnalyseResult {

		private StatusLinha statusLinha;
		private String original;
		private Conta conta;
		private Lancamento lancamento;
		private LancamentoStatus lancamentoStatus;
		private ContaStatus contaStatus;

		public StatusLinha getStatusLinha() {
			return statusLinha;
		}

		public void setStatusLinha(StatusLinha statusLinha) {
			this.statusLinha = statusLinha;
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

		public LancamentoStatus getLancamentoStatus() {
			return lancamentoStatus;
		}

		public void setLancamentoStatus(LancamentoStatus lancamentoStatus) {
			this.lancamentoStatus = lancamentoStatus;
		}

		public ContaStatus getContaStatus() {
			return contaStatus;
		}

		public void setContaStatus(ContaStatus contaStatus) {
			this.contaStatus = contaStatus;
		}

	}

	private LancamentoDAO lancamentoDAO;
	private Conta banco;
	private ExtratoAnalyseSwitches switches;
	private List<InternalManualReference> manualRefList;

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

		manualRefList = makeManualRefList();

		banco = getConta(switches.getNomeBanco());

		List<Lancamento> lancamentoSemExtratoList = getLancamentoSemExtratoList();

		List<ExtratoLancamento> extratoLancamentoOrfao = getExtratoLancamentoOrfao(banco);

		if (extratoLancamentoOrfao.size() == 0) {
			System.out.println();
			System.out.println("Nenhum item de extrato órfão encontrado para o banco \"" + banco.getNome() + "\".");

			return;
		}

		TextGrid grid = createGrid();
		List<ExtratoLineAnalyseResult> statuses;
		
		try {
			statuses= makeSync(lancamentoSemExtratoList, extratoLancamentoOrfao);
		} catch (ExtratoLineParserException e) {
			throw new ExtratoAnalyseException(e);
		}
		
		grid.setValues(statuses);

		try {
			grid.show();
		} catch (TextGridException e) {
			throw new ExtratoAnalyseException(e);
		}
	}

	private List<InternalManualReference> makeManualRefList() throws ExtratoAnalyseException {
		List<InternalManualReference> mrl = new ArrayList<InternalManualReference>();

		ContaDAO dao = new ContaDAO();

		for (ManualReference mr : switches.getManualRefList()) {
			InternalManualReference imr = new InternalManualReference();

			Conta conta = dao.getByNome(mr.getNomeConta());

			if (conta == null) {
				throw new ExtratoAnalyseException("Referências manuais: conta " + mr.getNomeConta() + "não encontrada.");
			}

			imr.setConta(conta);
			imr.setRegex(mr.getRegex());

			mrl.add(imr);
		}

		return mrl;
	}

	private List<ExtratoLineAnalyseResult> makeSync(List<Lancamento> lancamentoSemExtratoList, List<ExtratoLancamento> extratoLancamentoOrfao) throws ExtratoLineParserException, ExtratoAnalyseException {
		List<ParsedExtratoLancamento> extratoLines = new ArrayList<ParsedExtratoLancamento>();
		
		makeParser(extratoLancamentoOrfao, extratoLines);
		
		List<ExtratoLineAnalyseResult> statuses = new ArrayList<ExtratoLineAnalyseResult>();

		ContaDAO contaDAO = new ContaDAO();
		
//		Calendar dataAnterior = null;
		
		for (ParsedExtratoLancamento extrato : extratoLines) {
			ExtratoLineAnalyseResult analyseResult;

			if (extrato instanceof ExtratoLancamentoBalance) {
				analyseResult = syncBalanceLine();
			} else if (extrato instanceof ExtratoLancamentoTransaction) {
				analyseResult = syncTransactionLine(lancamentoSemExtratoList, contaDAO, (ExtratoLancamentoTransaction) extrato);
			} else if (extrato instanceof ExtratoLancamentoUnknown) {
				analyseResult = syncUnknownLine();
			} else {
				throw new ExtratoAnalyseException("Classe de linha desconhecida (\"" + extrato + "\")");
			}

			analyseResult.setOriginal(extrato.getOrigem().getOriginal());

			statuses.add(analyseResult);
		}

		return statuses;
	}

	private void makeParser(List<ExtratoLancamento> extratoLancamentoOrfao, List<ParsedExtratoLancamento> extratoLines) throws ExtratoLineParserException, ExtratoAnalyseException {
		ExtratoLineParser parser = banco.getParser();

		if (parser == null) {
			throw new ExtratoAnalyseException("Nenhum parser registrado para o banco: \"" + banco.getNome() + "\".");
		}

		for (ExtratoLancamento el : extratoLancamentoOrfao) {
			ParsedExtratoLancamento line = parser.parse(el);

			extratoLines.add(line);
		}
	}

	private ExtratoLineAnalyseResult syncBalanceLine() {
		ExtratoLineAnalyseResult result = new ExtratoLineAnalyseResult();
		result.setStatusLinha(StatusLinha.BALANCE);
		result.setContaStatus(ContaStatus.DONT_APPLY);
		result.setLancamentoStatus(LancamentoStatus.DONT_APPLY);

		return result;
	}

	private ExtratoLineAnalyseResult syncTransactionLine(List<Lancamento> lancamentoSemExtratoList, ContaDAO contaDAO, ExtratoLancamentoTransaction line) {
		ExtratoLineAnalyseResult result = new ExtratoLineAnalyseResult();
		result.setStatusLinha(StatusLinha.TRANSACTION);

		Conta contaExtrato = resolveReference(contaDAO, line.getReferencia());

		if (contaExtrato == null) {
			result.setContaStatus(ContaStatus.NOT_FOUND);
			return result;
		}

		result.setContaStatus(ContaStatus.OK);

		result.setConta(contaExtrato);

		for (Lancamento lancamentoSemExtrato : lancamentoSemExtratoList) {
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

			if ((lancamentoSemExtrato.getExtrato() == null) && (extratoLineMatch(lancamentoSemExtrato, line, contaOrigemEsperada, contaDestinoEsperada))) {
				result.setLancamentoStatus(LancamentoStatus.FOUND);

				lancamentoSemExtrato.setExtrato(line.getOrigem());

				if (switches.getRealize()) {
					lancamentoDAO.alterar(lancamentoSemExtrato);
				}

				result.setLancamento(lancamentoSemExtrato);

				return result;
			}

		}

		// Nenhum lançamento correspondente encontrado
		result.setLancamentoStatus(LancamentoStatus.NEW);

		Lancamento novoLancamento = new Lancamento();

		result.setLancamento(novoLancamento);

		novoLancamento.setData(line.getData());
		// Calcular depois. O cálculo será complicado, deverá levar em conta os
		// N's pré-existentes no banco e os gerados aqui.
		novoLancamento.setN(-1);

		Conta contaOrigemEsperada;
		Conta contaDestinoEsperada;

		if (line.getValor() > 0) {
			// Dinheiro saiu da conta
			contaOrigemEsperada = banco;
			contaDestinoEsperada = contaExtrato;
		} else {
			// Dinheiro entrou na conta
			contaOrigemEsperada = contaExtrato;
			contaDestinoEsperada = banco;
		}

		novoLancamento.setContaOrigem(contaOrigemEsperada);
		novoLancamento.setContaDestino(contaDestinoEsperada);

		novoLancamento.setValor(line.getValor());

		novoLancamento.setExtrato(line.getOrigem());

		if (switches.getRealize()) {
			lancamentoDAO.inserir(novoLancamento);
		}

		return result;
	}

	private Conta resolveReference(ContaDAO contaDAO, String referencia) {
		Conta conta = contaDAO.resolveExtratoLine(banco, referencia);

		if (conta != null) {
			return conta;
		}

		for (InternalManualReference imr : manualRefList) {
			if (referencia.matches(imr.getRegex())) {
				return imr.getConta();
			}
		}

		return null;
	}

	private ExtratoLineAnalyseResult syncUnknownLine() {
		ExtratoLineAnalyseResult result = new ExtratoLineAnalyseResult();

		result.setStatusLinha(StatusLinha.UNKNOWN);
		result.setContaStatus(ContaStatus.DONT_APPLY);
		result.setLancamentoStatus(LancamentoStatus.DONT_APPLY);

		return result;
	}

	private TextGrid createGrid() {
		TextGrid grid = new TextGrid();

		grid.getData().setHeaderSeparatorChar('=');
		grid.getData().setTitle("Sincronização de Extrato banco \"" + banco.getNome() + "\"");

		TextGridFormattedColumn.createFormattedColumn(grid, "", new ToStringFormatter(""), TextGridColumnAlignment.CENTER, "getStatusLinha");
		TextGridFormattedColumn.createFormattedColumn(grid, "Original", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.LEFT, "getOriginal");
		TextGridFormattedColumn.createFormattedColumn(grid, "", new ToStringFormatter(""), TextGridColumnAlignment.CENTER, "getContaStatus");
		TextGridFormattedColumn.createFormattedColumn(grid, "Conta", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.LEFT, "getConta");
		TextGridFormattedColumn.createFormattedColumn(grid, "", new ToStringFormatter(""), TextGridColumnAlignment.CENTER, "getLancamentoStatus");
		TextGridFormattedColumn.createFormattedColumn(grid, "Lancamento", ControleFinanceiroFormatters.LANCAMENTO_FORMATTER, TextGridColumnAlignment.LEFT, "getLancamento");

		return grid;
	}

	private boolean extratoLineMatch(Lancamento lancto, ExtratoLancamentoTransaction line, Conta contaOrigemEsperada, Conta contaDestinoEsperada) {
		Calendar dataEsperada = line.getData();
		boolean dataOk = lancto.getData().equals(dataEsperada);
		boolean origemOk = lancto.getContaOrigem().equals(contaOrigemEsperada);
		boolean destinoOk = lancto.getContaDestino().equals(contaDestinoEsperada);
		boolean valorOk = lancto.getValor().equals(Math.abs(line.getValor()));

		return dataOk && origemOk && destinoOk && valorOk;
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
