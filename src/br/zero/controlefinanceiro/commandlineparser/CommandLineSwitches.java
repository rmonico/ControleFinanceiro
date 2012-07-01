package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;
import br.zero.commandlineparser.SubCommandLine;

public class CommandLineSwitches {

	private Command command;
	
	private Object subCommandSwitches;

	private BackupSwitches backupSwitches;

	private ContaAddSwitches contaAddSwitches;
	private ContaRemoveSwitches contaRemoveSwitches;
	private ContaListSwitches contaListSwitches;
	private ContaSetBalanceSwitches contaSetBalanceSwitches;

	private LancamentoListSwitches lancamentoListSwitches;
	private LancamentoBalanceSwitches lancamentoBalanceSwitches;
	private LancamentoAddSwitches lancamentoAddSwitches;
	private LancamentoAddFullSwitches lancamentoAddFullSwitches;
	private LancamentoRemoveSwitches lancamentoRemoveSwitches;

	private ModeloAddSwitches modeloAddSwitches;
	private ModeloSimulateSwitches modeloSimulateSwitches;
	private ModeloRemoveSwitches modeloRemoveSwitches;
	private ModeloCloneSwitches modeloCloneSwitches;
	private ModeloAnalyseSwitches modeloAnalyseSwitches;

	private LancamentoModeloListSwitches lancamentoModeloListSwitches;
	private LancamentoModeloAddSwitches lancamentoModeloAddSwitches;
	private LancamentoModeloRemoveSwitches lancamentoModeloRemoveSwitches;

	private ExtratoListSwitches extratoListSwitches;
	private ExtratoImportSwitches extratoImportSwitches;
	private ExtratoAnalyseSwitches extratoAnalyseSwitches;

	private ContaReferenciaListSwitches contaReferenciaListSwitches;
	private ContaReferenciaAddSwitches contaReferenciaAddSwitches;

	private HelpSwitches helpSwitches;

	/**
	 * Argumento principal da linha de comando.
	 */
	@CommandLineSwitch(parser = "EntityParser.parseComplexEnum", complexParser = true, index = 1, subCommandLineProperties = {
			@SubCommandLine(value = "BACKUP", subCommandLineClass = BackupSwitches.class, propertyName = "setBackupSwitches"),
			@SubCommandLine(value = "CONTA_LIST", subCommandLineClass = ContaListSwitches.class, propertyName = "setContaListSwitches"),
			@SubCommandLine(value = "CONTA_ADD", subCommandLineClass = ContaAddSwitches.class, propertyName = "setContaAddSwitches"),
			@SubCommandLine(value = "CONTA_REMOVE", subCommandLineClass = ContaRemoveSwitches.class, propertyName = "setContaRemoveSwitches"),
			@SubCommandLine(value = "CONTA_SETBALANCE", subCommandLineClass = ContaSetBalanceSwitches.class, propertyName = "setContaSetBalanceSwitches"),
			@SubCommandLine(value = "LANCAMENTO_LIST", subCommandLineClass = LancamentoListSwitches.class, propertyName = "setLancamentoListSwitches"),
			@SubCommandLine(value = "LANCAMENTO_BALANCE", subCommandLineClass = LancamentoBalanceSwitches.class, propertyName = "setLancamentoBalanceSwitches"),
			@SubCommandLine(value = "LANCAMENTO_ADD", subCommandLineClass = LancamentoAddSwitches.class, propertyName = "setLancamentoAddSwitches"),
			@SubCommandLine(value = "LANCAMENTO_ADDFULL", subCommandLineClass = LancamentoAddFullSwitches.class, propertyName = "setLancamentoAddFullSwitches"),
			@SubCommandLine(value = "LANCAMENTO_REMOVE", subCommandLineClass = LancamentoRemoveSwitches.class, propertyName = "setLancamentoRemoveSwitches"),
			// @SubCommandLine(value = "MODELO_LIST", subCommandLineClass =
			// Object.class, propertyName = ""),
			@SubCommandLine(value = "MODELO_ADD", subCommandLineClass = ModeloAddSwitches.class, propertyName = "setModeloAddSwitches"), 
			@SubCommandLine(value = "MODELO_SIMULATE", subCommandLineClass = ModeloSimulateSwitches.class, propertyName = "setModeloSimulateSwitches"),
			@SubCommandLine(value = "MODELO_REMOVE", subCommandLineClass = ModeloRemoveSwitches.class, propertyName = "setModeloRemoveSwitches"), 
			@SubCommandLine(value = "MODELO_CLONE", subCommandLineClass = ModeloCloneSwitches.class, propertyName = "setModeloCloneSwitches"),
			@SubCommandLine(value = "MODELO_ANALYSE", subCommandLineClass = ModeloAnalyseSwitches.class, propertyName = "setModeloAnalyseSwitches"), 
			@SubCommandLine(value = "LANCAMENTOMODELO_LIST", subCommandLineClass = LancamentoModeloListSwitches.class, propertyName = "setLancamentoModeloListSwitches"),
			@SubCommandLine(value = "LANCAMENTOMODELO_ADD", subCommandLineClass = LancamentoModeloAddSwitches.class, propertyName = "setLancamentoModeloAddSwitches"), 
			@SubCommandLine(value = "LANCAMENTOMODELO_REMOVE", subCommandLineClass = LancamentoModeloRemoveSwitches.class, propertyName = "setLancamentoModeloRemoveSwitches"),
			@SubCommandLine(value = "EXTRATO_LIST", subCommandLineClass = ExtratoListSwitches.class, propertyName = "setExtratoListSwitches"), 
			@SubCommandLine(value = "EXTRATO_IMPORT", subCommandLineClass = ExtratoImportSwitches.class, propertyName = "setExtratoImportSwitches"),
			@SubCommandLine(value = "EXTRATO_ANALYSE", subCommandLineClass = ExtratoAnalyseSwitches.class, propertyName = "setExtratoAnalyseSwitches"),
			@SubCommandLine(value = "CONTAREFERENCIA_LIST", subCommandLineClass = ContaReferenciaListSwitches.class, propertyName = "setContaReferenciaListSwitches"),
			@SubCommandLine(value = "CONTAREFERENCIA_ADD", subCommandLineClass = ContaReferenciaAddSwitches.class, propertyName = "setContaReferenciaAddSwitches"),
			@SubCommandLine(value = "HELP", subCommandLineClass = HelpSwitches.class, propertyName = "setHelpSwitches") })
	public void setCommand(Command command) {
		this.command = command;
	}

