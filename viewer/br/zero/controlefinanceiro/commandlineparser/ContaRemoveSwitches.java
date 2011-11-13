package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class ContaRemoveSwitches {
	private Integer contaId;

	public Integer getContaId() {
		return contaId;
	}

	@CommandLineSwitch(index=1)
	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}
	
	
}
