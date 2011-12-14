package br.zero.controlefinanceiro.model;

public interface ExtratoTransactionLine extends ExtratoLine {
	String getReferencia();

	Double getValor();
}
