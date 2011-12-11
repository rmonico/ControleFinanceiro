package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class HelpSwitches {
	private Command entity;

	public Command getEntity() {
		return entity;
	}

	@CommandLineSwitch(index=1, param="EntityParser.parseEnum")
	public void setEntity(Command entity) {
		this.entity = entity;
	}
	
	
}
