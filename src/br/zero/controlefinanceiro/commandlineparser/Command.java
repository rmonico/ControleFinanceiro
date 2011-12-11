package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitchParam;

public enum Command {
	@CommandLineSwitchParam(name = "backup")
	BACKUP,

//	@CommandLineSwitchParam(name = "conta")
//	CONTA,

	@CommandLineSwitchParam(name = "conta-ls")
	CONTA_LIST,

	@CommandLineSwitchParam(name = "conta-add")
	CONTA_ADD,

	@CommandLineSwitchParam(name = "conta-rm")
	CONTA_REMOVE,

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
