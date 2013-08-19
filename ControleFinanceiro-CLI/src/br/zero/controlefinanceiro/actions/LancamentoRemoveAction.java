package br.zero.controlefinanceiro.actions;

import br.zero.controlefinanceiro.commandlineparser.LancamentoRemoveSwitches;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;
import br.zero.controlefinanceiro.utils.ControleFinanceiroException;
import br.zero.tinycontroller.Action;

public class LancamentoRemoveAction implements Action<LancamentoRemoveSwitches, Object> {

	private class LancamentoRemoveException extends ControleFinanceiroException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1702349740543718569L;

		public LancamentoRemoveException(String message) {
			super("lanc rm: " + message);
		}
		
	}
	
	@Override
	public Object run(LancamentoRemoveSwitches switches) throws LancamentoRemoveException {
		checkParamValid(switches);
		
		LancamentoDAO lancamentoDAO = new LancamentoDAO();
		
		Lancamento lancamento = lancamentoDAO.getById(switches.getId());
		
		lancamentoDAO.excluir(lancamento);
		
		System.out.println();
		
		System.out.println("-- Lançamento removido --");
		
		System.out.println(lancamento);
		
		return null;
	}

	private void checkParamValid(LancamentoRemoveSwitches switches) throws LancamentoRemoveException {
		if (switches.getId() ==  null) {
			throw new LancamentoRemoveException("ID deve ser informado.");
		}
	}

}
