package br.zero.controlefinanceiro.utils;

import br.zero.controlefinanceiro.model.ExtratoTransactionLine;

public class ConcreteExtratoTransactionLine implements ExtratoTransactionLine {

	private String referencia;
	private Double valor;

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

}
