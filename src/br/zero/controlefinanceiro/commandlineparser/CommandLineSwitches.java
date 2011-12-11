package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;
import br.zero.commandlineparser.SubCommandLine;

public class CommandLineSwitches {

	private Command entity;
	private BackupSwitches backupSwitches;
//	private ContaSwitches contaSwitches;
	private ContaAddSwitches contaAddSwitches;
	private ContaRemoveSwitches contaRemoveSwitches;
	private ContaListSwitches contaListSwitches;
	
	private LancamentoSwitches lancamentoSwitches;
	private ModeloSwitches modeloSwitches;
	private LancamentoModeloSwitches lancamentoModeloSwitches;
	private ExtratoSwitches extratoSwitches;
	private HelpSwitches helpSwitches;
	private ContaReferenciaSwitches contaReferenciaSwitches;

	// TODO Centralizar comandos e subcomandos todos aqui
	/**
	 * Argumento principal da linha de comando.
	 */
	@CommandLineSwitch(parser = "EntityParser.parseComplexEnum", complexParser = true, index = 1, subCommandLineProperties = { 
			@SubCommandLine(value = "BACKUP", subCommandLineClass = BackupSwitches.class, propertyName = "setBackupSwitches"), 
			@SubCommandLine(value = "CONTA_LIST", subCommandLineClass = ContaListSwitches.class, propertyName = "setContaListSwitches"),
			@SubCommandLine(value = "CONTA_ADD", subCommandLineClass = ContaAddSwitches.class, propertyName = "setContaAddSwitches"),
			@SubCommandLine(value = "CONTA_REMOVE", subCommandLineClass = ContaRemoveSwitches.class, propertyName = "setContaRemoveSwitches"),
			@SubCommandLine(value = "LANCAMENTO", subCommandLineClass = LancamentoSwitches.class, propertyName = "setLancamentoSwitches"), 
			@SubCommandLine(value = "MODELO", subCommandLineClass = ModeloSwitches.class, propertyName = "setModeloSwitches"), 
			@SubCommandLine(value = "LANCAMENTO_MODELO", subCommandLineClass = LancamentoModeloSwitches.class, propertyName = "setLancamentoModeloSwitches"),
			@SubCommandLine(value = "EXTRATO", subCommandLineClass = ExtratoSwitches.class, propertyName = "setExtratoSwitches"),
			@SubCommandLine(value = "CONTA_REFERENCIA", subCommandLineClass = ContaReferenciaSwitches.class, propertyName = "setContaReferenceSwitches"),
			@SubCommandLine(value = "HELP", subCommandLineClass = HelpSwitches.class, propertyName = "setHelpSwitches") })
	public void setEntity(Command entity) {
		this.entity = entity;
	}

	public Command getEntity() {
		return entity;
	}

	public void setBackupSwitches(BackupSwitches value) {
		backupSwitches = value;
	}

	public BackupSwitches getBackupSwitches() {
		return backupSwitches;
	}

//	public void setContaSwitches(ContaSwitches value) {
//		contaSwitches = value;
//	}
//
//	public ContaSwitches getContaSwitches() {
//		return contaSwitches;
//	}

	public void setLancamentoSwitches(LancamentoSwitches value) {
		lancamentoSwitches = value;
	}

	public LancamentoSwitches getLancamentoSwitches() {
		return lancamentoSwitches;
	}

	public void setModeloSwitches(ModeloSwitches value) {
		modeloSwitches = value;
	}

	public ModeloSwitches getModeloSwitches() {
		return modeloSwitches;
	}

	public void setLancamentoModeloSwitches(LancamentoModeloSwitches value) {
		lancamentoModeloSwitches = value;
	}

	public LancamentoModeloSwitches getLancamentoModeloSwitches() {
		return lancamentoModeloSwitches;
	}
	
	public void setExtratoSwitches(ExtratoSwitches value) {
		extratoSwitches = value;
	}
	
	public ExtratoSwitches getExtratoSwitches() {
		return extratoSwitches;
	}
	
	public void setContaReferenceSwitches(ContaReferenciaSwitches value) {
		contaReferenciaSwitches = value;
	}
	
	public ContaReferenciaSwitches getContaReferenceSwitches() {
		return contaReferenciaSwitches;
	}

	public void setHelpSwitches(HelpSwitches value) {
		helpSwitches = value;
	}

	public HelpSwitches getHelpSwitches() {
		return helpSwitches;
	}

