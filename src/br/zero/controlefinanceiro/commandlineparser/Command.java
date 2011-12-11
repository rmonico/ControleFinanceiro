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

	@CommandLineSwitchParam(name = "lancmodelo-ls")
	LANCAMENTOMODELO_LIST,

	@CommandLineSwitchParam(name = "lancmodelo-add")
	LANCAMENTOMODELO_ADD,

	@CommandLineSwitchParam(name = "lancmodelo-rm")
	LANCAMENTOMODELO_REMOVE,

	@CommandLineSwitchParam(name = "extrato-ls")
	EXTRATO_LIST,

	@CommandLineSwitchParam(name = "extrato-import")
	EXTRATO_IMPORT,

	@CommandLineSwitchParam(name = "extrato-analyse")
	EXTRATO_ANALYSE,

	@CommandLineSwitchParam(name = "ctaref-ls")
	CONTAREFERENCIA_LIST,
	
	@CommandLineSwitchParam(name = "ctaref-add")
	CONTAREFERENCIA_ADD,
	
	@CommandLineSwitchParam(name = "help")
	HELP
}
