package br.zero.controlefinanceiro.utils;

import br.zero.controlefinanceiro.abstractextratoparser.ExtratoLineParser;
import br.zero.controlefinanceiro.model.ContaDAO;

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
		ContaDAO.registerExtratoParser("itau", ExtratoParsers.ITAU_EXTRATO_PARSER);
		ContaDAO.registerExtratoParser("santander", ExtratoParsers.SANTANDER_EXTRATO_PARSER);
	}

}
