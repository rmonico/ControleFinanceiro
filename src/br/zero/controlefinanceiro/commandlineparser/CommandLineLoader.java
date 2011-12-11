package br.zero.controlefinanceiro.commandlineparser;

import java.text.SimpleDateFormat;
import java.util.Map;

import br.zero.commandlineparser.CommandLineParser;
import br.zero.commandlineparser.IInvalidCommandLineArgument;
import br.zero.commandlineparser.ParserException;
import br.zero.commandlineparser.parsers.EnumParser;
import br.zero.commandlineparser.parsers.PrimitiveParsers;
import br.zero.commandlineparser.parsers.UtilsParser;

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
		
		parsers.put("EntityParser", new EnumParser(Command.class));
		parsers.put("BackupTypeParser", new EnumParser(BackupType.class));
		parsers.put("LancamentoModeloCommandParser", new EnumParser(LancamentoModeloCommand.class)); 
		parsers.put("ExtratoCommandParser", new EnumParser(ExtratoCommand.class)); 
		parsers.put("PrimitiveParsers", new PrimitiveParsers());
		
		UtilsParser calendarParser = new UtilsParser(new SimpleDateFormat("dd/MMM/yyyy"));
		parsers.put("UtilsParser", calendarParser);
		
		UtilsParser monthDateParser = new UtilsParser(new SimpleDateFormat("MMM/yyyy"));
		parsers.put("MonthDateParser", monthDateParser);

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
