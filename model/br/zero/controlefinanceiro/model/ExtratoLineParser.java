package br.zero.controlefinanceiro.model;

import br.zero.controlefinanceiro.utils.ExtratoParserException;

public interface ExtratoLineParser {

	void parse(String line) throws ExtratoParserException;

	ExtratoLine getLine();
	
	Exception getThrewException();
}