	public Command getCommand() {
		return command;
	}

	public void setBackupSwitches(BackupSwitches value) {
		backupSwitches = value;
		subCommandSwitches = value;
	}

	public BackupSwitches getBackupSwitches() {
		return backupSwitches;
	}

	public void setHelpSwitches(HelpSwitches value) {
		helpSwitches = value;
		subCommandSwitches = value;
	}

	public HelpSwitches getHelpSwitches() {
		return helpSwitches;
	}

	public ContaAddSwitches getContaAddSwitches() {
		return contaAddSwitches;
	}

	public void setContaAddSwitches(ContaAddSwitches value) {
		this.contaAddSwitches = value;
		subCommandSwitches = value;
	}

	public ContaRemoveSwitches getContaRemoveSwitches() {
		return contaRemoveSwitches;
	}

	public void setContaRemoveSwitches(ContaRemoveSwitches value) {
		this.contaRemoveSwitches = value;
		subCommandSwitches = value;
	}

	public ContaListSwitches getContaListSwitches() {
		return contaListSwitches;
	}

	public void setContaListSwitches(ContaListSwitches value) {
		this.contaListSwitches = value;
		subCommandSwitches = value;
	}

	public void setContaSetBalanceSwitches(ContaSetBalanceSwitches value) {
		this.contaSetBalanceSwitches = value;
		subCommandSwitches = value;
	}
	
	public ContaSetBalanceSwitches getContaSetBalanceSwitches() {
		return contaSetBalanceSwitches;
	}

	public LancamentoListSwitches getLancamentoListSwitches() {
		return lancamentoListSwitches;
	}

	public void setLancamentoListSwitches(LancamentoListSwitches value) {
		this.lancamentoListSwitches = value;
		subCommandSwitches = value;
	}

	public LancamentoBalanceSwitches getLancamentoBalanceSwitches() {
		return lancamentoBalanceSwitches;
	}

	public void setLancamentoBalanceSwitches(LancamentoBalanceSwitches value) {
		this.lancamentoBalanceSwitches = value;
		subCommandSwitches = value;
	}

	public LancamentoAddSwitches getLancamentoAddSwitches() {
		return lancamentoAddSwitches;
	}

	public void setLancamentoAddSwitches(LancamentoAddSwitches value) {
		this.lancamentoAddSwitches = value;
		subCommandSwitches = value;
	}

