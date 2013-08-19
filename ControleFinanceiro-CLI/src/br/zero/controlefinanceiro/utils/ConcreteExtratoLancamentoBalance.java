package br.zero.controlefinanceiro.utils;

import java.util.Calendar;

import br.zero.controlefinanceiro.abstractextratoparser.ExtratoLancamentoBalance;

public class ConcreteExtratoLancamentoBalance extends AbstractParsedExtratoLancamento implements ExtratoLancamentoBalance {

	private Calendar data;

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

}
