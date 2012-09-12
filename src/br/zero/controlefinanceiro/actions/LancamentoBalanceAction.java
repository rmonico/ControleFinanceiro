package br.zero.controlefinanceiro.actions;

import java.util.List;
import java.util.Map;

import br.zero.controlefinanceiro.commandlineparser.LancamentoBalanceSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;
import br.zero.controlefinanceiro.utils.Contabilizador;
import br.zero.controlefinanceiro.utils.ControleFinanceiroException;
import br.zero.controlefinanceiro.utils.ControleFinanceiroFormatters;
import br.zero.controlefinanceiro.utils.LancamentoContabilizavel;
import br.zero.controlefinanceiro.utils.Packer;
import br.zero.textgrid.TextGrid;
import br.zero.textgrid.TextGridColumnAlignment;
import br.zero.textgrid.TextGridException;
import br.zero.textgrid.TextGridFormattedColumn;
import br.zero.tinycontroller.Action;

public class LancamentoBalanceAction implements Action<LancamentoBalanceSwitches, Object> {

	private LancamentoBalanceSwitches switches;
	private Conta contaBalance;

	public class LancamentoBalanceException extends ControleFinanceiroException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 17024381565601359L;

		public LancamentoBalanceException(String message) {
			super(message);
		}

	}

	public class LancamentoBalance extends LancamentoContabilizavel {
		
		public Double getSaldo() {
			return getContaOrigem().equals(contaBalance) ? getSaldoOrigem(): getSaldoDestino();
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
	public Object run(LancamentoBalanceSwitches switches) throws TextGridException, LancamentoBalanceException {
		LancamentoDAO dao = new LancamentoDAO();

		checkParamValid(switches);

		ContaDAO contaDAO = new ContaDAO();

		contaBalance = contaDAO.getByNome(switches.getConta());

		if (contaBalance == null) {
			throw new TextGridException(new LancamentoBalanceException("Conta \"" + switches.getConta() + "\" não encontrada..."));
		}

		List<Lancamento> lancamentoList = dao.listarPorConta(contaBalance);

		Contabilizador contabilizador = new Contabilizador();
		
		Packer<LancamentoBalance, Lancamento> packager = new Packer<LancamentoBalance, Lancamento>() {
			@Override
			public LancamentoBalance pack(Lancamento lancamento) {
				LancamentoBalance lancamentoBalance = new LancamentoBalance();
				lancamentoBalance.setLancamentoBase(lancamento);
				
				return lancamentoBalance;
			}
		};
		
		List<LancamentoBalance> lancamentoBalanceList = contabilizador.packageList(lancamentoList, packager);
		
		contabilizador.setList(lancamentoBalanceList);
		
		contabilizador.contabilizar();

		TextGrid grid = createGrid();

		grid.setValues(lancamentoBalanceList);

		grid.show();
		
		Map<Conta, Double> saldosPorConta = contabilizador.getSaldosPorConta();
		Double saldo = saldosPorConta.get(contaBalance);
		
		System.out.println("===> Saldo: " + saldo);
		
		return null;
	}

	private void checkParamValid(LancamentoBalanceSwitches param) throws LancamentoBalanceException {
		if (switches.getConta() == null) {
			throw new LancamentoBalanceException("Conta deve ser informada");
		}
	}

}
