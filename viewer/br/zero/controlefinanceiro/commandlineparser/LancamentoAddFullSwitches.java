package br.zero.controlefinanceiro.commandlineparser;

import java.util.Calendar;

import br.zero.commandlineparser.CommandLineSwitch;

public class LancamentoAddFullSwitches {
	private int lancamentoModeloID;
	private Calendar date;
	private String contaOrigem;
	private String contaDestino;
	private double valor;
	private String observacao;


	public int getLancamentoModeloID() {
		return lancamentoModeloID;
	}

	@CommandLineSwitch(index = 1, parser="PrimitiveParsers.parseInteger")
	public void setLancamentoModeloID(int lancamentoModeloID) {
		this.lancamentoModeloID = lancamentoModeloID;
	}

	public Calendar getData() {
		return date;
	}

	@CommandLineSwitch(index = 2, parser="UtilsParser.parseCalendar")
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

	@CommandLineSwitch(index = 5, parser="PrimitiveParsers.parseDouble")
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
