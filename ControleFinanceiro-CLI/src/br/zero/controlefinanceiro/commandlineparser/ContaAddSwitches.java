package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class ContaAddSwitches {

	private String nome;
	private Boolean contabilizavel = false;
	private String observacao;

	public String getNome() {
		return nome;
	}

	@CommandLineSwitch(index = 1)
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getContabilizavel() {
		return contabilizavel;
	}

	@CommandLineSwitch(param = "--contabilizar")
	public void setContabilizavel(boolean contabilizavel) {
		this.contabilizavel = contabilizavel;
	}

	public String getObservacao() {
		return observacao;
	}

	@CommandLineSwitch(param = { "-obs", "--observacao" })
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
