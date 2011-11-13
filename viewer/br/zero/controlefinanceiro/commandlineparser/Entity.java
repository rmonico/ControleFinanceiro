package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitchParam;

public enum Entity {
	@CommandLineSwitchParam(name = "backup")
	BACKUP,
	
	@CommandLineSwitchParam(name = "conta")
	CONTA,
	LANCAMENTO,
	MODELO,
	LANCAMENTO_MODELO,
	HELP
}
