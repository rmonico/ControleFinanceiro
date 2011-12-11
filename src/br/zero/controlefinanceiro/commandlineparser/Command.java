package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitchParam;

public enum Command {
	@CommandLineSwitchParam(name = "backup")
	BACKUP,

	@CommandLineSwitchParam(name = "conta-ls")
	CONTA_LIST,

	@CommandLineSwitchParam(name = "conta-add")
	CONTA_ADD,

	@CommandLineSwitchParam(name = "conta-rm")
	CONTA_REMOVE,

	@CommandLineSwitchParam(name="lanc-ls")
	LANCAMENTO_LIST,
	
	@CommandLineSwitchParam(name="lanc-balance")
	LANCAMENTO_BALANCE,
	
	@CommandLineSwitchParam(name="lanc-add")
	LANCAMENTO_ADD,
	
	@CommandLineSwitchParam(name="lanc-addfull")
	LANCAMENTO_ADDFULL,
	
	@CommandLineSwitchParam(name="lanc-rm")
	LANCAMENTO_REMOVE,

	@CommandLineSwitchParam(name="modelo-ls")
	MODELO_LIST,
	
	@CommandLineSwitchParam(name="modelo-add")
	MODELO_ADD,
	
	@CommandLineSwitchParam(name="modelo-simulate")
	MODELO_SIMULATE,
	
	@CommandLineSwitchParam(name="modelo-rm")
	MODELO_REMOVE,
	
	@CommandLineSwitchParam(name="modelo-clone")
	MODELO_CLONE,
	
	@CommandLineSwitchParam(name="modelo-analyse")
	MODELO_ANALYSE,

	@CommandLineSwitchParam(name = "lancmodelo")
	LANCAMENTO_MODELO,

	@CommandLineSwitchParam(name = "extrato")
	EXTRATO,
	
	@CommandLineSwitchParam(name = "ctaref")
	CONTA_REFERENCIA,
	
	@CommandLineSwitchParam(name = "help")
	HELP
}
