package br.zero.controlefinanceiro.actions;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import br.zero.controlefinanceiro.commandlineparser.LancamentoModeloListSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.modelo.LancamentoModelo;
import br.zero.controlefinanceiro.model.modelo.LancamentoModeloDAO;
import br.zero.controlefinanceiro.model.modelo.Modelo;
import br.zero.controlefinanceiro.utils.Contabilizador;
import br.zero.controlefinanceiro.utils.Contabilizavel;
import br.zero.controlefinanceiro.utils.ControleFinanceiroException;
import br.zero.controlefinanceiro.utils.ControleFinanceiroFormatters;
import br.zero.controlefinanceiro.utils.Packer;
import br.zero.textgrid.TextGrid;
import br.zero.textgrid.TextGridColumnAlignment;
import br.zero.textgrid.TextGridException;
import br.zero.textgrid.TextGridFormattedColumn;
import br.zero.tinycontroller.Action;

public class LancamentoModeloListAction implements Action {

	public class LancamentoModeloContabilizavel implements Contabilizavel {

		private LancamentoModelo lancamento;
		private Double saldoOrigem;
		private Double saldoDestino;
		private Double porcentual;
		private Double porcentualAcumulado;

		@Override
		public Double getValor() {
			return lancamento.getValor();
		}

		@Override
		public Conta getContaOrigem() {
			return lancamento.getContaOrigem();
		}

		@Override
		public void setSaldoOrigem(Double saldo) {
			saldoOrigem = saldo;
		}

		public Double getSaldoOrigem() {
			return saldoOrigem;
		}

		@Override
		public Conta getContaDestino() {
			return lancamento.getContaDestino();
		}

		@Override
		public void setSaldoDestino(Double saldo) {
			saldoDestino = saldo;
		}

		public Double getSaldoDestino() {
			return saldoDestino;
		}

		public void setLancamentoModeloBase(LancamentoModelo lancamento, Calendar database) {
			this.lancamento = lancamento;
		}

		public Integer getId() {
			return lancamento.getId();
		}

		public Modelo getModelo() {
			return lancamento.getModelo();
		}

		public Integer getDiaVencimento() {
			return lancamento.getDiaVencimento();
		}

		public String getObservacao() {
			return lancamento.getObservacao();
		}

		/**
		 * Devolve o porcentual deste lançamento em relação ao total dos
		 * lançamentos.
		 * 
		 * @return
		 */
		public Double getPorcentual() {
			return porcentual;
		}

		public void setPorcentual(Double porcentual) {
			this.porcentual = porcentual;
		}

		/**
		 * O porcentual acima, acumulado.
		 * 
		 * @return
		 */
		public Double getPorcentualAcumulado() {
			return porcentualAcumulado;
		}

		public void setPorcentualAcumulado(Double porcentualAcumulado) {
			this.porcentualAcumulado = porcentualAcumulado;
		}
	}

	public class LancamentoModeloPacker implements Packer<LancamentoModeloContabilizavel, LancamentoModelo> {

		private Calendar database = getDatabase();

		@Override
		public LancamentoModeloContabilizavel pack(LancamentoModelo p) {
			LancamentoModeloContabilizavel lc = new LancamentoModeloContabilizavel();

			lc.setLancamentoModeloBase(p, database);

			return lc;
		}

		private Calendar getDatabase() {
			Calendar database = GregorianCalendar.getInstance();

			database.set(Calendar.DAY_OF_MONTH, 1);

			return database;
		}

	}

	private class LancamentoModeloListException extends ControleFinanceiroException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1702349740543718569L;

