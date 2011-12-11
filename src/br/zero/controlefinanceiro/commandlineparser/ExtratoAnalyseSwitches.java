package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class ExtratoAnalyseSwitches {

	private String nomeBanco;
	private boolean realize;

	@CommandLineSwitch(index = 1)
	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}
	
	
	@CommandLineSwitch(index=2, param="--realize", defaultValue="False")
	public void setRealize(boolean value) {
		realize = value;
	}
	
	public boolean getRealize() {
		return realize;
	}

}
