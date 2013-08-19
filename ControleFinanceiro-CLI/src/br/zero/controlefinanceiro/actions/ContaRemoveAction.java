package br.zero.controlefinanceiro.actions;

import br.zero.controlefinanceiro.commandlineparser.ContaRemoveSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.controlefinanceiro.utils.ControleFinanceiroException;
import br.zero.tinycontroller.Action;

public class ContaRemoveAction implements Action<ContaRemoveSwitches, Object> {

	private class ContaRemoveException extends ControleFinanceiroException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1702349740543718569L;

		public ContaRemoveException(String message) {
			super("conta rm: " + message);
		}
		
	}
	
	@Override
	public Object run(ContaRemoveSwitches switches) throws ContaRemoveException {
		checkParamValid(switches);
		
		ContaDAO contaDAO = new ContaDAO();
		
		Conta conta = contaDAO.getByNome(switches.getContaNome());
		
		if (conta == null) {
			throw new ContaRemoveException("Conta de origem não encontrada (\"" + switches.getContaNome() + "\").");
		}
		
		contaDAO.excluir(conta);
		
		System.out.println();
		
		System.out.println("-- Conta removida --");
		
		System.out.println(conta);
		
		return null;
	}

	private void checkParamValid(ContaRemoveSwitches switches) throws ContaRemoveException {
		if (switches.getContaNome() ==  null) {
			throw new ContaRemoveException("Nome da conta deve ser informado.");
		}
	}

}
