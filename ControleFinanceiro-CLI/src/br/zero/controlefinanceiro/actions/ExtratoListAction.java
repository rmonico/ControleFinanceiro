package br.zero.controlefinanceiro.actions;

import java.util.List;

import br.zero.controlefinanceiro.commandlineparser.ExtratoListSwitches;
import br.zero.controlefinanceiro.model.extrato.ExtratoLancamento;
import br.zero.controlefinanceiro.model.extrato.ExtratoLancamentoDAO;
import br.zero.controlefinanceiro.utils.ControleFinanceiroFormatters;
import br.zero.textgrid.TextGrid;
import br.zero.textgrid.TextGridColumnAlignment;
import br.zero.textgrid.TextGridException;
import br.zero.textgrid.TextGridFormattedColumn;
import br.zero.tinycontroller.Action;

public class ExtratoListAction implements Action<ExtratoListSwitches, Object> {

	public class ExtratoListException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 3970188812383867840L;

		public ExtratoListException(String message) {
			super(message);
		}

	}

	private TextGrid createGrid(ExtratoListSwitches switches) {
		TextGrid grid = new TextGrid();

		grid.getData().setHeaderSeparatorChar('=');
		grid.getData().setTitle("ExtratoLancamento \"" + switches.getNomeConta() + "\"");

		TextGridFormattedColumn.createFormattedColumn(grid, "id", TextGridFormattedColumn.ID_FORMATTER, TextGridColumnAlignment.RIGHT, "getId");
		TextGridFormattedColumn.createFormattedColumn(grid, "Conta", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.LEFT, "getBanco");
		TextGridFormattedColumn.createFormattedColumn(grid, "Original", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.LEFT, "getOriginal");

		return grid;
	}

	@Override
	public Object run(ExtratoListSwitches switches) throws ExtratoListException, TextGridException {
		checkParamValid(switches);
		
		ExtratoLancamentoDAO dao = new ExtratoLancamentoDAO();

		List<ExtratoLancamento> extratoList = dao.listarPorConta(switches.getNomeConta());

		TextGrid grid = createGrid(switches);

		grid.setValues(extratoList);

		grid.show();
		
		return null;
	}

	private void checkParamValid(ExtratoListSwitches switches) throws ExtratoListException {
		if (switches.getNomeConta() ==  null) {
			throw new ExtratoListException("Nome da conta deve ser informada.");
		}
	}
}
