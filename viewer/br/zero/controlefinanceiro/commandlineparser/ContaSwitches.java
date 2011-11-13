package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;
import br.zero.commandlineparser.SubCommandLine;

public class ContaSwitches {

	private ContaCommand command;
	private ContaAddSwitches addSwitches;
	private ContaRemoveSwitches removeSwitches;
	
	@CommandLineSwitch(index=1, parser = "ContaCommandParser.parseEnum", complexParser = true, subCommandLineProperties = {
			@SubCommandLine(value = "ADD", subCommandLineClass = ContaAddSwitches.class, propertyName = "setAddSwitches"),
			@SubCommandLine(value = "RM", subCommandLineClass = ContaRemoveSwitches.class, propertyName = "setRemoveSwitches"),
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
	
	public void setRemoveSwitches(ContaRemoveSwitches value) {
		removeSwitches = value;
	}
	
	public ContaRemoveSwitches getRemoveSwitches() {
		return removeSwitches;
	}
}
