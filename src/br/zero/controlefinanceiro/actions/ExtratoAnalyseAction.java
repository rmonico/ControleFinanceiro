package br.zero.controlefinanceiro.actions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.zero.controlefinanceiro.abstractextratoparser.DatedExtratoLancamento;
import br.zero.controlefinanceiro.abstractextratoparser.ExtratoLancamentoBalance;
import br.zero.controlefinanceiro.abstractextratoparser.ExtratoLancamentoTransaction;
import br.zero.controlefinanceiro.abstractextratoparser.ExtratoLancamentoUnknown;
import br.zero.controlefinanceiro.abstractextratoparser.ExtratoLineParser;
import br.zero.controlefinanceiro.abstractextratoparser.ExtratoParsers;
import br.zero.controlefinanceiro.abstractextratoparser.ParsedExtratoLancamento;
import br.zero.controlefinanceiro.commandlineparser.ExtratoAnalyseSwitches;
import br.zero.controlefinanceiro.commandlineparser.ManualReference;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.ContaDAO;
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
import br.zero.textgrid.formatters.ToStringFormatter;
import br.zero.tinycontroller.Action;

public class ExtratoAnalyseAction implements Action {

	public enum StatusLinha {
		BALANCE("B"), TRANSACTION("T"), UNKNOWN("?"), DONT_APPLY("-");

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
		private DatedExtratoLancamento extratoLancamento;
		private Conta conta;
		private Lancamento lancamento;
		private LancamentoStatus lancamentoStatus;
		private ContaStatus contaStatus;

		public StatusLinha getStatusLinha() {
			return statusLinha;
		}

		public void setLinhaStatus(StatusLinha statusLinha) {
			this.statusLinha = statusLinha;
		}

		public DatedExtratoLancamento getExtrato() {
			return extratoLancamento;
		}

		public void setExtrato(DatedExtratoLancamento extrato) {
			this.extratoLancamento = extrato;
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

		public Calendar getOriginalDate() {
			if (extratoLancamento == null) {
				return null;
			} else {
				return extratoLancamento.getData();
			}
		}

		public String getLinhaOriginal() {
			if (extratoLancamento == null) {
				return "Ignored";
			} else {
				return extratoLancamento.getOrigem().getOriginal();
			}
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
			statuses = makeSync(lancamentoSemExtratoList, extratoLancamentoOrfao);
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

		for (int i = 0; i < extratoLines.size(); i++) {
			ParsedExtratoLancamento extrato = extratoLines.get(i);

			ExtratoLineAnalyseResult analyseResult;

			if (extrato instanceof ExtratoLancamentoBalance) {
				analyseResult = syncBalanceLine();
				analyseResult.setExtrato((ExtratoLancamentoBalance) extrato);
			} else if (extrato instanceof ExtratoLancamentoTransaction) {
				analyseResult = syncTransactionLine(lancamentoSemExtratoList, contaDAO, (ExtratoLancamentoTransaction) extrato);
				analyseResult.setExtrato((ExtratoLancamentoTransaction) extrato);
			} else if (extrato instanceof ExtratoLancamentoUnknown) {
				analyseResult = syncUnknownLine();
				analyseResult.setExtrato(null);
			} else {
				throw new ExtratoAnalyseException("Classe de linha desconhecida (\"" + extrato + "\")");
			}

			statuses.add(analyseResult);

			checkLancamentosNaoResolvidos(extratoLines, lancamentoSemExtratoList, i, statuses);
		}

		return statuses;
	}

	private void checkLancamentosNaoResolvidos(List<ParsedExtratoLancamento> extratoLines, List<Lancamento> lancamentoOrfaoList, int i, List<ExtratoLineAnalyseResult> statuses) {
		ParsedExtratoLancamento extrato = extratoLines.get(i);

		if (i != extratoLines.size() - 1) {
			Calendar data;
			if (extrato instanceof DatedExtratoLancamento) {
				data = ((DatedExtratoLancamento) extrato).getData();
			} else {
				return;
			}

			ParsedExtratoLancamento proximo = null;

			Calendar dataProximo = null;
			for (int j = i + 1; j < extratoLines.size(); j++) {
				proximo = extratoLines.get(j);

				if (proximo instanceof DatedExtratoLancamento) {
					dataProximo = ((DatedExtratoLancamento) proximo).getData();
					break;
				}
			}

			if ((dataProximo == null) || (!data.equals(dataProximo))) {
				addLancamentosNaoResolvidos(data, lancamentoOrfaoList, statuses);
			}
		}
	}

	private void addLancamentosNaoResolvidos(Calendar data, List<Lancamento> lancamentoOrfaoList, List<ExtratoLineAnalyseResult> statuses) {
		for (Lancamento lancamento : lancamentoOrfaoList) {
			if ((lancamento.getData().equals(data)) && (lancamento.getExtrato() == null)) {
				ExtratoLineAnalyseResult r = new ExtratoLineAnalyseResult();

				r.setLinhaStatus(StatusLinha.DONT_APPLY);
				r.setContaStatus(ContaStatus.DONT_APPLY);
				r.setLancamentoStatus(LancamentoStatus.NOT_RELATED);
				r.setLancamento(lancamento);

				statuses.add(r);
			}

		}

	}

	private void makeParser(List<ExtratoLancamento> extratoLancamentoOrfao, List<ParsedExtratoLancamento> extratoLines) throws ExtratoLineParserException, ExtratoAnalyseException {
		ExtratoLineParser parser = ExtratoParsers.getParser(banco);

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
		result.setLinhaStatus(StatusLinha.BALANCE);
		result.setContaStatus(ContaStatus.DONT_APPLY);
		result.setLancamentoStatus(LancamentoStatus.DONT_APPLY);

		return result;
	}

	private ExtratoLineAnalyseResult syncTransactionLine(List<Lancamento> lancamentoSemExtratoList, ContaDAO contaDAO, ExtratoLancamentoTransaction line) {
		ExtratoLineAnalyseResult result = new ExtratoLineAnalyseResult();
		result.setLinhaStatus(StatusLinha.TRANSACTION);

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
			// Dinheiro entrou da conta
			contaOrigemEsperada = contaExtrato;
			contaDestinoEsperada = banco;
		} else {
			// Dinheiro saiu na conta
			contaOrigemEsperada = banco;
			contaDestinoEsperada = contaExtrato;
		}

		novoLancamento.setContaOrigem(contaOrigemEsperada);
		novoLancamento.setContaDestino(contaDestinoEsperada);

		novoLancamento.setValor(Math.abs(line.getValor()));

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

		result.setLinhaStatus(StatusLinha.UNKNOWN);
		result.setContaStatus(ContaStatus.DONT_APPLY);
		result.setLancamentoStatus(LancamentoStatus.DONT_APPLY);

		return result;
	}

