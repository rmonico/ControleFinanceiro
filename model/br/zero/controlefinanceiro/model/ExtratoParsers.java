package br.zero.controlefinanceiro.model;

import java.util.Calendar;

/**
 * Apenas instancia os parsers de extrato para que possam ser utilizados.
 * 
 * @author Rafael Monico
 * 
 */
public class ExtratoParsers {
	public static final ExtratoParser ITAU_EXTRATO_PARSER = createItauParser();
	public static final ExtratoParser SANTANDER_EXTRATO_PARSER = createSantanderParser();
	
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
