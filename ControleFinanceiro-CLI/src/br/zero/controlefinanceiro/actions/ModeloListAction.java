package br.zero.controlefinanceiro.actions;

import java.util.List;

import br.zero.controlefinanceiro.model.modelo.Modelo;
import br.zero.controlefinanceiro.model.modelo.ModeloDAO;
import br.zero.textgrid.TextGrid;
import br.zero.textgrid.TextGridColumnAlignment;
import br.zero.textgrid.TextGridException;
import br.zero.textgrid.TextGridFormattedColumn;
import br.zero.tinycontroller.Action;

public class ModeloListAction implements Action<Object, Object> {

	private TextGrid createGrid() {
		TextGrid grid = new TextGrid();

		grid.getData().setHeaderSeparatorChar('=');
		grid.getData().setTitle("Lista de Modelos");

		TextGridFormattedColumn.createFormattedColumn(grid, "id", TextGridFormattedColumn.ID_FORMATTER, TextGridColumnAlignment.RIGHT, "getId");
		TextGridFormattedColumn.createFormattedColumn(grid, "Nome", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.LEFT, "getNome");
		TextGridFormattedColumn.createFormattedColumn(grid, "Observação", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.LEFT, "getObservacao");

		return grid;
	}

	@Override
	public Object run(Object param) throws TextGridException {
		ModeloDAO modeloDAO = new ModeloDAO();
		
		List<Modelo> listModelos = modeloDAO.listarTodos();
		
		TextGrid grid = createGrid();

		grid.setValues(listModelos);

		grid.show();
		
		return null;
	}

}
