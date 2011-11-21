package br.zero.controlefinanceiro.actions;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import br.zero.controlefinanceiro.ControleFinanceiroException;
import br.zero.controlefinanceiro.commandlineparser.LancamentoAddFullSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;
import br.zero.controlefinanceiro.model.modelo.LancamentoModelo;
import br.zero.controlefinanceiro.model.modelo.LancamentoModeloDAO;
import br.zero.tinycontroller.Action;

public class LancamentoAddFullAction implements Action {
	
	private class LancamentoAddFullException extends ControleFinanceiroException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1702349740543718569L;

		public LancamentoAddFullException(String message) {
			super("lanc addfull: " + message);
		}
		
	}
	
	private LancamentoAddFullSwitches switches;

	@Override
	public void run(Object param) throws LancamentoAddFullException {
		
		switches = checkParamValid(param);
		
		ContaDAO contaDAO = new ContaDAO();
		
		Conta contaOrigem = contaDAO.getByNome(switches.getContaOrigem());
		
		if (contaOrigem == null) {
			throw new LancamentoAddFullException("Conta de origem não encontrada (\"" + switches.getContaOrigem() + "\").");
		}
		
		Conta contaDestino = contaDAO.getByNome(switches.getContaDestino());
		
		if (contaDestino == null) {
			throw new LancamentoAddFullException("Conta de destino não encontrada (\"" + switches.getContaDestino() + "\").");
		}
		
		Lancamento lancamento = new Lancamento();
		
		LancamentoModeloDAO lancamentoModeloDAO = new LancamentoModeloDAO();
		
		LancamentoModelo lancamentoModelo = lancamentoModeloDAO.getById(switches.getLancamentoModeloID());
		
		lancamento.setLancamentoModelo(lancamentoModelo);
		
		lancamento.setContaOrigem(contaOrigem);
		lancamento.setContaDestino(contaDestino);
		
		Calendar today = GregorianCalendar.getInstance();
		today.setTime(new Date());
		
		lancamento.setData(today);
		lancamento.setLancamentoModelo(null);
		lancamento.setValor(switches.getValor());
		lancamento.setObservacao(switches.getObservacao());
		
		
		LancamentoDAO lancamentoDAO = new LancamentoDAO();
		
		lancamentoDAO.inserir(lancamento);
	}

	private LancamentoAddFullSwitches checkParamValid(Object param) throws LancamentoAddFullException {
		if (!(param instanceof LancamentoAddFullSwitches)) {
			throw new LancamentoAddFullException("Parametro deve ser um LancamentoAddFullSwitches.");
		}
		
		LancamentoAddFullSwitches switches = (LancamentoAddFullSwitches) param;
		
		if (switches.getContaOrigem() ==  null) {
			throw new LancamentoAddFullException("Conta de origem deve ser informada.");
		}
		
		if (switches.getContaDestino() == null) {
			throw new LancamentoAddFullException("Conta de destino deve ser informada.");
		}
		
		if (switches.getData() == null) {
			throw new LancamentoAddFullException("Data deve ser informada.");
		}
		
		if (switches.getLancamentoModeloID() == null) {
			throw new LancamentoAddFullException("Modelo deve ser informado.");
		}
		
		return switches;
	}

}
