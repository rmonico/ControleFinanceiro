package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineParser;
import br.zero.commandlineparser.parsers.EnumParser;
import br.zero.switchesparser.IInvalidCommandLineArgument;
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

		parser.getPropertyParsers().put("MainCommandParser", new EnumParser(MainCommand.class));

		parser.parse();
		
		if (!parser.getErrors().isEmpty()) {
			int i = 1;
			for (IInvalidCommandLineArgument error : parser.getErrors()) {
				System.out.println("[" + ++i + "]: " + error.getMessage());
			}
			
			throw new ParserException("Erros encontrados durante o parsing da linha de comando!");
		}
	}

	public CommandLineSwitches getSwitches() {
		return switches;
	}
}
