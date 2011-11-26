package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitchParam;

public enum LancamentoModeloCommand {
	@CommandLineSwitchParam(name = "ls")
	LIST,

	@CommandLineSwitchParam(name = "add")
	ADD,

	@CommandLineSwitchParam(name = "rm")
	REMOVE,

	@CommandLineSwitchParam(name = "clone")
	CLONE,

	@CommandLineSwitchParam(name = "simulate")
	SIMULATE
}
