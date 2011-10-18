package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import br.zero.switchesparser.ParserException;

public class SwitchesTests {

	private CommandLineLoader loader;
	private CommandLineSwitches switches;
	
	@Before
	public void setup() {
		loader = new CommandLineLoader();
	}
	
	private void doLoad(String[] args) throws ParserException {
		loader.setArgs(args);
		
		loader.load();
		
		switches = loader.getSwitches();
	}
	
	@Test
	public void testNewCommand() throws ParserException {
		doLoad(new String[] {"new ContaOrigem ContaDestino 1,99"});
		
		assertEquals("New Command - Main Command", MainCommand.NEW, switches.getCommand());
		
		NewTransaction newTransaction = switches.getNewTransaction();
		
		assertNotNull("New Command - Sub command line", newTransaction);
		
		assertEquals("New Command - ContaOrigem", "ContaOrigem", newTransaction.getContaOrigem().getNome());
		
		assertEquals("New Command - ContaDestino", "ContaDestino", newTransaction.getContaDestino().getNome());
		
		assertEquals("New Command - Valor", 1.99, newTransaction.getValor(), 0);
	}
	
	@Test
	public void testNewCommandCommented() throws ParserException {
		doLoad(new String[] {"new", "ContaOrigemComentada", "ContaDestinoComentada", "2,50", "\"Comment parameter\""});
		
		assertEquals("New Command - Main Command", MainCommand.NEW, switches.getCommand());
		
		NewTransaction newTransaction = switches.getNewTransaction();
		
		assertNotNull("New Command - Sub command line", newTransaction);
		
		assertEquals("New Command - ContaOrigem", "ContaOrigemComentada", newTransaction.getContaOrigem().getNome());
		
		assertEquals("New Command - ContaDestino", "ContaDestinoComentada", newTransaction.getContaDestino().getNome());
		
		assertEquals("New Command - Valor", 2.50, newTransaction.getValor(), 0);
		
		assertEquals("New Command - Comments", "Comment parameter", newTransaction.getComments());
	}

	@Test
	public void testListCommand() throws ParserException {
		doLoad(new String[] {"list"});
		
		assertEquals("Main Command - list", MainCommand.LIST, switches.getCommand());
	}
	
	@Test
	public void testHelpCommand() throws ParserException {
		doLoad(new String[] {"help"});
		
		assertEquals("Main Command - help", MainCommand.HELP, switches.getCommand());
	}
}
