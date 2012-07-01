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
	
	private ContaAddSwitches switches;

	@Override
	public Object run(ContaAddSwitches param) throws ContaAddException {
		
		switches = checkParamValid(param);
		
		ContaDAO contaDAO = new ContaDAO();
		
		Conta conta = new Conta();
		
		conta.setNome(switches.getNome());
		conta.setContabilizavel(switches.getContabilizavel());
		conta.setObservacao(switches.getObservacao());
		
		contaDAO.inserir(conta);
		
		return null;
		
//		System.out.println();
//		
//		System.out.println("-- Conta adicionada --");
//		
//		System.out.println(conta);
	}

	private ContaAddSwitches checkParamValid(Object param) throws ContaAddException {
		if (!(param instanceof ContaAddSwitches)) {
			throw new ContaAddException("Parametro deve ser um ContaAddSwitches.");
		}
		
		ContaAddSwitches switches = (ContaAddSwitches) param;
		
		if (switches.getNome() ==  null) {
			throw new ContaAddException("Nome da conta deve ser informado.");
		}
		
		return switches;
	}

}
