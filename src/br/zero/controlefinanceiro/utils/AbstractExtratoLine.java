package br.zero.controlefinanceiro.utils;

import br.zero.controlefinanceiro.model.ExtratoLine;

public class AbstractExtratoLine implements ExtratoLine {

	private String original;
	
	@Override
	public String getOriginal() {
		return original;
	}

	public void setOriginal(String value) {
		original = value;
	}
}
