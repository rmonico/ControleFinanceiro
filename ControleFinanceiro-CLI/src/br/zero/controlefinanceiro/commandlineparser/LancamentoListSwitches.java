package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class LancamentoListSwitches {

	private String where;

	/**
	 * TODO Depois trocar por filtros mais adequados
	 * 
	 * @param value
	 */
	@CommandLineSwitch(index = 1)
	public void setWhere(String value) {
		this.where = value;
	}

	public String getWhere() {
		return where;
	}
}
