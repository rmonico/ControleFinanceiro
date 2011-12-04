package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitchParam;

public enum ExtratoCommand {

	@CommandLineSwitchParam(name = "ls")
	LIST,

	@CommandLineSwitchParam(name = "import")
	IMPORT,

	@CommandLineSwitchParam(name = "analyse")
	ANALYSE;

}
