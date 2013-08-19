package br.zero.controlefinanceiro.actions;

import br.zero.controlefinanceiro.commandlineparser.ContaAddSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.controlefinanceiro.utils.ControleFinanceiroException;
import br.zero.tinycontroller.Action;


public class ContaAddAction implements Action<ContaAddSwitches, Object> {

	private class ContaAddException extends ControleFinanceiroException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1702349740543718569L;

		public ContaAddException(String message) {
			super("conta add: " + message);
		}
		
	}
	
	@Override
	public Object run(ContaAddSwitches switches) throws ContaAddException {
		checkParamValid(switches);
		
		ContaDAO contaDAO = new ContaDAO();
		
		Conta conta = new Conta();
		
		conta.setNome(switches.getNome());
		conta.setContabilizavel(switches.getContabilizavel());
		conta.setObservacao(switches.getObservacao());
		
		contaDAO.inserir(conta);
		
		System.out.println();
		
		System.out.println("-- Conta adicionada --");
		
		System.out.println(conta);
		
		return null;
	}

	private void checkParamValid(ContaAddSwitches switches) throws ContaAddException {
		if (switches.getNome() ==  null) {
			throw new ContaAddException("Nome da conta deve ser informado.");
		}
	}

}
