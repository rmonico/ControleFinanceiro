package br.zero.fin.model.extrato;

import br.zero.fin.model.Conta;

public class ReferenciaExtrato {
	private int id;
	private Conta conta;
	private Conta banco;
	private String referencia;
	private int n;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Conta getBanco() {
		return banco;
	}

	public void setBanco(Conta banco) {
		this.banco = banco;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

}
