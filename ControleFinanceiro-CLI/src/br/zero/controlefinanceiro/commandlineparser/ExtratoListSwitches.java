package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class ExtratoListSwitches {
	private String nomeConta;

	public String getNomeConta() {
		return nomeConta;
	}

	@CommandLineSwitch(index = 1)
	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}

}
