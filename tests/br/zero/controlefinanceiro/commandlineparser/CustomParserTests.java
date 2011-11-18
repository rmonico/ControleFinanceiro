package br.zero.controlefinanceiro.commandlineparser;

import org.junit.Before;

import br.zero.switchesparser.ParserException;


public class CustomParserTests {

	private CommandLineLoader loader;
	protected CommandLineSwitches switches;
	
	@Before
	public void setup() {
		loader = new CommandLineLoader();
	}
	
	protected void doLoad(String[] args) throws ParserException {
		loader.setArgs(args);
		
		loader.load();
		
		switches = loader.getSwitches();
	}
	
}
