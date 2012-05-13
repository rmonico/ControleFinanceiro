package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;
import br.zero.commandlineparser.SubCommandLine;

public class CommandLineSwitches {

	private Command command;

	private BackupSwitches backupSwitches;

	private ContaAddSwitches contaAddSwitches;
	private ContaRemoveSwitches contaRemoveSwitches;
	private ContaListSwitches contaListSwitches;

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
	}

	public BackupSwitches getBackupSwitches() {
		return backupSwitches;
	}

	public void setHelpSwitches(HelpSwitches value) {
		helpSwitches = value;
	}

	public HelpSwitches getHelpSwitches() {
		return helpSwitches;
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

	// TODO Fazer método setter de ContaSetBalanceSwitches
	
	public ContaSetBalanceSwitches getContaSetBalanceSwitches() {
		return new ContaSetBalanceSwitches();
	}

	public LancamentoListSwitches getLancamentoListSwitches() {
		return lancamentoListSwitches;
	}

	public void setLancamentoListSwitches(LancamentoListSwitches lancamentoListSwitches) {
		this.lancamentoListSwitches = lancamentoListSwitches;
	}

	public LancamentoBalanceSwitches getLancamentoBalanceSwitches() {
		return lancamentoBalanceSwitches;
	}

	public void setLancamentoBalanceSwitches(LancamentoBalanceSwitches lancamentoBalanceSwitches) {
		this.lancamentoBalanceSwitches = lancamentoBalanceSwitches;
	}

	public LancamentoAddSwitches getLancamentoAddSwitches() {
		return lancamentoAddSwitches;
	}

	public void setLancamentoAddSwitches(LancamentoAddSwitches lancamentoAddSwitches) {
		this.lancamentoAddSwitches = lancamentoAddSwitches;
	}

	public LancamentoAddFullSwitches getLancamentoAddFullSwitches() {
		return lancamentoAddFullSwitches;
	}

	public void setLancamentoAddFullSwitches(LancamentoAddFullSwitches lancamentoAddFullSwitches) {
		this.lancamentoAddFullSwitches = lancamentoAddFullSwitches;
	}

	public LancamentoRemoveSwitches getLancamentoRemoveSwitches() {
		return lancamentoRemoveSwitches;
	}

	public void setLancamentoRemoveSwitches(LancamentoRemoveSwitches lancamentoRemoveSwitches) {
		this.lancamentoRemoveSwitches = lancamentoRemoveSwitches;
	}

	public ModeloAddSwitches getModeloAddSwitches() {
		return modeloAddSwitches;
	}

	public void setModeloAddSwitches(ModeloAddSwitches modeloAddSwitches) {
		this.modeloAddSwitches = modeloAddSwitches;
	}

	public ModeloSimulateSwitches getModeloSimulateSwitches() {
		return modeloSimulateSwitches;
	}

	public void setModeloSimulateSwitches(ModeloSimulateSwitches modeloSimulateSwitches) {
		this.modeloSimulateSwitches = modeloSimulateSwitches;
	}

	public ModeloRemoveSwitches getModeloRemoveSwitches() {
		return modeloRemoveSwitches;
	}

	public void setModeloRemoveSwitches(ModeloRemoveSwitches modeloRemoveSwitches) {
		this.modeloRemoveSwitches = modeloRemoveSwitches;
	}

	public ModeloCloneSwitches getModeloCloneSwitches() {
		return modeloCloneSwitches;
	}

	public void setModeloCloneSwitches(ModeloCloneSwitches modeloCloneSwitches) {
		this.modeloCloneSwitches = modeloCloneSwitches;
	}

	public ModeloAnalyseSwitches getModeloAnalyseSwitches() {
		return modeloAnalyseSwitches;
	}

	public void setModeloAnalyseSwitches(ModeloAnalyseSwitches modeloAnalyseSwitches) {
		this.modeloAnalyseSwitches = modeloAnalyseSwitches;
	}

	public LancamentoModeloListSwitches getLancamentoModeloListSwitches() {
		return lancamentoModeloListSwitches;
	}

	public void setLancamentoModeloListSwitches(LancamentoModeloListSwitches lancamentoModeloListSwitches) {
		this.lancamentoModeloListSwitches = lancamentoModeloListSwitches;
	}

	public LancamentoModeloAddSwitches getLancamentoModeloAddSwitches() {
		return lancamentoModeloAddSwitches;
	}

	public void setLancamentoModeloAddSwitches(LancamentoModeloAddSwitches lancamentoModeloAddSwitches) {
		this.lancamentoModeloAddSwitches = lancamentoModeloAddSwitches;
	}

	public LancamentoModeloRemoveSwitches getLancamentoModeloRemoveSwitches() {
		return lancamentoModeloRemoveSwitches;
	}

	public void setLancamentoModeloRemoveSwitches(LancamentoModeloRemoveSwitches lancamentoModeloRemoveSwitches) {
		this.lancamentoModeloRemoveSwitches = lancamentoModeloRemoveSwitches;
	}

	public ExtratoListSwitches getExtratoListSwitches() {
		return extratoListSwitches;
	}

	public void setExtratoListSwitches(ExtratoListSwitches extratoListSwitches) {
		this.extratoListSwitches = extratoListSwitches;
	}

	public ExtratoImportSwitches getExtratoImportSwitches() {
		return extratoImportSwitches;
	}

	public void setExtratoImportSwitches(ExtratoImportSwitches extratoImportSwitches) {
		this.extratoImportSwitches = extratoImportSwitches;
	}

	public ExtratoAnalyseSwitches getExtratoAnalyseSwitches() {
		return extratoAnalyseSwitches;
	}

	public void setExtratoAnalyseSwitches(ExtratoAnalyseSwitches extratoAnalyseSwitches) {
		this.extratoAnalyseSwitches = extratoAnalyseSwitches;
	}

	public void setContaReferenciaListSwitches(ContaReferenciaListSwitches contaReferenciaListSwitches) {
		this.contaReferenciaListSwitches = contaReferenciaListSwitches;
	}

	public ContaReferenciaListSwitches getContaReferenciaListSwitches() {
		return contaReferenciaListSwitches;
	}

	public ContaReferenciaAddSwitches getContaReferenciaAddSwitches() {
		return contaReferenciaAddSwitches;
	}

	public void setContaReferenciaAddSwitches(ContaReferenciaAddSwitches contaReferenciaAddSwitches) {
		this.contaReferenciaAddSwitches = contaReferenciaAddSwitches;
	}

}
