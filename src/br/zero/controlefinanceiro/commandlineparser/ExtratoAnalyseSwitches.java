package br.zero.controlefinanceiro.commandlineparser;

import java.util.ArrayList;
import java.util.List;

import br.zero.commandlineparser.CommandLineSwitch;

public class ExtratoAnalyseSwitches {

	private String nomeBanco;
	private boolean realize;
	private List<ManualReference> manualRefs = new ArrayList<ManualReference>();

	@CommandLineSwitch(index = 1)
	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	@CommandLineSwitch(param = "--realize", defaultValue = "False")
	public void setRealize(boolean value) {
		realize = value;
	}

	public boolean getRealize() {
		return realize;
	}

	@CommandLineSwitch(param = "--refs", complexParser = true, parser = "ManualReferencesParser.parse")
	public List<ManualReference> setManualRefList(List<ManualReference> value) {
		return manualRefs;
	}

	public List<ManualReference> getManualRefList() {
		return manualRefs;
	}

}
