package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class ExtratoAnalyseSwitches {

	private String nomeBanco;

	@CommandLineSwitch(index = 1)
	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

}
