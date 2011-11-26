package br.zero.controlefinanceiro.actions;

import br.zero.controlefinanceiro.ControleFinanceiroException;
import br.zero.controlefinanceiro.commandlineparser.LancamentoModeloAddSwitches;
import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.controlefinanceiro.model.modelo.LancamentoModelo;
import br.zero.controlefinanceiro.model.modelo.Modelo;
import br.zero.controlefinanceiro.model.modelo.ModeloDAO;
import br.zero.tinycontroller.Action;

public class LancamentoModeloAddAction implements Action {

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
	public void run(Object param) throws LancamentoModeloAddException {

		LancamentoModeloAddSwitches switches = checkParamValid(param);

		LancamentoModelo lancamentoModelo = new LancamentoModelo();

		Modelo modelo = getModelo(switches.getModelo());

		lancamentoModelo.setModelo(modelo);

		lancamentoModelo.setDiavencimento(switches.getDiaVencimento());

		Conta contaOrigem = getConta(switches.getContaOrigem());

		lancamentoModelo.setContaOrigem(contaOrigem);

		Conta contaDestino = getConta(switches.getContaDestino());

		lancamentoModelo.setContaDestino(contaDestino);
		
		lancamentoModelo.setValor(switches.getValor());
		
		lancamentoModelo.setObservacao(switches.getObservacao());
		

		System.out.println();

		System.out.println("-- Lançamento Modelo adicionado --");

		System.out.println(lancamentoModelo);
	}

	private LancamentoModeloAddSwitches checkParamValid(Object param) throws LancamentoModeloAddException {
		if (!(param instanceof LancamentoModeloAddSwitches)) {
			throw new LancamentoModeloAddException("Parametro deve ser um LancamentoModeloAddSwitches.");
		}

		LancamentoModeloAddSwitches switches = (LancamentoModeloAddSwitches) param;

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

		return switches;
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
