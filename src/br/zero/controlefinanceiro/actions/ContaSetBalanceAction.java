package br.zero.controlefinanceiro.actions;

import br.zero.controlefinanceiro.commandlineparser.ContaSetBalanceSwitches;
import br.zero.tinycontroller.Action;

public class ContaSetBalanceAction implements Action {

	public class ContaSetBalanceException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7060607761687756128L;

		public ContaSetBalanceException(String message) {
			super("conta setbalance: " + message);
		}
		
	}

	private ContaSetBalanceSwitches switches;
	
	@Override
	public void run(Object param) throws Exception {
		switches = checkParamValid(param);
		
	}

	private ContaSetBalanceSwitches checkParamValid(Object param) throws ContaSetBalanceException {
		if (!(param instanceof ContaSetBalanceSwitches)) {
			throw new ContaSetBalanceException("Parametro deve ser um ContaSetBalanceSwitches.");
		}
		
		ContaSetBalanceSwitches switches = (ContaSetBalanceSwitches) param;
		
		if (switches.getContaNome() ==  null) {
			throw new ContaSetBalanceException("Nome da conta deve ser informado.");
		}
		
		if (switches.getNovoSaldo() ==  null) {
			throw new ContaSetBalanceException("Novo Saldo da conta deve ser informado.");
		}
		
		return switches;
	}

}
