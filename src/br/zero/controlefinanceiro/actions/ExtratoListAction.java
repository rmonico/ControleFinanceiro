package br.zero.controlefinanceiro.actions;

import java.util.List;

import br.zero.controlefinanceiro.commandlineparser.ExtratoListSwitches;
import br.zero.controlefinanceiro.model.Extrato;
import br.zero.controlefinanceiro.model.ExtratoDAO;
import br.zero.controlefinanceiro.utils.ControleFinanceiroFormatters;
import br.zero.textgrid.TextGrid;
import br.zero.textgrid.TextGridColumnAlignment;
import br.zero.textgrid.TextGridException;
import br.zero.textgrid.TextGridFormattedColumn;
import br.zero.tinycontroller.Action;

public class ExtratoListAction implements Action {

	private ExtratoListSwitches switches;

	public class ExtratoListException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3970188812383867840L;

		public ExtratoListException(String message) {
			super(message);
		}

	}

	private TextGrid createGrid() {
		TextGrid grid = new TextGrid();

		grid.getData().setHeaderSeparatorChar('=');
		grid.getData().setTitle("Extrato \"" + switches.getNomeConta() + "\"");

		TextGridFormattedColumn.createFormattedColumn(grid, "id", TextGridFormattedColumn.ID_FORMATTER, TextGridColumnAlignment.RIGHT, "getId");
		TextGridFormattedColumn.createFormattedColumn(grid, "Conta", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.LEFT, "getBanco");
		TextGridFormattedColumn.createFormattedColumn(grid, "Data", TextGridFormattedColumn.DATE_FORMATTER, TextGridColumnAlignment.CENTER, "getData");
		TextGridFormattedColumn.createFormattedColumn(grid, "Original", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.LEFT, "getOriginal");

		return grid;
	}

	@Override
	public void run(Object param) throws ExtratoListException, TextGridException {
		switches = checkParamValid(param);
		
		ExtratoDAO dao = new ExtratoDAO();

		List<Extrato> extratoList = dao.listarPorConta(switches.getNomeConta());

		TextGrid grid = createGrid();

		grid.setValues(extratoList);

		grid.show();

	}

	private ExtratoListSwitches checkParamValid(Object param) throws ExtratoListException {
		if (!(param instanceof ExtratoListSwitches)) {
			throw new ExtratoListException("Parametro deve ser um ExtratoListSwitches.");
		}
		
		ExtratoListSwitches switches = (ExtratoListSwitches) param;
		
		if (switches.getNomeConta() ==  null) {
			throw new ExtratoListException("Nome da conta deve ser informada.");
		}
		
		return switches;
	}
}