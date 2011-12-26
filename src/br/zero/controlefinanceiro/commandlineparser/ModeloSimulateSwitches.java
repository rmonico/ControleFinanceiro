package br.zero.controlefinanceiro.commandlineparser;

import java.util.List;

import br.zero.commandlineparser.CommandLineSwitch;

public class ModeloSimulateSwitches {

	private List<ModeloSimulate> modelo;

	public List<ModeloSimulate> getModelo() {
		return modelo;
	}

	@CommandLineSwitch(parser = "", complexParser = true)
	public void setModelo(List<ModeloSimulate> modelo) {
		this.modelo = modelo;
	}

}
