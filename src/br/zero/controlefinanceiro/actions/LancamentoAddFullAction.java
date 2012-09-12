package br.zero.controlefinanceiro.actions;

import br.zero.controlefinanceiro.commandlineparser.LancamentoAddFullSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.controlefinanceiro.model.Lancamento;
import br.zero.controlefinanceiro.model.LancamentoDAO;
import br.zero.controlefinanceiro.model.modelo.LancamentoModelo;
import br.zero.controlefinanceiro.model.modelo.LancamentoModeloDAO;
import br.zero.controlefinanceiro.utils.ControleFinanceiroException;
import br.zero.tinycontroller.Action;

public class LancamentoAddFullAction implements Action<LancamentoAddFullSwitches, Object> {
	
	private class LancamentoAddFullException extends ControleFinanceiroException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1702349740543718569L;

		public LancamentoAddFullException(String message) {
			super("lanc addfull: " + message);
		}
		
	}
	
	@Override
	public Object run(LancamentoAddFullSwitches switches) throws LancamentoAddFullException {
		checkParamValid(switches);
		
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
		lancamento.setData(switches.getData());
		lancamento.setContaOrigem(contaOrigem);
		lancamento.setContaDestino(contaDestino);
		lancamento.setValor(switches.getValor());
		
		LancamentoDAO lancamentoDAO = new LancamentoDAO();

		int n = lancamentoDAO.getNextN(switches.getData());
		
		lancamento.setN(n);
		
		
		lancamento.setObservacao(switches.getObservacao());
		
		
		lancamentoDAO.inserir(lancamento);
		
		return null;
	}

	private void checkParamValid(LancamentoAddFullSwitches switches) throws LancamentoAddFullException {
		if (switches.getData() == null) {
			throw new LancamentoAddFullException("Data deve ser informada.");
		}
		
		if (switches.getContaOrigem() ==  null) {
			throw new LancamentoAddFullException("Conta de origem deve ser informada.");
		}
		
		if (switches.getContaDestino() == null) {
			throw new LancamentoAddFullException("Conta de destino deve ser informada.");
		}
		
		if (switches.getValor() == null) {
			throw new LancamentoAddFullException("Valor deve ser informado.");
		}
	}

}
