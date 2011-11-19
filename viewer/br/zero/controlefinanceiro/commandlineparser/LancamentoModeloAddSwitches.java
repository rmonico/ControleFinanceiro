package br.zero.controlefinanceiro.commandlineparser;

import java.util.Calendar;

import br.zero.commandlineparser.CommandLineSwitch;

public class LancamentoModeloAddSwitches {
	private String modelo;
	private Calendar data;
	private String contaOrigem;
	private String contaDestino;
	private double valor;
	private String Observacao;

	public String getModelo() {
		return modelo;
	}

	@CommandLineSwitch(index = 1)
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Calendar getData() {
		return data;
	}

	@CommandLineSwitch(index = 2, parser="UtilsParser.parseCalendar")
	public void setData(Calendar data) {
		this.data = data;
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

	public double getValor() {
		return valor;
	}

	@CommandLineSwitch(index = 5, parser="PrimitiveParsers.parseDouble")
	public void setValor(double valor) {
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
