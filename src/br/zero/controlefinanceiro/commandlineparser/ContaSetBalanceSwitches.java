package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class ContaSetBalanceSwitches {
	private String contaNome;
	private Double novoSaldo;

	public String getContaNome() {
		return contaNome;
	}

	@CommandLineSwitch(index = 1)
	public void setContaNome(String value) {
		contaNome = value;
	}

	public Double getNovoSaldo() {
		return novoSaldo;
	}

	@CommandLineSwitch(index = 2)
	public void setNovoSaldo(Double novoSaldo) {
		this.novoSaldo = novoSaldo;
	}

}