		public LancamentoModeloListException(String message) {
			super("lancmodelo ls: " + message);
		}

	}

	private static TextGrid grid = null;

	public static TextGrid getGrid() {
		if (grid == null) {
			createGrid();
		}

		return grid;
	}

	private static void createGrid() {
		LancamentoModeloListAction.grid = new TextGrid();

		grid.getData().setHeaderSeparatorChar('=');
		grid.getData().setTitle("Lista de Lançamentos");

		TextGridFormattedColumn.createFormattedColumn(grid, "id", TextGridFormattedColumn.ID_FORMATTER, TextGridColumnAlignment.RIGHT, "getId");
		TextGridFormattedColumn.createFormattedColumn(grid, "Modelo", ControleFinanceiroFormatters.MODELO_FORMATTER, TextGridColumnAlignment.LEFT, "getModelo");
		TextGridFormattedColumn.createFormattedColumn(grid, "Vencimento", TextGridFormattedColumn.INTEGER_FORMATTER, TextGridColumnAlignment.LEFT, "getDiaVencimento");
		TextGridFormattedColumn.createFormattedColumn(grid, "Conta Origem", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.RIGHT, "getContaOrigem");
		TextGridFormattedColumn.createFormattedColumn(grid, "Conta Destino", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.LEFT, "getContaDestino");
		TextGridFormattedColumn.createFormattedColumn(grid, "Valor", TextGridFormattedColumn.MONEY_FORMATTER, TextGridColumnAlignment.RIGHT, "getValor");
		TextGridFormattedColumn.createFormattedColumn(grid, "Observação", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.LEFT, "getObservacao");
		TextGridFormattedColumn.createFormattedColumn(grid, "Porcentual", TextGridFormattedColumn.PERCENT_FORMATTER, TextGridColumnAlignment.RIGHT, "getPorcentual");
		TextGridFormattedColumn.createFormattedColumn(grid, "Acumulado", TextGridFormattedColumn.PERCENT_FORMATTER, TextGridColumnAlignment.RIGHT, "getPorcentualAcumulado");
	}

	@Override
	public void run(Object param) throws TextGridException, LancamentoModeloListException {

		LancamentoModeloListSwitches switches = checkParamValid(param);

		List<LancamentoModelo> lancamentoModeloList = getLancamentosPorModelo(switches);

		Contabilizador contabilizador = new Contabilizador();

		Packer<LancamentoModeloContabilizavel, LancamentoModelo> packager = new LancamentoModeloPacker();

		List<LancamentoModeloContabilizavel> lancamentoContabilizavelList = contabilizador.packageList(lancamentoModeloList, packager);

		contabilizador.setList(lancamentoContabilizavelList);

		contabilizador.contabilizar();

		calcStatistics(lancamentoContabilizavelList);

		Map<Conta, Double> saldos = contabilizador.getSaldosPorConta();

		TextGrid grid = getGrid();

		grid.setValues(lancamentoContabilizavelList);

		grid.show();

		System.out.println();
		System.out.println();
		System.out.println("-- Saldos: --");
		System.out.println();
		for (Conta conta : saldos.keySet()) {
			System.out.println("===> " + conta.getNome() + ": " + saldos.get(conta));
		}
	}

	private void calcStatistics(List<LancamentoModeloContabilizavel> lancamentoContabilizavelList) {
		Double totalLancamentos = calcTotalLancamentos(lancamentoContabilizavelList);

		Double porcentualAcumulado = 0.0;
		for (LancamentoModeloContabilizavel lm : lancamentoContabilizavelList) {
			Double porcentual = lm.getValor() / totalLancamentos;
			
			lm.setPorcentual(porcentual);
			
			porcentualAcumulado += porcentual;
			
			lm.setPorcentualAcumulado(porcentualAcumulado);
		}
	}

	private Double calcTotalLancamentos(List<LancamentoModeloContabilizavel> lancamentoContabilizavelList) {
		Double total = 0.0;

		for (LancamentoModeloContabilizavel lm : lancamentoContabilizavelList) {
			total += lm.getValor();
		}

		return total;
	}

	private List<LancamentoModelo> getLancamentosPorModelo(LancamentoModeloListSwitches switches) {
		LancamentoModeloDAO dao = new LancamentoModeloDAO();

		List<LancamentoModelo> lancamentoModeloList;

		if (switches.getModelo() == null) {
			lancamentoModeloList = dao.listarTodos();
		} else {
			lancamentoModeloList = dao.listarPorModelo(switches.getModelo());
		}

		return lancamentoModeloList;
	}

	private LancamentoModeloListSwitches checkParamValid(Object param) throws LancamentoModeloListException {
		if (!(param instanceof LancamentoModeloListSwitches)) {
			throw new LancamentoModeloListException("Parametro deve ser um LancamentoModeloListSwitches.");
		}

		LancamentoModeloListSwitches switches = (LancamentoModeloListSwitches) param;

		return switches;
	}

}
