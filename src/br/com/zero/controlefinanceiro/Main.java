package br.com.zero.controlefinanceiro;

import org.zero.commandlineparser.CommandLineParser;
import org.zero.commandlineparser.CommandLineParserException;

import br.com.zero.controlefinanceiro.commandlineparser.Switches;

public class Main {
	
	private String[] args;
	private Object switches;

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Main main = new Main();
		
		main.setArgs(args);
		
		main.run();
	}

	private void setArgs(String[] args) {
		this.args = args;
	}

	private void run() throws Exception {
		parseCommandLine();
		
		
	}

	private void parseCommandLine() throws CommandLineParserException {
		CommandLineParser parser = new CommandLineParser();
		
		parser.setCommandLine(args);
		
		switches = new Switches();
		
		parser.setSwitchesObject(switches);
		
		parser.parse();
	}

}
