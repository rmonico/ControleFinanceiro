package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class ContaListSwitches {
	
	private String filtroNome;

	@CommandLineSwitch(index=1)
	public void setFiltroNome(String value) {
		this.filtroNome = value;
	}
	
	public String getFiltroNome() {
		return filtroNome;
	}
}
