package br.zero.controlefinanceiro.abstractextratoparser;

import java.util.HashMap;
import java.util.Map;

import br.zero.controlefinanceiro.model.Conta;
import br.zero.controlefinanceiro.utils.ItauExtratoParser;
import br.zero.controlefinanceiro.utils.SantanderExtratoParser;

/**
 * Apenas instancia os parsers de extrato para que possam ser utilizados.
 * 
 * @author Rafael Monico
 * 
 */
public class ExtratoParsers {

	public static final ExtratoLineParser ITAU_EXTRATO_PARSER = new ItauExtratoParser();
	public static final ExtratoLineParser SANTANDER_EXTRATO_PARSER = new SantanderExtratoParser();

	public static void registerParsers() {
		registerExtratoParser("itau", ExtratoParsers.ITAU_EXTRATO_PARSER);
		registerExtratoParser("santander", ExtratoParsers.SANTANDER_EXTRATO_PARSER);
	}

	private static Map<String, ExtratoLineParser> extratoParsers = new HashMap<String, ExtratoLineParser>();

	private static void registerExtratoParser(String parserName, ExtratoLineParser extratoParser) {
		extratoParsers.put(parserName, extratoParser);
	}

	public static ExtratoLineParser getParser(Conta conta) {
		return extratoParsers.get(conta.getNome());
	}

}
