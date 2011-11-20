package br.zero.controlefinanceiro.actions;

import br.zero.controlefinanceiro.ControleFinanceiroException;
import br.zero.controlefinanceiro.commandlineparser.LancamentoAddSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.tinycontroller.Action;

public class LancamentoAddAction implements Action {
	
	private LancamentoAddSwitches switches;

	@Override
	public void run(Object param) throws ControleFinanceiroException {
		
		if (!(param instanceof LancamentoAddSwitches)) {
			throw new ControleFinanceiroException("Parametro deve ser um LancamentoAddSwitches.");
		}
		
		switches = (LancamentoAddSwitches) param;
		
		ContaDAO contaDAO = new ContaDAO();
		
		Conta contaOrigem = contaDAO.getByNome(switches.getContaOrigem());
		
		if (contaOrigem == null) {
			throw new ControleFinanceiroException("Conta de origem não encontrada (\"" + switches.getContaOrigem() + "\").");
		}
		
		Conta contaDestino = contaDAO.getByNome(switches.getContaDestino());
		
		
	}

}
