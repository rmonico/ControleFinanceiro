package br.zero.controlefinanceiro.actions;

import java.util.List;

import br.zero.controlefinanceiro.ControleFinanceiroFormatters;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;
import br.zero.textgrid.TextGrid;
import br.zero.textgrid.TextGridException;
import br.zero.tinycontroller.Action;
import br.zero.tinycontroller.TinyControllerException;

public class LancamentoListAction implements Action {

	@Override
	public void setParams(Object o) {
		// TODO Auto-generated method stub

	}

	private TextGrid createGrid() {
		TextGrid grid = new TextGrid();
		
		grid.getData().setHeaderSeparatorChar('=');
		grid.getData().setTitle("Lista de Lançamentos");
		
		grid.getData().createColumn("id", TextGrid.ID_FORMATTER, "getId");
		grid.getData().createColumn("data", TextGrid.DATE_FORMATTER, "getData");
		grid.getData().createColumn("n", TextGrid.INTEGER_FORMATTER, "getN");
		grid.getData().createColumn("contaorigem", ControleFinanceiroFormatters.CONTA_FORMATTER, "getContaOrigem", " -> ");
		grid.getData().createColumn("contadestino", ControleFinanceiroFormatters.CONTA_FORMATTER, "getContaDestino");
		grid.getData().createColumn("valor", TextGrid.MONEY_FORMATTER, "getValor");
		grid.getData().createColumn("observacao", TextGrid.STRING_FORMATTER, "getObservacao");
		
		return grid;
	}

	@Override
	public void run() throws TinyControllerException {
		LancamentoDAO dao = new LancamentoDAO();

		List<Lancamento> lancamentoList = dao.listarTodos();

		TextGrid grid = createGrid();
		
		grid.setValues(lancamentoList);

		try {
			grid.show();
		} catch (TextGridException e) {
			throw new TinyControllerException(e);
		}
	}

}
