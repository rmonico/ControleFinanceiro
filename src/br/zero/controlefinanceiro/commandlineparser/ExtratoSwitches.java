package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;
import br.zero.commandlineparser.SubCommandLine;

public class ExtratoSwitches {

	private ExtratoCommand command;
	private ExtratoListSwitches listSwitches;

	public ExtratoCommand getCommand() {
		return command;
	}

	@CommandLineSwitch(index = 1, parser = "ExtratoCommandParser.parseComplexEnum", complexParser = true, subCommandLineProperties = {
			@SubCommandLine(value = "LIST", subCommandLineClass = ExtratoListSwitches.class, propertyName = "setListSwitches") })
	public void setCommand(ExtratoCommand command) {
		this.command = command;
	}

	public void setListSwitches(ExtratoListSwitches value) {
		listSwitches = value;
	}

	public ExtratoListSwitches getListSwitches() {
		return listSwitches;
	}

}
