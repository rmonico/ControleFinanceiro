package br.zero.controlefinanceiro.utils;

import br.zero.controlefinanceiro.abstractextratoparser.ParsedExtratoLancamento;
import br.zero.controlefinanceiro.model.extrato.ExtratoLancamento;

public abstract class AbstractParsedExtratoLancamento implements ParsedExtratoLancamento {

	private ExtratoLancamento origem;

	public ExtratoLancamento getOrigem() {
		return origem;
	}

	public void setOrigem(ExtratoLancamento origem) {
		this.origem = origem;
	}

}
