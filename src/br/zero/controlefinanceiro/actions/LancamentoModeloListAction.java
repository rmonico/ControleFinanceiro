package br.zero.controlefinanceiro.actions;

import java.util.List;

import br.zero.controlefinanceiro.commandlineparser.LancamentoModeloListSwitches;
import br.zero.controlefinanceiro.model.modelo.LancamentoModelo;
import br.zero.controlefinanceiro.model.modelo.LancamentoModeloDAO;
import br.zero.controlefinanceiro.utils.ControleFinanceiroException;
import br.zero.controlefinanceiro.utils.ControleFinanceiroFormatters;
import br.zero.textgrid.TextGrid;
import br.zero.textgrid.TextGridColumnAlignment;
import br.zero.textgrid.TextGridException;
import br.zero.textgrid.TextGridFormattedColumn;
import br.zero.tinycontroller.Action;

public class LancamentoModeloListAction implements Action {

	private class LancamentoModeloListException extends ControleFinanceiroException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1702349740543718569L;

		public LancamentoModeloListException(String message) {
			super("lancmodelo ls: " + message);
		}

	}

	private TextGrid createGrid() {
		TextGrid grid = new TextGrid();

		grid.getData().setHeaderSeparatorChar('=');
		grid.getData().setTitle("Lista de Lançamentos");

		TextGridFormattedColumn.createFormattedColumn(grid, "id", TextGridFormattedColumn.ID_FORMATTER, TextGridColumnAlignment.RIGHT, "getId");
		TextGridFormattedColumn.createFormattedColumn(grid, "Modelo", ControleFinanceiroFormatters.MODELO_FORMATTER, TextGridColumnAlignment.LEFT, "getModelo");
		TextGridFormattedColumn.createFormattedColumn(grid, "Vencimento", TextGridFormattedColumn.INTEGER_FORMATTER, TextGridColumnAlignment.LEFT, "getDiaVencimento");
		TextGridFormattedColumn.createFormattedColumn(grid, "Conta Origem", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.RIGHT, "getContaOrigem", " -> ");
		TextGridFormattedColumn.createFormattedColumn(grid, "Conta Destino", ControleFinanceiroFormatters.CONTA_FORMATTER, TextGridColumnAlignment.LEFT, "getContaDestino");
		TextGridFormattedColumn.createFormattedColumn(grid, "Valor", TextGridFormattedColumn.MONEY_FORMATTER, TextGridColumnAlignment.RIGHT, "getValor");
		TextGridFormattedColumn.createFormattedColumn(grid, "Observação", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.LEFT, "getObservacao");

		return grid;
	}

	@Override
	public void run(Object param) throws TextGridException, LancamentoModeloListException {

		LancamentoModeloListSwitches switches = checkParamValid(param);
		
		List<LancamentoModelo> lancamentoModeloList = getLancamentosPorModelo(switches);

		TextGrid grid = createGrid();

		grid.setValues(lancamentoModeloList);
		
		grid.show();
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
