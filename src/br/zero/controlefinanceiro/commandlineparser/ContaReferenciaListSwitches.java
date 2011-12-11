package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class ContaReferenciaListSwitches {

	private String nomeConta;
	private String nomeBanco;

	public String getNomeConta() {
		return nomeConta;
	}

	@CommandLineSwitch(index = 1)
	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	@CommandLineSwitch(index = 2)
	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

}
