package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitchParam;

public enum Entity {
	@CommandLineSwitchParam(name = "backup")
	BACKUP,

	@CommandLineSwitchParam(name = "conta")
	CONTA,

	@CommandLineSwitchParam(name = "lanc")
	LANCAMENTO,

	@CommandLineSwitchParam(name = "modelo")
	MODELO, LANCAMENTO_MODELO, HELP
}
