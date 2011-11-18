package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;
import br.zero.commandlineparser.SubCommandLine;

public class CommandLineSwitches {

	private Entity entity;
	private BackupSwitches backupSwitches;
	private ContaSwitches contaSwitches;
	private LancamentoSwitches lancamentoSwitches;
	private ModeloSwitches modeloSwitches;

	/**
	 * Argumento principal da linha de comando.
	 */
	@CommandLineSwitch(parser = "EntityParser.parseComplexEnum", complexParser = true, index = 1, subCommandLineProperties = { 
			@SubCommandLine(value = "BACKUP", subCommandLineClass = BackupSwitches.class, propertyName = "setBackupSwitches"),
			@SubCommandLine(value = "CONTA", subCommandLineClass = ContaSwitches.class, propertyName = "setContaSwitches"),
			@SubCommandLine(value = "LANCAMENTO", subCommandLineClass = LancamentoSwitches.class, propertyName = "setLancamentoSwitches"),
			@SubCommandLine(value = "MODELO", subCommandLineClass = ModeloSwitches.class, propertyName = "setModeloSwitches"), 
			@SubCommandLine(value = "LANCAMENTO_MODELO", subCommandLineClass = Object.class, propertyName = "setShowHelpCommand"),
			@SubCommandLine(value = "HELP", subCommandLineClass = Object.class, propertyName = "setShowHelpCommand") })
	public void setEntity(Entity entity) {
		this.entity = entity;
	}

	public Entity getEntity() {
		return entity;
	}

	public void setBackupSwitches(BackupSwitches value) {
		backupSwitches = value;
	}
	
	public BackupSwitches getBackupSwitches() {
		return backupSwitches;
	}
	
	public void setContaSwitches(ContaSwitches value) {
		contaSwitches = value;
	}

	public ContaSwitches getContaSwitches() {
		return contaSwitches;
	}

	public void setLancamentoSwitches(LancamentoSwitches value) {
		lancamentoSwitches = value;
	}
	
	public LancamentoSwitches getLancamentoSwitches() {
		return lancamentoSwitches;
	}
}
