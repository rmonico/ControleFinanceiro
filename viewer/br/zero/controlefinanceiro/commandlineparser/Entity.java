package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitchParam;

public enum Entity {
	@CommandLineSwitchParam(name = "backup")
	BACKUP,
	CONTA,
	LANCAMENTO,
	MODELO,
	LANCAMENTO_MODELO,
	HELP
}
