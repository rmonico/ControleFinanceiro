package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;
import br.zero.commandlineparser.SubCommandLine;

public class ContaSwitches {

	private ContaCommand command;
	private ContaAddSwitches addSwitches = new ContaAddSwitches();
	private ContaRemoveSwitches removeSwitches = new ContaRemoveSwitches();
	private ContaListSwitches listSwitches = new ContaListSwitches();
	
	@CommandLineSwitch(index=1, parser = "ContaCommandParser.parseComplexEnum", complexParser = true, subCommandLineProperties = {
			@SubCommandLine(value = "ADD", subCommandLineClass = ContaAddSwitches.class, propertyName = "setAddSwitches"),
			@SubCommandLine(value = "REMOVE", subCommandLineClass = ContaRemoveSwitches.class, propertyName = "setRemoveSwitches"),
			@SubCommandLine(value = "LIST", subCommandLineClass = ContaListSwitches.class, propertyName = "setListSwitches"),
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
	
	public void setListSwitches(ContaListSwitches value) {
		listSwitches = value;
	}
	
	public ContaListSwitches getListSwitches() {
		return listSwitches;
	}
}
