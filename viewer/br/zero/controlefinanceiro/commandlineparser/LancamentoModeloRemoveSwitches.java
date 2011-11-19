package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class LancamentoModeloRemoveSwitches {
	private int id;

	public int getId() {
		return id;
	}

	@CommandLineSwitch(index = 1, parser = "PrimitiveParsers.parseInteger")
	public void setId(int id) {
		this.id = id;
	}

}
