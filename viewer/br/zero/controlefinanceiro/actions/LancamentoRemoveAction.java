package br.zero.controlefinanceiro.actions;

import br.zero.controlefinanceiro.ControleFinanceiroException;
import br.zero.controlefinanceiro.commandlineparser.LancamentoRemoveSwitches;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;
import br.zero.tinycontroller.Action;

public class LancamentoRemoveAction implements Action {

	private class LancamentoRemoveException extends ControleFinanceiroException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1702349740543718569L;

		public LancamentoRemoveException(String message) {
			super("lanc rm: " + message);
		}
		
	}
	
	private LancamentoRemoveSwitches switches;

	@Override
	public void run(Object param) throws LancamentoRemoveException {
		
		switches = checkParamValid(param);
		
		LancamentoDAO lancamentoDAO = new LancamentoDAO();
		
		Lancamento lancamento = lancamentoDAO.getById(switches.getId());
		
		lancamentoDAO.excluir(lancamento);
		
		System.out.println();
		
		System.out.println("-- Lançamento removido --");
		
		System.out.println(lancamento);
	}

	private LancamentoRemoveSwitches checkParamValid(Object param) throws LancamentoRemoveException {
		if (!(param instanceof LancamentoRemoveSwitches)) {
			throw new LancamentoRemoveException("Parametro deve ser um LancamentoRemoveSwitches.");
		}
		
		LancamentoRemoveSwitches switches = (LancamentoRemoveSwitches) param;
		
		if (switches.getId() ==  null) {
			throw new LancamentoRemoveException("ID deve ser informado.");
		}
		
		return switches;
	}

}
