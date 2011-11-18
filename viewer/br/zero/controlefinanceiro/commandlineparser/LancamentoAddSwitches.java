package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class LancamentoAddSwitches {
	private String contaOrigem;
	private String contaDestino;
	private double valor;
	private String observacao;

	public String getContaOrigem() {
		return contaOrigem;
	}

	@CommandLineSwitch(index = 1)
	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	@CommandLineSwitch(index = 2)
	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public double getValor() {
		return valor;
	}

	@CommandLineSwitch(index = 3, parser="PrimitiveParsers.parseDouble")
	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	@CommandLineSwitch(index = 4)
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
