package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class ModeloAddSwitches {

	private String nome;

	public String getNome() {
		return nome;
	}

	@CommandLineSwitch(index = 1)
	public void setNome(String nome) {
		this.nome = nome;
	}
}
