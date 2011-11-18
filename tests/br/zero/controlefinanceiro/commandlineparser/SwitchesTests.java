package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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
		doLoad(new String[] {"backup", "full"});
		
		assertEquals("Entidade", switches.getEntity(), switches.getEntity());
		
		BackupSwitches backupSwitches = switches.getBackupSwitches();
		
		assertTrue("isFull", backupSwitches.isFull());
		assertFalse("isDDLOnly", backupSwitches.isDDLOnly());
	}

	@Test
	public void doBackupDDLTest() throws ParserException {
		doLoad(new String[] {"backup", "ddlonly"});
		
		assertEquals("Entidade", Entity.BACKUP, switches.getEntity());
		
		BackupSwitches backupSwitches = switches.getBackupSwitches();
		
		assertFalse("Objeto de backup - isFull", backupSwitches.isFull());
		assertTrue("Objeto de backup - isDDLOnly", backupSwitches.isDDLOnly());
	}
	
	@Test
	public void doContaListTest() throws ParserException {
		doLoad(new String[] {"conta", "ls"});
		
		assertEquals("Entidade", Entity.CONTA, switches.getEntity());
		
		ContaSwitches contaSwitches = switches.getContaSwitches();
		
		assertEquals("Comando da conta", ContaCommand.LIST, contaSwitches.getCommand());
		
		ContaListSwitches contaListSwitches = contaSwitches.getListSwitches();
		
		assertNull("clausula where", contaListSwitches.getWhere());
	}
	
	@Test
	public void doContaListTest2() throws ParserException {
		doLoad(new String[] {"conta", "ls", "where_clause"});
		
		assertEquals("Entidade", Entity.CONTA, switches.getEntity());
		
		ContaSwitches contaSwitches = switches.getContaSwitches();
		
		assertEquals("Comando da conta", ContaCommand.LIST, contaSwitches.getCommand());
		
		ContaListSwitches contaListSwitches = contaSwitches.getListSwitches();
		
		assertEquals("clausula where", "where_clause", contaListSwitches.getWhere());
	}
	
	@Test
	public void doContaAddTest() throws ParserException {
		doLoad(new String[] {"conta", "add", "nova conta"});
		
		assertEquals("Entidade", Entity.CONTA, switches.getEntity());
		
		ContaSwitches contaSwitches = switches.getContaSwitches();
		
		assertEquals("Comando da conta", ContaCommand.ADD, contaSwitches.getCommand());

		ContaAddSwitches contaAddSwitches = contaSwitches.getAddSwitches();
		
		assertEquals("nome da nova conta", "nova conta", contaAddSwitches.getNome());
		assertNull("conta sem observacao", contaAddSwitches.getObservacao());
	}

	@Test
	public void doContaAddTest2() throws ParserException {
		doLoad(new String[] {"conta", "add", "nova conta", "Observação da nova conta"});
		
		assertEquals("Entidade", Entity.CONTA, switches.getEntity());
		
		ContaSwitches contaSwitches = switches.getContaSwitches();
		
		assertEquals("Comando da conta", ContaCommand.ADD, contaSwitches.getCommand());

		ContaAddSwitches contaAddSwitches = contaSwitches.getAddSwitches();
		
		assertEquals("Nome", "nova conta", contaAddSwitches.getNome());
		
		assertEquals("Observacao", "Observação da nova conta", contaAddSwitches.getObservacao());
	}
}
