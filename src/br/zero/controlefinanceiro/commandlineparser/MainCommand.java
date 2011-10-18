package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitchParam;

public enum MainCommand {
	/**
	 * Cria novas transações.
	 */
	@CommandLineSwitchParam(name = "new")
	NEW,
	/**
	 * Lista transações existentes.
	 */
	@CommandLineSwitchParam(name = "list")
	LIST,
	/**
	 * Exibe opções de ajuda e sai.
	 */
	@CommandLineSwitchParam(name = "help")
	HELP
}
