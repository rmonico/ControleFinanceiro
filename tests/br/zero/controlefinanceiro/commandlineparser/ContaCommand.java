package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitchParam;

public enum ContaCommand {
	@CommandLineSwitchParam(name = "ls")
	LIST,
	
	@CommandLineSwitchParam(name = "add")
	ADD

}
