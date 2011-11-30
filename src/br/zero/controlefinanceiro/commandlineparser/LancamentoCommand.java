package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitchParam;

public enum LancamentoCommand {
	@CommandLineSwitchParam(name="ls")
	LIST,
	
	@CommandLineSwitchParam(name="balance")
	BALANCE,
	
	@CommandLineSwitchParam(name="add")
	ADD,
	
	@CommandLineSwitchParam(name="addfull")
	ADD_FULL,
	
	@CommandLineSwitchParam(name="rm")
	REMOVE
}
