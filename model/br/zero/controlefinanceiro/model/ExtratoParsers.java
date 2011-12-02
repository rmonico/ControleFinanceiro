package br.zero.controlefinanceiro.model;

import java.util.Calendar;

/**
 * Apenas instancia os parsers de extrato para que possam ser utilizados.
 * 
 * @author Rafael Monico
 * 
 */
class ExtratoParsers {
	private static final ExtratoParser EXTRATO_ITAU_PARSER = createItauParser();
	
	private static ExtratoParser createItauParser() {
		ExtratoParser itauParser = new ExtratoParser() {
			
			@Override
			public void parse(String line) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Calendar getData() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		return null;
	}
}
