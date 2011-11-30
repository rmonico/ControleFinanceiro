package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;
import br.zero.commandlineparser.SubCommandLine;

public class ModeloSwitches {

	private ModeloCommand command;
	private ModeloAddSwitches addSwitches;
	private ModeloSimulateSwitches simulateSwitches;
	private ModeloRemoveSwitches removeSwitches;
	private ModeloCloneSwitches cloneSwitches;
	private ModeloAnalyseSwitches analyseSwitches;

	public ModeloCommand getCommand() {
		return command;
	}

	@CommandLineSwitch(index = 1, parser = "ModeloCommandParser.parseComplexEnum", complexParser = true, subCommandLineProperties = {
			@SubCommandLine(value = "ADD", subCommandLineClass = ModeloAddSwitches.class, propertyName = "setAddSwitches"),
			@SubCommandLine(value = "SIMULATE", subCommandLineClass = ModeloSimulateSwitches.class, propertyName = "setSimulateSwitches"),
			@SubCommandLine(value = "REMOVE", subCommandLineClass = ModeloRemoveSwitches.class, propertyName = "setRemoveSwitches"),
			@SubCommandLine(value = "CLONE", subCommandLineClass = ModeloCloneSwitches.class, propertyName = "setCloneSwitches"),
			@SubCommandLine(value = "ANALYSE", subCommandLineClass = ModeloAnalyseSwitches.class, propertyName = "setAnalyseSwitches"), })
	public void setCommand(ModeloCommand value) {
		command = value;
	}

	public ModeloAddSwitches getAddSwitches() {
		return addSwitches;
	}

	public void setAddSwitches(ModeloAddSwitches value) {
		addSwitches = value;
	}

	public ModeloSimulateSwitches getSimulateSwitches() {
		return simulateSwitches;
	}

	public void setSimulateSwitches(ModeloSimulateSwitches value) {
		simulateSwitches = value;
	}

	public ModeloRemoveSwitches getRemoveSwitches() {
		return removeSwitches;
	}

	public void setRemoveSwitches(ModeloRemoveSwitches value) {
		removeSwitches = value;
	}

	public ModeloCloneSwitches getCloneSwitches() {
		return cloneSwitches;
	}

	public void setCloneSwitches(ModeloCloneSwitches value) {
		cloneSwitches = value;
	}

	public ModeloAnalyseSwitches getAnalyseSwitches() {
		return analyseSwitches;
	}
	
	public void setAnalyseSwitches(ModeloAnalyseSwitches value) {
		analyseSwitches = value;
	}

}
