package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitchParam;

public enum Command {
	@CommandLineSwitchParam(name = "backup")
	BACKUP,

	@CommandLineSwitchParam(name = "conta")
	CONTA,

	@CommandLineSwitchParam(name = "lanc")
	LANCAMENTO,

	@CommandLineSwitchParam(name = "modelo")
	MODELO,

	@CommandLineSwitchParam(name = "lancmodelo")
	LANCAMENTO_MODELO,

	@CommandLineSwitchParam(name = "extrato")
	EXTRATO,
	
	@CommandLineSwitchParam(name = "ctaref")
	CONTA_REFERENCIA,
	
	@CommandLineSwitchParam(name = "help")
	HELP
}
