package br.zero.controlefinanceiro.model;

import java.util.Calendar;

public interface ExtratoTransactionLine extends ExtratoLine {
	String getReferencia();

	Double getValor();

	Calendar getData();
}
