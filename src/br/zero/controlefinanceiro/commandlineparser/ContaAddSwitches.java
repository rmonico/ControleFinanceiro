package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class ContaAddSwitches {
	
	private String nome;
	private String observacao;

	public String getNome() {
		return nome;
	}

	@CommandLineSwitch(index=1)
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getObservacao() {
		return observacao;
	}

	@CommandLineSwitch(index=2)
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
