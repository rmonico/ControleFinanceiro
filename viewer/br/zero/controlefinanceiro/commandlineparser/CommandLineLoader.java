package br.zero.controlefinanceiro.commandlineparser;

import java.util.Map;

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

		Map<String, Object> parsers = parser.getPropertyParsers();
		
		parsers.put("EntityParser", new EnumParser(Entity.class));
		parsers.put("BackupTypeParser", new EnumParser(BackupType.class));
		parsers.put("ContaCommandParser", new EnumParser(ContaCommand.class));
		
		// TODO Fazer
		parsers.put("ContaParser", null);
		
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
