package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class LancamentoBalanceSwitches {
	private String conta;
	private String where;

	
	public String getConta() {
		return conta;
	}

	@CommandLineSwitch(index=1)
	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getWhere() {
		return where;
	}

	@CommandLineSwitch(index=2)
	public void setWhere(String where) {
		this.where = where;
	}

}
