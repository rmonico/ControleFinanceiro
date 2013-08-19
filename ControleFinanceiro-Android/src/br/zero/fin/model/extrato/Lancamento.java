package br.zero.fin.model.extrato;

import br.zero.fin.model.Conta;

public class Lancamento {
	private int id;
	private Conta banco;
	private String original;
	private Arquivo arquivo;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Conta getBanco() {
		return banco;
	}
	public void setBanco(Conta banco) {
		this.banco = banco;
	}
	public String getOriginal() {
		return original;
	}
	public void setOriginal(String original) {
		this.original = original;
	}
	public Arquivo getArquivo() {
		return arquivo;
	}
	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	
}