	private TextGrid createGrid() {
		TextGrid grid = new TextGrid();

		grid.getData().setHeaderSeparatorChar('=');
		grid.getData().setTitle("Sincronização de Extrato banco \"" + banco.getNome() + "\"");

		TextGridFormattedColumn.createFormattedColumn(grid, "", new ToStringFormatter(""), TextGridColumnAlignment.CENTER, "getStatusLinha");
		TextGridFormattedColumn.createFormattedColumn(grid, "Date", TextGridFormattedColumn.DATE_FORMATTER, TextGridColumnAlignment.LEFT, "getOriginalDate");
		TextGridFormattedColumn.createFormattedColumn(grid, "Original", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.LEFT, "getLinhaOriginal");
		TextGridFormattedColumn.createFormattedColumn(grid, "", new ToStringFormatter(""), TextGridColumnAlignment.CENTER, "getContaStatus");
		TextGridFormattedColumn.createFormattedColumn(grid, "Conta", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.LEFT, "getConta");
		TextGridFormattedColumn.createFormattedColumn(grid, "", new ToStringFormatter(""), TextGridColumnAlignment.CENTER, "getLancamentoStatus");
		TextGridFormattedColumn.createFormattedColumn(grid, "Lancamento", ControleFinanceiroFormatters.LANCAMENTO_FORMATTER, TextGridColumnAlignment.LEFT, "getLancamento");

		return grid;
	}

	private boolean extratoLineMatch(Lancamento lancto, ExtratoLancamentoTransaction line, Conta contaOrigemEsperada, Conta contaDestinoEsperada) {
		Calendar menorDataEsperada = line.getData();
		// Regras dos três dias. Por enquanto não vou utilizar.
		// Calendar maiorDataEsperada = (Calendar) line.getData().clone();
		// maiorDataEsperada.add(Calendar.DAY_OF_MONTH, 3);
		//
		// boolean dataOk = ((lancto.getData().compareTo(maiorDataEsperada) <=
		// 0) && (lancto.getData().compareTo(menorDataEsperada) >= 0));
		boolean dataOk = lancto.getData().equals(menorDataEsperada);
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

		return lancamentoDAO.listarSemExtrato(banco);
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
