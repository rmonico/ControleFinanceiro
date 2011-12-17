package br.zero.controlefinanceiro.utils;

import br.zero.controlefinanceiro.model.ParsedExtratoLancamento;

public class AbstractExtratoLine implements ParsedExtratoLancamento {

	private String original;
	
	@Override
	public String getOriginal() {
		return original;
	}

	public void setOriginal(String value) {
		original = value;
	}
}
