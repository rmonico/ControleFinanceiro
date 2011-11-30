package br.zero.controlefinanceiro.actions;

import br.zero.controlefinanceiro.commandlineparser.ContaRemoveSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.controlefinanceiro.utils.ControleFinanceiroException;
import br.zero.tinycontroller.Action;

public class ContaRemoveAction implements Action {

	private class ContaRemoveException extends ControleFinanceiroException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1702349740543718569L;

		public ContaRemoveException(String message) {
			super("conta rm: " + message);
		}
		
	}
	
	private ContaRemoveSwitches switches;

	@Override
	public void run(Object param) throws ContaRemoveException {
		
		switches = checkParamValid(param);
		
		ContaDAO contaDAO = new ContaDAO();
		
		Conta conta = contaDAO.getByNome(switches.getContaNome());
		
		if (conta == null) {
			throw new ContaRemoveException("Conta de origem não encontrada (\"" + switches.getContaNome() + "\").");
		}
		
		contaDAO.excluir(conta);
		
		System.out.println();
		
		System.out.println("-- Conta removida --");
		
		System.out.println(conta);
	}

	private ContaRemoveSwitches checkParamValid(Object param) throws ContaRemoveException {
		if (!(param instanceof ContaRemoveSwitches)) {
			throw new ContaRemoveException("Parametro deve ser um ContaRemoveSwitches.");
		}
		
		ContaRemoveSwitches switches = (ContaRemoveSwitches) param;
		
		if (switches.getContaNome() ==  null) {
			throw new ContaRemoveException("Nome da conta deve ser informado.");
		}
		
		return switches;
	}

}
