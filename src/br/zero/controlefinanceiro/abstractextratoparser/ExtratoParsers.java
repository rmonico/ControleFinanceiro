package br.zero.controlefinanceiro.abstractextratoparser;

import br.zero.controlefinanceiro.model.ContaDAO;
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
		ContaDAO.registerExtratoParser("itau", ExtratoParsers.ITAU_EXTRATO_PARSER);
		ContaDAO.registerExtratoParser("santander", ExtratoParsers.SANTANDER_EXTRATO_PARSER);
	}

}
