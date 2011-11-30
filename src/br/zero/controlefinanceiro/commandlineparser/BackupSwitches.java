package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class BackupSwitches {
	
	private BackupType backupType;

	@CommandLineSwitch(index=1, parser = "BackupTypeParser.parseEnum")
	public void setBackupType(BackupType value) {
		backupType = value;
	}

	public boolean isFull() {
		return backupType.equals(BackupType.FULL);
	}

	public boolean isDDLOnly() {
		return backupType.equals(BackupType.DDLONLY);
	}

}
