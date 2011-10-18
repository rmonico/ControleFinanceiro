package br.zero.controlefinanceiro.commandlineparser;

import br.zero.controlefinanceiro.model.comum.Conta;

public class NewTransactionCommand {
	
	public Conta getContaOrigem() {
		Conta conta = new Conta();
		conta.setNome("ContaOrigem");
		
		return conta;
	}

	public Conta getContaDestino() {
		Conta conta = new Conta();
		conta.setNome("ContaDestino");
		
		return conta;
	}

	public double getValor() {
		return 1.99;
	}

	public StringBuilder getComments() {
		// TODO Auto-generated method stub
		return null;
	}

}
