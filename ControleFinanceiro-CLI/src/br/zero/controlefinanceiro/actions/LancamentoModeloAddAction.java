package br.zero.controlefinanceiro.actions;

import br.zero.controlefinanceiro.commandlineparser.LancamentoModeloAddSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.controlefinanceiro.model.modelo.LancamentoModelo;
import br.zero.controlefinanceiro.model.modelo.LancamentoModeloDAO;
import br.zero.controlefinanceiro.model.modelo.Modelo;
import br.zero.controlefinanceiro.model.modelo.ModeloDAO;
import br.zero.controlefinanceiro.utils.ControleFinanceiroException;
import br.zero.tinycontroller.Action;

public class LancamentoModeloAddAction implements Action<LancamentoModeloAddSwitches, Object> {

	private class LancamentoModeloAddException extends ControleFinanceiroException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -1702349740543718569L;

		public LancamentoModeloAddException(String message) {
			super("lancmodelo add: " + message);
		}

	}

	@Override
	public Object run(LancamentoModeloAddSwitches switches) throws LancamentoModeloAddException {

		checkParamValid(switches);

		LancamentoModelo lancamentoModelo = new LancamentoModelo();

		Modelo modelo = getModelo(switches.getModelo());

		lancamentoModelo.setModelo(modelo);

		lancamentoModelo.setDiaVencimento(switches.getDiaVencimento());

		Conta contaOrigem = getConta(switches.getContaOrigem());

		lancamentoModelo.setContaOrigem(contaOrigem);

		Conta contaDestino = getConta(switches.getContaDestino());

		lancamentoModelo.setContaDestino(contaDestino);
		
		lancamentoModelo.setValor(switches.getValor());
		
		lancamentoModelo.setObservacao(switches.getObservacao());
		
		
		LancamentoModeloDAO dao = new LancamentoModeloDAO();
		
		dao.inserir(lancamentoModelo);

		System.out.println();

		System.out.println("-- Lançamento Modelo adicionado --");

		System.out.println(lancamentoModelo);
		
		return null;
	}

	private void checkParamValid(LancamentoModeloAddSwitches switches) throws LancamentoModeloAddException {
		if (switches.getModelo() == null) {
			throw new LancamentoModeloAddException("Modelo deve ser informado.");
		}

		if (switches.getDiaVencimento() == null) {
			throw new LancamentoModeloAddException("Dia de Vencimento deve ser informado.");
		}

		if (switches.getContaOrigem() == null) {
			throw new LancamentoModeloAddException("Conta de origem deve ser informada.");
		}

		if (switches.getContaDestino() == null) {
			throw new LancamentoModeloAddException("Conta de destino deve ser informada.");
		}

		if (switches.getValor() == null) {
			throw new LancamentoModeloAddException("Valor deve ser informado.");
		}
	}

	private Modelo getModelo(String nomeModelo) throws LancamentoModeloAddException {
		ModeloDAO dao = new ModeloDAO();

		Modelo modelo = dao.getByNome(nomeModelo);

		if (modelo == null) {
			throw new LancamentoModeloAddException("Modelo \"" + nomeModelo + "\" não encontrado.");
		}

		return modelo;
	}

	private Conta getConta(String nomeConta) throws LancamentoModeloAddException {
		ContaDAO dao = new ContaDAO();

		Conta conta = dao.getByNome(nomeConta);
		
		if (conta == null) {
			throw new LancamentoModeloAddException("Conta \"" + nomeConta + "\" não encontrada.");
		}
		
		return conta;
	}

}
