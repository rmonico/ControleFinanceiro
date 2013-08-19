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

public class LancamentoModeloListAction implements Action<LancamentoModeloListSwitches, Object> {

	public class LancamentoModeloContabilizavel implements Contabilizavel {

		private LancamentoModelo lancamento;
		private Double saldoOrigem;
		private Double saldoDestino;
		private Double porcentualReceita;
		private Double porcentualDespesa;
		private Double porcentualReceitaAcumulado;
		private Double porcentualDespesaAcumulado;

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
		 * Devolve o porcentual deste lançamento em relação ao total das
		 * receitas.
		 * 
		 * @return
		 */
		public Double getPorcentualReceita() {
			return porcentualReceita;
		}

		public void setPorcentualReceita(Double porcentual) {
			this.porcentualReceita = porcentual;
		}

		/**
		 * O porcentual acima, acumulado.
		 * 
		 * @return
		 */
		public Double getPorcentualReceitaAcumulado() {
			return porcentualReceitaAcumulado;
		}

		public void setPorcentualReceitaAcumulado(Double porcentualAcumulado) {
			this.porcentualReceitaAcumulado = porcentualAcumulado;
		}

		/**
		 * Devolve o porcentual deste lançamento em relação ao total das
		 * despesas.
		 * 
		 * @return
		 */
		public Double getPorcentualDespesa() {
			return porcentualDespesa;
		}

		public void setPorcentualDespesa(Double porcentual) {
			this.porcentualDespesa = porcentual;
		}

		/**
		 * O porcentual acima, acumulado.
		 * 
		 * @return
		 */
		public Double getPorcentualDespesaAcumulado() {
			return porcentualDespesaAcumulado;
		}

		public void setPorcentualDespesaAcumulado(Double porcentualAcumulado) {
			this.porcentualDespesaAcumulado = porcentualAcumulado;
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
		TextGridFormattedColumn.createFormattedColumn(grid, "% Receita", TextGridFormattedColumn.PERCENT_FORMATTER, TextGridColumnAlignment.RIGHT, "getPorcentualReceita");
		TextGridFormattedColumn.createFormattedColumn(grid, "Acumulado", TextGridFormattedColumn.PERCENT_FORMATTER, TextGridColumnAlignment.RIGHT, "getPorcentualReceitaAcumulado");
		TextGridFormattedColumn.createFormattedColumn(grid, "% Despesa", TextGridFormattedColumn.PERCENT_FORMATTER, TextGridColumnAlignment.RIGHT, "getPorcentualDespesa");
		TextGridFormattedColumn.createFormattedColumn(grid, "Acumulado", TextGridFormattedColumn.PERCENT_FORMATTER, TextGridColumnAlignment.RIGHT, "getPorcentualDespesaAcumulado");
	}

	private Double totalReceitas;
	private Double totalDespesas;

	@Override
	public Object run(LancamentoModeloListSwitches switches) throws TextGridException, LancamentoModeloListException {

		List<LancamentoModelo> lancamentoModeloList = getLancamentosPorModelo(switches);

		Contabilizador contabilizador = new Contabilizador();

		Packer<LancamentoModeloContabilizavel, LancamentoModelo> packager = new LancamentoModeloPacker();

		List<LancamentoModeloContabilizavel> lancamentoContabilizavelList = contabilizador.packageList(lancamentoModeloList, packager);

		contabilizador.setList(lancamentoContabilizavelList);

		contabilizador.contabilizar();
		
		calcTotalLancamentos(lancamentoContabilizavelList);

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
		
		return null;
	}

	private void calcStatistics(List<LancamentoModeloContabilizavel> lancamentoContabilizavelList) {
		Double porcentualReceitaAcumulado = 0.0;
		Double porcentualDespesaAcumulado = 0.0;
		
		for (LancamentoModeloContabilizavel lm : lancamentoContabilizavelList) {

			if (lm.getContaOrigem().getContabilizavel()) {
				Double porcentual = lm.getValor() / totalDespesas;

				lm.setPorcentualDespesa(porcentual);

				porcentualDespesaAcumulado += porcentual;

				lm.setPorcentualDespesaAcumulado(porcentualDespesaAcumulado);
			}

			if (lm.getContaDestino().getContabilizavel()) {
				Double porcentual = lm.getValor() / totalReceitas;

				lm.setPorcentualReceita(porcentual);

				porcentualReceitaAcumulado += porcentual;

				lm.setPorcentualReceitaAcumulado(porcentualReceitaAcumulado);
			}

		}
	}

	private void calcTotalLancamentos(List<LancamentoModeloContabilizavel> lancamentoContabilizavelList) {
		totalDespesas = 0.0;
		totalReceitas = 0.0;
		
		for (LancamentoModeloContabilizavel lm : lancamentoContabilizavelList) {
			if (lm.getContaOrigem().getContabilizavel()) {
				totalDespesas += lm.getValor();
			}
			
			if (lm.getContaDestino().getContabilizavel()) {
				totalReceitas += lm.getValor();
			}
		}
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

}
