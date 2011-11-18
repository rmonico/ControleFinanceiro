package br.zero.controlefinanceiro.commandlineparser;

import java.util.Calendar;

import br.zero.commandlineparser.CommandLineSwitch;

public class LancamentoAddFullSwitches {
	private String modelo;
	private Calendar date;
	private String contaOrigem;
	private String contaDestino;
	private double valor;
	private String observacao;


	public String getModelo() {
		return modelo;
	}

	@CommandLineSwitch(index = 1)
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Calendar getDate() {
		return date;
	}

	@CommandLineSwitch(index = 2)
	public void setDate(Calendar date) {
		this.date = date;
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

	@CommandLineSwitch(index = 5)
	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getObservacao() {
		return observacao;
	}

	@CommandLineSwitch(index = 6)
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

}
