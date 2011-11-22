package br.zero.controlefinanceiro.actions;

import java.util.List;

import br.zero.controlefinanceiro.ControleFinanceiroFormatters;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;
import br.zero.textgrid.TextGrid;
import br.zero.textgrid.TextGridException;
import br.zero.textgrid.TextGridFormattedColumn;
import br.zero.tinycontroller.Action;

public class LancamentoListAction implements Action {

	private TextGrid createGrid() {
		TextGrid grid = new TextGrid();
		
		grid.getData().setHeaderSeparatorChar('=');
		grid.getData().setTitle("Lista de Lançamentos");
		
		TextGridFormattedColumn.createFormattedColumn(grid, "id", TextGridFormattedColumn.ID_FORMATTER, "getId");
		TextGridFormattedColumn.createFormattedColumn(grid, "data", TextGridFormattedColumn.DATE_FORMATTER, "getData");
		TextGridFormattedColumn.createFormattedColumn(grid, "n", TextGridFormattedColumn.INTEGER_FORMATTER, "getN");
		TextGridFormattedColumn.createFormattedColumn(grid, "contaorigem", ControleFinanceiroFormatters.CONTA_FORMATTER, "getContaOrigem", " -> ");
		TextGridFormattedColumn.createFormattedColumn(grid, "contadestino", ControleFinanceiroFormatters.CONTA_FORMATTER, "getContaDestino");
		TextGridFormattedColumn.createFormattedColumn(grid, "valor", TextGridFormattedColumn.MONEY_FORMATTER, "getValor");
		TextGridFormattedColumn.createFormattedColumn(grid, "observacao", TextGridFormattedColumn.STRING_FORMATTER, "getObservacao");
		
		return grid;
	}

	@Override
	public void run(Object param) throws TextGridException {
		LancamentoDAO dao = new LancamentoDAO();

		List<Lancamento> lancamentoList = dao.listarTodos();

		TextGrid grid = createGrid();
		
		grid.setValues(lancamentoList);

		grid.show();
	}

}
