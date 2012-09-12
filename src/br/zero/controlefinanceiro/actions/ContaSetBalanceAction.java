package br.zero.controlefinanceiro.actions;

import br.zero.controlefinanceiro.commandlineparser.ContaSetBalanceSwitches;
import br.zero.tinycontroller.Action;

public class ContaSetBalanceAction implements Action<ContaSetBalanceSwitches, Object> {

	public class ContaSetBalanceException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 7060607761687756128L;

		public ContaSetBalanceException(String message) {
			super("conta setbalance: " + message);
		}
		
	}

	@Override
	public Object run(ContaSetBalanceSwitches switches) throws Exception {
		checkParamValid(switches);
		
		return null;
	}

	private void checkParamValid(ContaSetBalanceSwitches switches) throws ContaSetBalanceException {
		if (switches.getContaNome() ==  null) {
			throw new ContaSetBalanceException("Nome da conta deve ser informado.");
		}
		
		if (switches.getNovoSaldo() ==  null) {
			throw new ContaSetBalanceException("Novo Saldo da conta deve ser informado.");
		}
	}

}
