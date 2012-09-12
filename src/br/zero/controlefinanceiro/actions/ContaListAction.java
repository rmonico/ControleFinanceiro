package br.zero.controlefinanceiro.actions;

import java.util.List;

import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.textgrid.TextGrid;
import br.zero.textgrid.TextGridColumnAlignment;
import br.zero.textgrid.TextGridException;
import br.zero.textgrid.TextGridFormattedColumn;
import br.zero.tinycontroller.Action;

public class ContaListAction implements Action<Object, Object> {

	private TextGrid createGrid() {
		TextGrid grid = new TextGrid();

		grid.getData().setHeaderSeparatorChar('=');
		grid.getData().setTitle("Lista de Lançamentos");

		TextGridFormattedColumn.createFormattedColumn(grid, "id", TextGridFormattedColumn.ID_FORMATTER, TextGridColumnAlignment.RIGHT, "getId");
		TextGridFormattedColumn.createFormattedColumn(grid, "Nome", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.LEFT, "getNome");
		TextGridFormattedColumn.createFormattedColumn(grid, "Contab.", TextGridFormattedColumn.BOOLEAN_FORMATTER, TextGridColumnAlignment.CENTER, "getContabilizavel");
		TextGridFormattedColumn.createFormattedColumn(grid, "Observação", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.LEFT, "getObservacao");

		return grid;
	}

	@Override
	public Object run(Object param) throws TextGridException {
		ContaDAO dao = new ContaDAO();

		List<Conta> contaList = dao.listarTodos();

		TextGrid grid = createGrid();

		grid.setValues(contaList);

		grid.show();
		
		return null;
	}
}
