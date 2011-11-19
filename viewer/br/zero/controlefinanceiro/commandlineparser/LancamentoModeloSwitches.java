package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;
import br.zero.commandlineparser.SubCommandLine;

public class LancamentoModeloSwitches {

	private LancamentoModeloCommand command;
	private LancamentoModeloListSwitches lancamentoModeloListSwitches;
	private LancamentoModeloAddSwitches lancamentoModeloAddSwitches;
	private LancamentoModeloRemoveSwitches lancamentoModeloRemoveSwitches;

	public LancamentoModeloCommand getCommand() {
		return command;
	}

	@CommandLineSwitch(index = 1, parser = "LancamentoModeloCommandParser.parseComplexEnum", complexParser = true, subCommandLineProperties = { 
			@SubCommandLine(value = "LIST", subCommandLineClass = LancamentoModeloListSwitches.class, propertyName = "setListSwitches"),
			@SubCommandLine(value = "ADD", subCommandLineClass = LancamentoModeloAddSwitches.class, propertyName = "setAddSwitches"),
			@SubCommandLine(value = "REMOVE", subCommandLineClass = LancamentoModeloRemoveSwitches.class, propertyName = "setRemoveSwitches"), })
	public void setCommand(LancamentoModeloCommand value) {
		command = value;
	}

	public void setListSwitches(LancamentoModeloListSwitches value) {
		lancamentoModeloListSwitches = value;
	}
	
	public LancamentoModeloListSwitches getListSwitches() {
		return lancamentoModeloListSwitches;
	}

	public void setAddSwitches(LancamentoModeloAddSwitches value) {
		lancamentoModeloAddSwitches = value;
	}

	public LancamentoModeloAddSwitches getAddSwitches() {
		return lancamentoModeloAddSwitches;
	}

	public void setRemoveSwitches(LancamentoModeloRemoveSwitches value) {
		lancamentoModeloRemoveSwitches = value;
	}

	public LancamentoModeloRemoveSwitches getRemoveSwitches() {
		return lancamentoModeloRemoveSwitches;
	}
}
