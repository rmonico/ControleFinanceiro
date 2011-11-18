package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class LancamentoRemoveSwitches {
	private int id;

	public int getId() {
		return id;
	}

	@CommandLineSwitch(index = 1)
	public void setId(int id) {
		this.id = id;
	}

}
