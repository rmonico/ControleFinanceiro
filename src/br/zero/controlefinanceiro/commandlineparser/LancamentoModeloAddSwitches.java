package br.zero.controlefinanceiro.commandlineparser;

import br.zero.commandlineparser.CommandLineSwitch;

public class LancamentoModeloAddSwitches {
	private String modelo;
	private Integer diaVencimento;
	private String contaOrigem;
	private String contaDestino;
	private Double valor;
	private String Observacao;

	public String getModelo() {
		return modelo;
	}

	@CommandLineSwitch(index = 1)
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getDiaVencimento() {
		return diaVencimento;
	}

	@CommandLineSwitch(index = 2, parser="PrimitiveParsers.parseInteger")
	public void setDiaVencimento(Integer diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public String getContaOrigem() {
		return contaOrigem;
	}

	@CommandLineSwitch(index = 3)
	public void setContaOrigem(String contaOrigem) {
		this.contaOrigem = contaOrigem;
	}

	public String getContaDestino() {
		return contaDestino;
	}

	@CommandLineSwitch(index = 4)
	public void setContaDestino(String contaDestino) {
		this.contaDestino = contaDestino;
	}

	public Double getValor() {
		return valor;
	}

	@CommandLineSwitch(index = 5, parser="PrimitiveParsers.parseDouble")
	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return Observacao;
	}

	@CommandLineSwitch(index = 6)
	public void setObservacao(String observacao) {
		Observacao = observacao;
	}

}
