package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitchParam;

public enum BackupType {
	@CommandLineSwitchParam(name = "full")
	FULL,
	
	@CommandLineSwitchParam(name = "ddlonly")
	DDLONLY
}
