package br.zero.controlefinanceiro.actions;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.zero.controlefinanceiro.ControleFinanceiroException;
import br.zero.controlefinanceiro.commandlineparser.LancamentoAddSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;
import br.zero.tinycontroller.Action;

public class LancamentoAddAction implements Action {
	
	private class LancamentoAddException extends ControleFinanceiroException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1702349740543718569L;

		public LancamentoAddException(String message) {
			super("lanc add: " + message);
		}
		
	}
	
	private LancamentoAddSwitches switches;

	@Override
	public void run(Object param) throws LancamentoAddException {
		
		switches = checkParamValid(param);
		
		ContaDAO contaDAO = new ContaDAO();
		
		Conta contaOrigem = contaDAO.getByNome(switches.getContaOrigem());
		
		if (contaOrigem == null) {
			throw new LancamentoAddException("Conta de origem não encontrada (\"" + switches.getContaOrigem() + "\").");
		}
		
		Conta contaDestino = contaDAO.getByNome(switches.getContaDestino());
		
		if (contaDestino == null) {
			throw new LancamentoAddException("Conta de destino não encontrada (\"" + switches.getContaDestino() + "\").");
		}
		
		Lancamento lancamento = new Lancamento();
		
		lancamento.setContaOrigem(contaOrigem);
		lancamento.setContaDestino(contaDestino);
		
		Calendar today = GregorianCalendar.getInstance();
		today.setTime(new Date());
		
		lancamento.setData(today);
		lancamento.setLancamentoModelo(null);
		lancamento.setValor(switches.getValor());
		
		LancamentoDAO lancamentoDAO = new LancamentoDAO();

		int n = lancamentoDAO.getNextN(today);
		
		lancamento.setN(n);
		
		lancamento.setObservacao(switches.getObservacao());
		
		
		
		lancamentoDAO.inserir(lancamento);
	}

	private LancamentoAddSwitches checkParamValid(Object param) throws LancamentoAddException {
		if (!(param instanceof LancamentoAddSwitches)) {
			throw new LancamentoAddException("Parametro deve ser um LancamentoAddSwitches.");
		}
		
		LancamentoAddSwitches switches = (LancamentoAddSwitches) param;
		
		if (switches.getContaOrigem() ==  null) {
			throw new LancamentoAddException("Conta de origem deve ser informada.");
		}
		
		if (switches.getContaDestino() == null) {
			throw new LancamentoAddException("Conta de destino deve ser informada.");
		}
		
		return switches;
	}

}
