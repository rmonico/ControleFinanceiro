package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class ModeloRemoveSwitches {

	private String nomeModelo;

	public String getNomeModelo() {
		return nomeModelo;
	}

	@CommandLineSwitch(index = 1)
	public void setNomeModelo(String value) {
		nomeModelo = value;
	}

}
