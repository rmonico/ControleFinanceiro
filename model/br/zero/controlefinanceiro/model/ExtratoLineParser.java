package br.zero.controlefinanceiro.model;

import br.zero.controlefinanceiro.utils.ExtratoLineParserException;

public interface ExtratoLineParser {

	void parse(String line) throws ExtratoLineParserException;

	ParsedExtratoLancamento getLine();
}
