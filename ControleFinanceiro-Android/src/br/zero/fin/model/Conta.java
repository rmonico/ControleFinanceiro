package br.zero.fin.model;

public class Conta {
	private int id;
	private String nome;
	private String observacao;
	private boolean contabilizavel;

	public int getID() {
		return id;
	}

	public void setID(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public boolean getContabilizavel() {
		return contabilizavel;
	}

	public void setContabilizavel(Boolean contabilizavel) {
		this.contabilizavel = contabilizavel;
	}

}
