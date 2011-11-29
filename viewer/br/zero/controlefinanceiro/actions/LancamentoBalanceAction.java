package br.zero.controlefinanceiro.actions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.zero.controlefinanceiro.commandlineparser.LancamentoBalanceSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;
import br.zero.controlefinanceiro.model.modelo.LancamentoModelo;
import br.zero.controlefinanceiro.utils.ControleFinanceiroException;
import br.zero.controlefinanceiro.utils.ControleFinanceiroFormatters;
import br.zero.textgrid.TextGrid;
import br.zero.textgrid.TextGridColumnAlignment;
import br.zero.textgrid.TextGridException;
import br.zero.textgrid.TextGridFormattedColumn;
import br.zero.tinycontroller.Action;

public class LancamentoBalanceAction implements Action {

	private LancamentoBalanceSwitches switches;

	public class LancamentoBalanceException extends ControleFinanceiroException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 17024381565601359L;

		public LancamentoBalanceException(String message) {
			super(message);
		}

	}

	public class LancamentoBalance {

		private Double saldo;
		private Lancamento lancamento;

		public LancamentoBalance(Lancamento lancamento) {
			this.lancamento = lancamento;
		}

		public Integer getId() {
			return lancamento.getId();
		}

		public LancamentoModelo getLancamentoModelo() {
			return lancamento.getLancamentoModelo();
		}

		public Calendar getData() {
			return lancamento.getData();
		}

		public int getN() {
			return lancamento.getN();
		}

		public Conta getContaOrigem() {
			return lancamento.getContaOrigem();
		}

		public Conta getContaDestino() {
			return lancamento.getContaDestino();
		}

		public Double getValor() {
			return lancamento.getValor();
		}

		public Double getSaldo() {
			return saldo;
		}

		public void setSaldo(Double saldo) {
			this.saldo = saldo;
		}

		public String getObservacao() {
			return lancamento.getObservacao();
		}

	}

	private TextGrid createGrid() {
		TextGrid grid = new TextGrid();

		grid.getData().setHeaderSeparatorChar('=');
		grid.getData().setTitle("Balanço da conta \"" + switches.getConta() + "\"");

		TextGridFormattedColumn.createFormattedColumn(grid, "id", TextGridFormattedColumn.ID_FORMATTER, TextGridColumnAlignment.RIGHT, "getId");
		TextGridFormattedColumn.createFormattedColumn(grid, "Data", TextGridFormattedColumn.DATE_FORMATTER, TextGridColumnAlignment.CENTER, "getData");
		TextGridFormattedColumn.createFormattedColumn(grid, "N", TextGridFormattedColumn.INTEGER_FORMATTER, TextGridColumnAlignment.LEFT, "getN");
		TextGridFormattedColumn.createFormattedColumn(grid, "Conta Origem", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.RIGHT, "getContaOrigem", " -> ");
		TextGridFormattedColumn.createFormattedColumn(grid, "Conta Destino", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.LEFT, "getContaDestino");
		TextGridFormattedColumn.createFormattedColumn(grid, "Valor", TextGridFormattedColumn.MONEY_FORMATTER, TextGridColumnAlignment.RIGHT, "getValor");
		TextGridFormattedColumn.createFormattedColumn(grid, "Saldo", TextGridFormattedColumn.MONEY_FORMATTER, TextGridColumnAlignment.RIGHT, "getSaldo");
		TextGridFormattedColumn.createFormattedColumn(grid, "Observação", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.LEFT, "getObservacao");

		return grid;
	}

	@Override
	public void run(Object param) throws TextGridException {
		LancamentoDAO dao = new LancamentoDAO();

		try {
			switches = checkParamValid(param);
		} catch (LancamentoBalanceException e) {
			throw new TextGridException(e);
		}

		ContaDAO contaDAO = new ContaDAO();

		Conta contaBalance = contaDAO.getByNome(switches.getConta());

		if (contaBalance == null) {
			throw new TextGridException(new LancamentoBalanceException("Conta \"" + switches.getConta() + "\" não encontrada..."));
		}

		List<Lancamento> lancamentoList = dao.listarPorConta(contaBalance);

		List<LancamentoBalance> lancamentoBalanceList = new ArrayList<LancamentoBalance>();

		Double saldo = 0.0;
		
		// TODO Recalcular o N, ignorar o do banco de dados
		for (Lancamento lancamento : lancamentoList) {
			LancamentoBalance lancamentoBalance = new LancamentoBalance(lancamento);

			if (lancamento.getContaOrigem().equals(contaBalance)) {
				saldo -= lancamento.getValor();
			} else {
				saldo += lancamento.getValor();
			}
			
			lancamentoBalance.setSaldo(saldo);
			
			lancamentoBalanceList.add(lancamentoBalance);
		}

		TextGrid grid = createGrid();

		grid.setValues(lancamentoBalanceList);

		grid.show();
		
		System.out.println("===> Saldo: " + saldo);
	}

	private LancamentoBalanceSwitches checkParamValid(Object param) throws LancamentoBalanceException {
		if (!(param instanceof LancamentoBalanceSwitches)) {
			throw new LancamentoBalanceException("Parametro deve ser um LancamentoBalanceSwitches.");
		}

		LancamentoBalanceSwitches switches = (LancamentoBalanceSwitches) param;

		if (switches.getConta() == null) {
			throw new LancamentoBalanceException("Conta deve ser informada");
		}

		return switches;
	}

}
