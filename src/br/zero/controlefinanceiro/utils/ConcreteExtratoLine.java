package br.zero.controlefinanceiro.utils;

import br.zero.controlefinanceiro.model.ExtratoLine;

public class ConcreteExtratoLine implements ExtratoLine {

	private String referencia;

	public void setReferencia(String value) {
		referencia = value;
	}

	@Override
	public String getReferencia() {
		return referencia;
	}

}
