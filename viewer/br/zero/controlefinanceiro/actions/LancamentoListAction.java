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
		
		grid.getData().createFormattedColumn("id", TextGridFormattedColumn.ID_FORMATTER, "getId");
		grid.getData().createFormattedColumn("data", TextGridFormattedColumn.DATE_FORMATTER, "getData");
		grid.getData().createFormattedColumn("n", TextGridFormattedColumn.INTEGER_FORMATTER, "getN");
		grid.getData().createFormattedColumn("contaorigem", ControleFinanceiroFormatters.CONTA_FORMATTER, "getContaOrigem", " -> ");
		grid.getData().createFormattedColumn("contadestino", ControleFinanceiroFormatters.CONTA_FORMATTER, "getContaDestino");
		grid.getData().createFormattedColumn("valor", TextGridFormattedColumn.MONEY_FORMATTER, "getValor");
		grid.getData().createFormattedColumn("observacao", TextGridFormattedColumn.STRING_FORMATTER, "getObservacao");
		
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
