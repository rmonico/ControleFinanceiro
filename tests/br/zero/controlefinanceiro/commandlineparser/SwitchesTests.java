package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
	public void doBackupTest() throws ParserException {
		doLoad(new String[] {"backup", "--full"});
		
		assertEquals("Backup, teste simples", switches.getEntity(), switches.getEntity());
		
		assertTrue("Objeto de backup - isFull", switches.getBackupSwitches().isFull());
		assertFalse("Objeto de backup - isDDLOnly", switches.getBackupSwitches().isDDLOnly());
	}

	@Test
	public void doBackupDDLTest() throws ParserException {
		doLoad(new String[] {"backup", "--ddlonly"});
		
		assertEquals("Backup, teste simples", switches.getEntity(), switches.getEntity());
		
		assertFalse("Objeto de backup - isFull", switches.getBackupSwitches().isFull());
		assertTrue("Objeto de backup - isDDLOnly", switches.getBackupSwitches().isDDLOnly());
	}
		
}
