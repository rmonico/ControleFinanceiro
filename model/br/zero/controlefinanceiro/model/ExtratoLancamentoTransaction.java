package br.zero.controlefinanceiro.model;

import java.util.Calendar;

public interface ExtratoLancamentoTransaction extends ParsedExtratoLancamento {
	String getReferencia();

	Double getValor();

	Calendar getData();
}
