package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class ContaSwitches {

	private ContaCommand command;
	
	@CommandLineSwitch(index=1, parser = "ContaCommandParser.parseEnum")
	public void setCommand(ContaCommand value) {
		this.command = value;
	}
	
	public ContaCommand getCommand() {
		return command;
	}
	
}
