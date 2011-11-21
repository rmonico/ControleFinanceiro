package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class LancamentoModeloRemoveSwitches {
	private Integer id;

	public Integer getId() {
		return id;
	}

	@CommandLineSwitch(index = 1, parser = "PrimitiveParsers.parseInteger")
	public void setId(Integer id) {
		this.id = id;
	}

}
