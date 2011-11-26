package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class ModeloCloneSwitches {

	private String modeloBase;
	private String modeloNovo;
	private String modeloNovoObservacao;

	public String getModeloBase() {
		return modeloBase;
	}

	@CommandLineSwitch(index = 1)
	public void setModeloBase(String modeloBase) {
		this.modeloBase = modeloBase;
	}

	public String getModeloNovo() {
		return modeloNovo;
	}

	@CommandLineSwitch(index = 2)
	public void setModeloNovo(String modeloNovo) {
		this.modeloNovo = modeloNovo;
	}

	public String getModeloNovoObservacao() {
		return modeloNovoObservacao;
	}

	@CommandLineSwitch(index = 3)
	public void setModeloNovoObservacao(String modeloNovoObservacao) {
		this.modeloNovoObservacao = modeloNovoObservacao;
	}

}