	public Enum<?> getEntityCommand() {
		switch (entity) {
		case BACKUP: {
			return null;
		}
//		case CONTA: {
//			return contaSwitches.getCommand();
//		}
		case LANCAMENTO: {
			return lancamentoSwitches.getCommand();
		}
		case MODELO: {
			return modeloSwitches.getCommand();
		}
		case LANCAMENTO_MODELO: {
			return lancamentoModeloSwitches.getCommand();
		}
		case EXTRATO: {
			return extratoSwitches.getCommand();
		}
		case HELP: {
			return null;
		}

		default: {
			return null;
		}
		}
	}

//	public Object getContaSubSwitches(ContaCommand command) {
//		if (!entity.equals(Command.CONTA)) {
//			return null;
//		}
//
//		switch (command) {
//		case LIST: {
//			return contaSwitches.getListSwitches();
//		}
//		case ADD: {
//			return contaSwitches.getAddSwitches();
//		}
//		case REMOVE: {
//			return contaSwitches.getRemoveSwitches();
//		}
//		default: {
//			assert false : "Commando de conta não implementado!";
//			return null;
//		}
//		}
//	}

	public Object getLancamentoSubSwitches(LancamentoCommand command) {
		if (!entity.equals(Command.LANCAMENTO)) {
			return null;
		}

		switch (command) {
		case ADD: {
			return lancamentoSwitches.getAddSwitches();
		}
		case ADD_FULL: {
			return lancamentoSwitches.getAddFullSwitches();
		}
		case BALANCE: {
			return lancamentoSwitches.getBalanceSwitches();
		}
		case LIST: {
			return lancamentoSwitches.getListSwitches();
		}
		case REMOVE: {
			return lancamentoSwitches.getRemoveSwitches();
		}
		default: {
			assert false : "Commando de lancamento não implementado!";

			return null;
		}
		}
	}

	public Object getModeloSubSwitches(ModeloCommand command) {
		if (!entity.equals(Command.MODELO)) {
			return null;
		}

		switch (command) {
		case ADD: {
			return modeloSwitches.getAddSwitches();
		}
		case CLONE: {
			return modeloSwitches.getCloneSwitches();
		}
		case LIST: {
			return null;
		}
		case REMOVE: {
			return modeloSwitches.getRemoveSwitches();
		}
		case SIMULATE: {
			return modeloSwitches.getSimulateSwitches();
		}
		case ANALYSE: {
			return modeloSwitches.getAnalyseSwitches();
		}
		default: {
			assert false : "Comando de modelo não implementado!";

			return null;
		}
		}
	}

	public Object getLancamentoModeloSubSwitches(LancamentoModeloCommand command) {
		if (!entity.equals(Command.LANCAMENTO_MODELO)) {
			return null;
		}

		switch (command) {
		case ADD: {
			return lancamentoModeloSwitches.getAddSwitches();
		}
		case LIST: {
			return lancamentoModeloSwitches.getListSwitches();
		}
		case REMOVE: {
			return lancamentoModeloSwitches.getRemoveSwitches();
		}
		default: {
			assert false : "Comando de lançamento modelo não implementado!";

			return null;
		}
		}
	}

	public Object getExtratoSubSwitches(ExtratoCommand command) {
		if (!entity.equals(Command.EXTRATO)) {
			return null;
		}
		
		switch (command) {
		case LIST: {
			return extratoSwitches.getListSwitches();
		}
		case IMPORT: {
			return extratoSwitches.getImportSwitches();
		}
		case ANALYSE: {
			return extratoSwitches.getAnalyseSwitches();
		}
		default: {
			assert false : "Comando de lançamento modelo não implementado!";

			return null;
		}
		}
	}

	public ContaAddSwitches getContaAddSwitches() {
		return contaAddSwitches;
	}

	public void setContaAddSwitches(ContaAddSwitches contaAddSwitches) {
		this.contaAddSwitches = contaAddSwitches;
	}

	public ContaRemoveSwitches getContaRemoveSwitches() {
		return contaRemoveSwitches;
	}

	public void setContaRemoveSwitches(ContaRemoveSwitches contaRemoveSwitches) {
		this.contaRemoveSwitches = contaRemoveSwitches;
	}

	public ContaListSwitches getContaListSwitches() {
		return contaListSwitches;
	}

	public void setContaListSwitches(ContaListSwitches contaListSwitches) {
		this.contaListSwitches = contaListSwitches;
	}


	
}
