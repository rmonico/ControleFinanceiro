package br.zero.controlefinanceiro.model;

public interface ExtratoLineParser {

	void parse(String line);

	ExtratoTransactionLine getLine();
	
	Exception getThrewException();
}
