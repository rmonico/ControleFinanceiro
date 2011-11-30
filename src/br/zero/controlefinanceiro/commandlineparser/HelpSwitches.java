package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class HelpSwitches {
	private Entity entity;

	public Entity getEntity() {
		return entity;
	}

	@CommandLineSwitch(index=1, param="EntityParser.parseEnum")
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	
	
}