	public LancamentoAddFullSwitches getLancamentoAddFullSwitches() {
		return lancamentoAddFullSwitches;
	}

	public void setLancamentoAddFullSwitches(LancamentoAddFullSwitches value) {
		this.lancamentoAddFullSwitches = value;
		subCommandSwitches = value;
	}

	public LancamentoRemoveSwitches getLancamentoRemoveSwitches() {
		return lancamentoRemoveSwitches;
	}

	public void setLancamentoRemoveSwitches(LancamentoRemoveSwitches value) {
		this.lancamentoRemoveSwitches = value;
		subCommandSwitches = value;
	}

	public ModeloAddSwitches getModeloAddSwitches() {
		return modeloAddSwitches;
	}

	public void setModeloAddSwitches(ModeloAddSwitches value) {
		this.modeloAddSwitches = value;
		subCommandSwitches = value;
	}

	public ModeloSimulateSwitches getModeloSimulateSwitches() {
		return modeloSimulateSwitches;
	}

	public void setModeloSimulateSwitches(ModeloSimulateSwitches value) {
		this.modeloSimulateSwitches = value;
		subCommandSwitches = value;
	}

	public ModeloRemoveSwitches getModeloRemoveSwitches() {
		return modeloRemoveSwitches;
	}

	public void setModeloRemoveSwitches(ModeloRemoveSwitches value) {
		this.modeloRemoveSwitches = value;
		subCommandSwitches = value;
	}

	public ModeloCloneSwitches getModeloCloneSwitches() {
		return modeloCloneSwitches;
	}

	public void setModeloCloneSwitches(ModeloCloneSwitches value) {
		this.modeloCloneSwitches = value;
		subCommandSwitches = value;
	}

	public ModeloAnalyseSwitches getModeloAnalyseSwitches() {
		return modeloAnalyseSwitches;
	}

	public void setModeloAnalyseSwitches(ModeloAnalyseSwitches value) {
		this.modeloAnalyseSwitches = value;
		subCommandSwitches = value;
	}

	public LancamentoModeloListSwitches getLancamentoModeloListSwitches() {
		return lancamentoModeloListSwitches;
	}

	public void setLancamentoModeloListSwitches(LancamentoModeloListSwitches value) {
		this.lancamentoModeloListSwitches = value;
		subCommandSwitches = value;
	}

	public LancamentoModeloAddSwitches getLancamentoModeloAddSwitches() {
		return lancamentoModeloAddSwitches;
	}

	public void setLancamentoModeloAddSwitches(LancamentoModeloAddSwitches value) {
		this.lancamentoModeloAddSwitches = value;
		subCommandSwitches = value;
	}

	public LancamentoModeloRemoveSwitches getLancamentoModeloRemoveSwitches() {
		return lancamentoModeloRemoveSwitches;
	}

	public void setLancamentoModeloRemoveSwitches(LancamentoModeloRemoveSwitches value) {
		this.lancamentoModeloRemoveSwitches = value;
		subCommandSwitches = value;
	}

	public ExtratoListSwitches getExtratoListSwitches() {
		return extratoListSwitches;
	}

	public void setExtratoListSwitches(ExtratoListSwitches value) {
		this.extratoListSwitches = value;
		subCommandSwitches = value;
	}

	public ExtratoImportSwitches getExtratoImportSwitches() {
		return extratoImportSwitches;
	}

	public void setExtratoImportSwitches(ExtratoImportSwitches value) {
		this.extratoImportSwitches = value;
		subCommandSwitches = value;
	}

	public ExtratoAnalyseSwitches getExtratoAnalyseSwitches() {
		return extratoAnalyseSwitches;
	}

	public void setExtratoAnalyseSwitches(ExtratoAnalyseSwitches value) {
		this.extratoAnalyseSwitches = value;
		subCommandSwitches = value;
	}

	public void setContaReferenciaListSwitches(ContaReferenciaListSwitches value) {
		this.contaReferenciaListSwitches = value;
		subCommandSwitches = value;
	}

	public ContaReferenciaListSwitches getContaReferenciaListSwitches() {
		return contaReferenciaListSwitches;
	}

	public ContaReferenciaAddSwitches getContaReferenciaAddSwitches() {
		return contaReferenciaAddSwitches;
	}

	public void setContaReferenciaAddSwitches(ContaReferenciaAddSwitches value) {
		this.contaReferenciaAddSwitches = value;
		subCommandSwitches = value;
	}
	
	public Object getSubCommandSwitches() {
		return subCommandSwitches;
	}

}
