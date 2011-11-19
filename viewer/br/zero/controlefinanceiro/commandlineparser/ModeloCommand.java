package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitchParam;

public enum ModeloCommand {
	@CommandLineSwitchParam(name="ls")
	LIST,
	
	@CommandLineSwitchParam(name="add")
	ADD,
	
	@CommandLineSwitchParam(name="simulate")
	SIMULATE,
	
	@CommandLineSwitchParam(name="rm")
	REMOVE,
	
	@CommandLineSwitchParam(name="clone")
	CLONE
}
