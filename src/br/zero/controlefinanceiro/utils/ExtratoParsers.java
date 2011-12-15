package br.zero.controlefinanceiro.utils;

import java.text.ParseException;

import br.zero.controlefinanceiro.model.ContaDAO;
import br.zero.controlefinanceiro.model.ExtratoBalanceLine;
import br.zero.controlefinanceiro.model.ExtratoLine;
import br.zero.controlefinanceiro.model.ExtratoLineParser;
import br.zero.controlefinanceiro.model.ExtratoTransactionLine;

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
