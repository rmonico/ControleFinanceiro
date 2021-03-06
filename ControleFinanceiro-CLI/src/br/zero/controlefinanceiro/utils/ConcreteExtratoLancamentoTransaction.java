package br.zero.controlefinanceiro.utils;

import java.util.Calendar;

import br.zero.controlefinanceiro.abstractextratoparser.ExtratoLancamentoTransaction;

public class ConcreteExtratoLancamentoTransaction extends AbstractParsedExtratoLancamento implements ExtratoLancamentoTransaction {

	private String referencia;
	private Double valor;
	private Calendar data;
	
	public void setReferencia(String value) {
		referencia = value;
	}

	@Override
	public String getReferencia() {
		return referencia;
	}

	public void setValor(Double value) {
		valor = value;
	}
	
	@Override
	public Double getValor() {
		return valor;
	}

	public void setData(Calendar value) {
		data = value;
	}
	
	@Override
	public Calendar getData() {
		return data;
	}

}
