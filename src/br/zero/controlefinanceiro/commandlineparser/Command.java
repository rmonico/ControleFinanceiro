package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitchParam;

public enum Command {
	@CommandLineSwitchParam(name = "list")
	LIST, @CommandLineSwitchParam(name = "new")
	NEW, @CommandLineSwitchParam(name = "delete")
	REMOVE, @CommandLineSwitchParam(name = "subst")
	SUBST;
}
