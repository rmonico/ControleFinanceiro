package br.zero.controlefinanceiro.model;

public interface ExtratoLineParser {

	void parse(String line);

	ExtratoLine getLine();
	
	Exception getThrewException();
}
