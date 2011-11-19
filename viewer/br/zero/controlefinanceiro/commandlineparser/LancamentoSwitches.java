package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;
import br.zero.commandlineparser.SubCommandLine;

public class LancamentoSwitches {

	private LancamentoCommand command;
	private LancamentoListSwitches listSwitches;
	private LancamentoBalanceSwitches balanceSwitches;
	private LancamentoAddSwitches addSwitches;
	private LancamentoAddFullSwitches addFullSwitches;
	private LancamentoRemoveSwitches removeSwitches;

	@CommandLineSwitch(index = 1, parser = "LancamentoCommandParser.parseComplexEnum", complexParser = true, subCommandLineProperties = {
			@SubCommandLine(value = "LIST", subCommandLineClass = LancamentoListSwitches.class, propertyName = "setListSwitches"), 
			@SubCommandLine(value = "BALANCE", subCommandLineClass = LancamentoBalanceSwitches.class, propertyName = "setBalanceSwitches"),
			@SubCommandLine(value = "ADD", subCommandLineClass = LancamentoAddSwitches.class, propertyName = "setAddSwitches"), 
			@SubCommandLine(value = "ADD_FULL", subCommandLineClass = LancamentoAddFullSwitches.class, propertyName = "setAddFullSwitches"), 
			@SubCommandLine(value = "REMOVE", subCommandLineClass = LancamentoRemoveSwitches.class, propertyName = "setRemoveSwitches"), })
	public void setCommand(LancamentoCommand value) {
		command = value;
	}

	public LancamentoCommand getCommand() {
		return command;
	}

	public LancamentoListSwitches getListSwitches() {
		return listSwitches;
	}

	public void setListSwitches(LancamentoListSwitches listSwitches) {
		this.listSwitches = listSwitches;
	}

	public LancamentoBalanceSwitches getBalanceSwitches() {
		return balanceSwitches;
	}

	public void setBalanceSwitches(LancamentoBalanceSwitches balanceSwitches) {
		this.balanceSwitches = balanceSwitches;
	}

	public LancamentoAddSwitches getAddSwitches() {
		return addSwitches;
	}

	public void setAddSwitches(LancamentoAddSwitches addSwitches) {
		this.addSwitches = addSwitches;
	}

	public LancamentoAddFullSwitches getAddFullSwitches() {
		return addFullSwitches;
	}

	public void setAddFullSwitches(LancamentoAddFullSwitches addFullSwitches) {
		this.addFullSwitches = addFullSwitches;
	}

	public LancamentoRemoveSwitches getRemoveSwitches() {
		return removeSwitches;
	}

	public void setRemoveSwitches(LancamentoRemoveSwitches removeSwitches) {
		this.removeSwitches = removeSwitches;
	}

}
