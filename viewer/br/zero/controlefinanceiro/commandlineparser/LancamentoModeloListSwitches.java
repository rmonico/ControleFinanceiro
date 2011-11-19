package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class LancamentoModeloListSwitches {
	private String modelo;

	public String getModelo() {
		return modelo;
	}

	@CommandLineSwitch(index = 1)
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

}
