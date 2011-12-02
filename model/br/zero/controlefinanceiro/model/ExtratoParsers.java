package br.zero.controlefinanceiro.model;

import java.util.Calendar;

/**
 * Apenas instancia os parsers de extrato para que possam ser utilizados.
 * 
 * @author Rafael Monico
 * 
 */
class ExtratoParsers {
	private static final ExtratoParser ITAU_EXTRATO_PARSER = createItauParser();
	private static final ExtratoParser SANTANDER_EXTRATO_PARSER = createSantanderParser();
	
	{
		ContaDAO.registerExtratoParser("itau", ITAU_EXTRATO_PARSER);
		ContaDAO.registerExtratoParser("santander", SANTANDER_EXTRATO_PARSER);
	}
	
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
		
		return itauParser;
	}

	private static ExtratoParser createSantanderParser() {
		ExtratoParser santanderParser = new ExtratoParser() {
			
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
		
		return santanderParser;
	}
}
