package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;
import br.zero.commandlineparser.SubCommandLine;

public class ContaSwitches {

	private ContaCommand command;
	private ContaAddSwitches addSwitches;
	
	@CommandLineSwitch(index=1, parser = "ContaCommandParser.parseEnum", complexParser = true, subCommandLineProperties = {
			@SubCommandLine(value = "ADD", subCommandLineClass = ContaAddSwitches.class, propertyName = "setAddSwitches"),
	})
	public void setCommand(ContaCommand value) {
		this.command = value;
	}
	
	public ContaCommand getCommand() {
		return command;
	}
	
	public void setAddSwitches(ContaAddSwitches value) {
		addSwitches = value;
	}
	
	public ContaAddSwitches getAddSwitches() {
		return addSwitches;
	}
}
