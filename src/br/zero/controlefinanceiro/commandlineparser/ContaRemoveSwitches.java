package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class ContaRemoveSwitches {
	private String contaNome;

	public String getContaNome() {
		return contaNome;
	}

	@CommandLineSwitch(index=1)
	public void setContaNome(String value) {
		contaNome = value;
	}
	
	
}
