package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.zero.commandlineparser.ParserException;

public class BackupSwitchesTests extends CustomParserTests {
	
	@Test
	public void doBackupTest() throws ParserException {
		doLoad(new String[] {"backup", "full"});
		
		assertEquals("Entidade", switches.getCommand(), switches.getCommand());
		
		BackupSwitches backupSwitches = switches.getBackupSwitches();
		
		assertTrue("isFull", backupSwitches.isFull());
		assertFalse("isDDLOnly", backupSwitches.isDDLOnly());
	}

	@Test
	public void doBackupDDLTest() throws ParserException {
		doLoad(new String[] {"backup", "ddlonly"});
		
		assertEquals("Entidade", Command.BACKUP, switches.getCommand());
		
		BackupSwitches backupSwitches = switches.getBackupSwitches();
		
		assertFalse("Objeto de backup - isFull", backupSwitches.isFull());
		assertTrue("Objeto de backup - isDDLOnly", backupSwitches.isDDLOnly());
	}

}
