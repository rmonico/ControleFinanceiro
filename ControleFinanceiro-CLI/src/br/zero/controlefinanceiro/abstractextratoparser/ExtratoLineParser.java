package br.zero.controlefinanceiro.abstractextratoparser;

import br.zero.controlefinanceiro.model.extrato.ExtratoLancamento;
import br.zero.controlefinanceiro.utils.ExtratoLineParserException;

public interface ExtratoLineParser {

	ParsedExtratoLancamento parse(String line) throws ExtratoLineParserException;
	ParsedExtratoLancamento parse(ExtratoLancamento line) throws ExtratoLineParserException;
}
