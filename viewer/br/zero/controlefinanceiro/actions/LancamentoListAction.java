package br.zero.controlefinanceiro.actions;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.zero.controlefinanceiro.ControleFinanceiroFormatters;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;
import br.zero.controlefinanceiro.model.modelo.LancamentoModelo;
import br.zero.textgrid.TextGrid;
import br.zero.textgrid.TextGridColumnAlignment;
import br.zero.textgrid.TextGridException;
import br.zero.textgrid.TextGridFormattedColumn;
import br.zero.tinycontroller.Action;

public class LancamentoListAction implements Action {

	public class LancamentoForList {
		private Double saldoOrigem;
		private Double saldoDestino;
		private Lancamento lancamento;

		public LancamentoForList(Lancamento lancamento) {
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

		public String getObservacao() {
			return lancamento.getObservacao();
		}

		public Double getSaldoOrigem() {
			return saldoOrigem;
		}

		public void setSaldoOrigem(Double saldoOrigem) {
			this.saldoOrigem = saldoOrigem;
		}

		public Double getSaldoDestino() {
			return saldoDestino;
		}

		public void setSaldoDestino(Double saldoDestino) {
			this.saldoDestino = saldoDestino;
		}

	}

	private TextGrid createGrid() {
		TextGrid grid = new TextGrid();

		grid.getData().setHeaderSeparatorChar('=');
		grid.getData().setTitle("Lista de Lançamentos");

		TextGridFormattedColumn.createFormattedColumn(grid, "id", TextGridFormattedColumn.ID_FORMATTER, TextGridColumnAlignment.RIGHT, "getId");
		TextGridFormattedColumn.createFormattedColumn(grid, "Data", TextGridFormattedColumn.DATE_FORMATTER, TextGridColumnAlignment.CENTER, "getData");
		TextGridFormattedColumn.createFormattedColumn(grid, "N", TextGridFormattedColumn.INTEGER_FORMATTER, TextGridColumnAlignment.LEFT, "getN");
		TextGridFormattedColumn.createFormattedColumn(grid, "Conta Origem", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.RIGHT, "getContaOrigem", " -> ");
		TextGridFormattedColumn.createFormattedColumn(grid, "Conta Destino", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.LEFT, "getContaDestino");
		TextGridFormattedColumn.createFormattedColumn(grid, "Valor", TextGridFormattedColumn.MONEY_FORMATTER, TextGridColumnAlignment.RIGHT, "getValor");
		TextGridFormattedColumn.createFormattedColumn(grid, "Saldo Origem", TextGridFormattedColumn.MONEY_FORMATTER, TextGridColumnAlignment.RIGHT, "getSaldoOrigem");
		TextGridFormattedColumn.createFormattedColumn(grid, "Saldo Destino", TextGridFormattedColumn.MONEY_FORMATTER, TextGridColumnAlignment.RIGHT, "getSaldoDestino");
		TextGridFormattedColumn.createFormattedColumn(grid, "Observação", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.LEFT, "getObservacao");

		return grid;
	}

	@Override
	public void run(Object param) throws TextGridException {
		LancamentoDAO dao = new LancamentoDAO();

		List<Lancamento> lancamentoList = dao.listarTodos();

		List<LancamentoForList> lancamentoForList = new ArrayList<LancamentoForList>();

		Map<Conta, Double> saldos = new HashMap<Conta, Double>();

		for (Lancamento lancamento : lancamentoList) {
			LancamentoForList lancamentoForListItem = new LancamentoForList(lancamento);

			Conta contaOrigem = lancamento.getContaOrigem();

			Double saldoOrigem = saldos.get(contaOrigem);

			if (saldoOrigem == null) {
				saldoOrigem = 0.0;
			}

			saldoOrigem -= lancamento.getValor();
			
			saldos.put(contaOrigem, saldoOrigem);

			lancamentoForListItem.setSaldoOrigem(saldoOrigem);
			

			Conta contaDestino = lancamento.getContaDestino();

			Double saldoDestino = saldos.get(contaDestino);

			if (saldoDestino == null) {
				saldoDestino = 0.0;
			}

			saldoDestino += lancamento.getValor();

			saldos.put(contaDestino, saldoDestino);

			lancamentoForListItem.setSaldoDestino(saldoDestino);
			

			lancamentoForList.add(lancamentoForListItem);
		}

		TextGrid grid = createGrid();

		grid.setValues(lancamentoForList);

		grid.show();
		
		System.out.println();
		System.out.println();
		System.out.println("-- Saldos: --");
		System.out.println();
		for (Conta conta : saldos.keySet()) {
			System.out.println("===> " + conta.getNome() + ": " + saldos.get(conta));
		}
	}

}
