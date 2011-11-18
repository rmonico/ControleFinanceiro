package br.zero.controlefinanceiro.commandlineparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import br.zero.switchesparser.ParserException;

public class BackupSwitchesTests extends CustomParserTests {
	
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

}
