package br.zero.controlefinanceiro.model;

import br.zero.controlefinanceiro.model.extrato.ExtratoLancamento;
import br.zero.controlefinanceiro.utils.ExtratoLineParserException;

public interface ExtratoLineParser {

	void parse(String line) throws ExtratoLineParserException;
	void parse(ExtratoLancamento line) throws ExtratoLineParserException;
	
	ParsedExtratoLancamento getLine();
}
