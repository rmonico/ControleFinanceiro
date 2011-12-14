package br.zero.controlefinanceiro.model;

public interface ExtratoParser {

	void parse(String line);

	ExtratoTransactionLine getLine();
	
	Exception getThrewException();
}
