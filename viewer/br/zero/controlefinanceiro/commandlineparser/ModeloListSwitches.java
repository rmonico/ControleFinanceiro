package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class ModeloListSwitches {

	private String nome;

	public String getFiltroNome() {
		return nome;
	}

	@CommandLineSwitch(index = 1)
	public void setFiltroNome(String value) {
		nome = value;
	}

}
