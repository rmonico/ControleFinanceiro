package br.zero.controlefinanceiro.actions;

import java.util.List;

import br.zero.controlefinanceiro.commandlineparser.ContaReferenciaListSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.controlefinanceiro.model.extrato.ReferenciaExtrato;
import br.zero.controlefinanceiro.model.extrato.ReferenciaExtratoDAO;
import br.zero.textgrid.TextGrid;
import br.zero.textgrid.TextGridColumnAlignment;
import br.zero.textgrid.TextGridFormattedColumn;
import br.zero.tinycontroller.Action;

public class ContaReferenciaListAction implements Action<ContaReferenciaListSwitches, Object> {

	public class ContaReferenciaListException extends Exception {

		public ContaReferenciaListException(String message) {
			super("ctaref-ls: " + message);
		}

		/**
		 * 
		 */
		private static final long serialVersionUID = -1922213805618395057L;

	}

	@Override
	public Object run(ContaReferenciaListSwitches switches) throws Exception {
		checkParamValid(switches);
		
		ContaDAO contaDAO = new ContaDAO();
		
		Conta conta = contaDAO.getByNome(switches.getNomeConta());
		
		if (conta == null) {
			throw new ContaReferenciaListException("Conta não encontrada: \"" + switches.getNomeConta());
		}
		
		Conta banco = contaDAO.getByNome(switches.getNomeBanco());
		
		if (banco == null) {
			throw new ContaReferenciaListException("Banco não encontrado: \"" + switches.getNomeBanco());
		}
		
		
		ReferenciaExtratoDAO dao = new ReferenciaExtratoDAO();
		
		List<ReferenciaExtrato> list = dao.listarPorContaEBanco(conta, banco);
		
		TextGrid grid = createGrid();

		grid.setValues(list);

		grid.show();
		
		return null;
	}

	private TextGrid createGrid() {
		TextGrid grid = new TextGrid();

		grid.getData().setHeaderSeparatorChar('=');
		grid.getData().setTitle("Lista de Referências de Extrato");

		TextGridFormattedColumn.createFormattedColumn(grid, "id", TextGridFormattedColumn.ID_FORMATTER, TextGridColumnAlignment.RIGHT, "getId");
		TextGridFormattedColumn.createFormattedColumn(grid, "N", TextGridFormattedColumn.INTEGER_FORMATTER, TextGridColumnAlignment.LEFT, "getN");
		TextGridFormattedColumn.createFormattedColumn(grid, "Regex", TextGridFormattedColumn.STRING_FORMATTER, TextGridColumnAlignment.LEFT, "getReferencia");

		return grid;
	}

	private void checkParamValid(ContaReferenciaListSwitches switches) throws ContaReferenciaListException {
		if (switches.getNomeConta() ==  null) {
			throw new ContaReferenciaListException("Conta deve ser informada.");
		}
		
		if (switches.getNomeBanco() == null) {
			throw new ContaReferenciaListException("Banco deve ser informado.");
		}
	}

}
