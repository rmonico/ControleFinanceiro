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
		doLoad(new String[] {"new", "ContaOrigem", "ContaDestino", "1,99"});
		
		assertEquals("New Command - Main Command", MainCommand.NEW, switches.getMainCommand());
		
		NewTransactionCommand newTransaction = switches.getNewTransactionCommand();
		
		assertNotNull("New Command - Sub command line", newTransaction);
		
		assertEquals("New Command - ContaOrigem", "ContaOrigem", newTransaction.getContaOrigem().getNome());
		
		assertEquals("New Command - ContaDestino", "ContaDestino", newTransaction.getContaDestino().getNome());
		
		assertEquals("New Command - Valor", 1.99, newTransaction.getValor(), 0);
	}
	
	@Test
	public void testNewCommandCommented() throws ParserException {
		doLoad(new String[] {"new", "ContaOrigemComentada", "ContaDestinoComentada", "2,50", "\"Comment parameter\""});
		
		assertEquals("New Command - Main Command", MainCommand.NEW, switches.getMainCommand());
		
		NewTransactionCommand newTransaction = switches.getNewTransactionCommand();
		
		assertNotNull("New Command - Sub command line", newTransaction);
		
		assertEquals("New Command - ContaOrigem", "ContaOrigemComentada", newTransaction.getContaOrigem().getNome());
		
		assertEquals("New Command - ContaDestino", "ContaDestinoComentada", newTransaction.getContaDestino().getNome());
		
		assertEquals("New Command - Valor", 2.50, newTransaction.getValor(), 0);
		
		assertEquals("New Command - Comments", "Comment parameter", newTransaction.getComments());
	}

	@Test
	public void testListCommand() throws ParserException {
		doLoad(new String[] {"list"});
		
		assertEquals("List Command - Main Command", MainCommand.LIST, switches.getMainCommand());
		
		ListTransactionsCommand listTransactions = switches.getListTransactionsCommand();

		assertNotNull("New Command - Sub command line", listTransactions);
	}
	
	@Test
	public void testHelpCommand() throws ParserException {
		testHelpForNewCommand();

		testHelpForListCommand();
	}

	private void testHelpForNewCommand() throws ParserException {
		doLoad(new String[] {"help", "new"});
		
		assertEquals("ShowHelpCommand Command - Main Command", MainCommand.HELP, switches.getMainCommand());
		
		ShowHelpCommand help = switches.getShowHelpCommand();
		
		assertNotNull("ShowHelpCommand Command - Sub command line", help);
		
		assertEquals("ShowHelpCommand Command - New Command", MainCommand.NEW, help.getCommand());	
	}
	
	private void testHelpForListCommand() throws ParserException {
		doLoad(new String[] {"help", "list"});
		
		assertEquals("ShowHelpCommand Command - Main Command", MainCommand.HELP, switches.getMainCommand());
		
		ShowHelpCommand help = switches.getShowHelpCommand();
		
		assertNotNull("ShowHelpCommand Command - Sub command line", help);
		
		assertEquals("ShowHelpCommand Command - List Command", MainCommand.LIST, help.getCommand());			
	}


}
