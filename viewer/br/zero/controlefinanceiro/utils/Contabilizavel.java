package br.zero.controlefinanceiro.utils;

import br.zero.controlefinanceiro.model.Conta;

public interface Contabilizavel {
	
	public Double getValor();

	public Conta getContaOrigem();

	public void setSaldoOrigem(Double saldo);

	public Conta getContaDestino();

	public void setSaldoDestino(Double saldo);
}
