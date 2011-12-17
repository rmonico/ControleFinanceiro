package br.zero.controlefinanceiro.model;

import java.util.Calendar;

public interface ExtratoTransactionLine extends ParsedExtratoLancamento {
	String getReferencia();

	Double getValor();

	Calendar getData();
}
