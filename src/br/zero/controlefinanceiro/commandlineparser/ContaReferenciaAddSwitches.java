package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class ContaReferenciaAddSwitches extends ContaReferenciaListSwitches {

	private String regex;

	public String getRegex() {
		return regex;
	}

	@CommandLineSwitch(param = {"-regex"})
	public void setRegex(String regex) {
		this.regex = regex;
	}

}
