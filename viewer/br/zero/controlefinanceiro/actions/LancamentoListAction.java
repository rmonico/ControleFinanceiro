package br.zero.controlefinanceiro.actions;

import java.util.List;
import java.util.Map;

import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;
import br.zero.controlefinanceiro.utils.Contabilizador;
import br.zero.controlefinanceiro.utils.ControleFinanceiroFormatters;
import br.zero.controlefinanceiro.utils.LancamentoContabilizavel;
import br.zero.controlefinanceiro.utils.Packer;
import br.zero.textgrid.TextGrid;
import br.zero.textgrid.TextGridColumnAlignment;
import br.zero.textgrid.TextGridException;
import br.zero.textgrid.TextGridFormattedColumn;
import br.zero.tinycontroller.Action;

public class LancamentoListAction implements Action {

	private TextGrid createGrid() {
		TextGrid grid = new TextGrid();

		grid.getData().setHeaderSeparatorChar('=');
		grid.getData().setTitle("Lista de Lançamentos");

		TextGridFormattedColumn.createFormattedColumn(grid, "id", TextGridFormattedColumn.ID_FORMATTER, TextGridColumnAlignment.RIGHT, "getId");
		TextGridFormattedColumn.createFormattedColumn(grid, "Data", TextGridFormattedColumn.DATE_FORMATTER, TextGridColumnAlignment.CENTER, "getData");
		TextGridFormattedColumn.createFormattedColumn(grid, "N", TextGridFormattedColumn.INTEGER_FORMATTER, TextGridColumnAlignment.LEFT, "getN");
		TextGridFormattedColumn.createFormattedColumn(grid, "Origem", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.RIGHT, "getContaOrigem");
		TextGridFormattedColumn.createFormattedColumn(grid, "Saldo", TextGridFormattedColumn.MONEY_FORMATTER, TextGridColumnAlignment.RIGHT, "getSaldoOrigem");
		TextGridFormattedColumn.createFormattedColumn(grid, "Destino", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.LEFT, "getContaDestino");
		TextGridFormattedColumn.createFormattedColumn(grid, "Saldo", TextGridFormattedColumn.MONEY_FORMATTER, TextGridColumnAlignment.RIGHT, "getSaldoDestino");
		TextGridFormattedColumn.createFormattedColumn(grid, "Valor", TextGridFormattedColumn.MONEY_FORMATTER, TextGridColumnAlignment.RIGHT, "getValor");
		TextGridFormattedColumn.createFormattedColumn(grid, "Observação", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.LEFT, "getObservacao");

		return grid;
	}

	@Override
	public void run(Object param) throws TextGridException {
		LancamentoDAO dao = new LancamentoDAO();

		List<Lancamento> lancamentoList = dao.listarTodos();

		Contabilizador contabilizador = new Contabilizador();

		Packer<LancamentoContabilizavel, Lancamento> packager = Packer.LANCAMENTO_LANCAMENTOCONTABILIZAVEL_PACKAGER;
		
		List<LancamentoContabilizavel> lancamentoContabilizavelList = contabilizador.packageList(lancamentoList, packager);
		
		contabilizador.setList(lancamentoContabilizavelList);

		contabilizador.contabilizar();

		Map<Conta, Double> saldos = contabilizador.getSaldosPorConta();

		TextGrid grid = createGrid();

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

}
