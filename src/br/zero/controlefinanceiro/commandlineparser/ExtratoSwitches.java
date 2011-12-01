package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;
import br.zero.commandlineparser.SubCommandLine;

// TODO: Fazer JUnit's!!!
public class ExtratoSwitches {

	private ExtratoCommand command;
	private ExtratoListSwitches listSwitches;
	private ExtratoImportSwitches importSwitches;

	public ExtratoCommand getCommand() {
		return command;
	}

	@CommandLineSwitch(index = 1, parser = "ExtratoCommandParser.parseComplexEnum", complexParser = true, subCommandLineProperties = {
			@SubCommandLine(value = "LIST", subCommandLineClass = ExtratoListSwitches.class, propertyName = "setListSwitches"),
			@SubCommandLine(value = "IMPORT", subCommandLineClass = ExtratoImportSwitches.class, propertyName = "setImportSwitches") })
	public void setCommand(ExtratoCommand command) {
		this.command = command;
	}

	public void setListSwitches(ExtratoListSwitches value) {
		listSwitches = value;
	}

	public ExtratoListSwitches getListSwitches() {
		return listSwitches;
	}

	public void setImportSwitches(ExtratoImportSwitches value) {
		importSwitches = value;
	}

	public ExtratoImportSwitches getImportSwitches() {
		return importSwitches;
	}

}
