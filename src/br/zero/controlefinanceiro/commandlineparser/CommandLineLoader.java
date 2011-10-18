package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineParser;
import br.zero.commandlineparser.parsers.EnumParser;
import br.zero.switchesparser.ParserException;

public class CommandLineLoader {

	private String[] args;
	private CommandLineSwitches switches;

	public void setArgs(String[] value) {
		args = value;
	}

	public void load() throws ParserException {
		CommandLineParser parser = new CommandLineParser();

		parser.setValuesObject(args);

		switches = new CommandLineSwitches();

		parser.setSwitchesObject(switches);

		parser.getPropertyParsers().put("EnumParser", new EnumParser(MainCommand.class));

		parser.parse();
	}

	public CommandLineSwitches getSwitches() {
		return switches;
	}
}
