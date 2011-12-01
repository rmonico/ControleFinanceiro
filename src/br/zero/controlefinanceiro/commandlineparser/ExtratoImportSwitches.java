package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class ExtratoImportSwitches {
	private String nomeConta;
	private String nomeArquivo;

	public String getNomeConta() {
		return nomeConta;
	}

	@CommandLineSwitch(index = 1)
	public void setNomeConta(String nomeConta) {
		this.nomeConta = nomeConta;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	@CommandLineSwitch(index = 2)
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

}
