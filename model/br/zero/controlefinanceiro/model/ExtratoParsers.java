package br.zero.controlefinanceiro.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
			
			private boolean isTransferLine;
			private Calendar data;
			private ParseException threwException;

			@Override
			public void parse(String line) {
				String[] fields = line.split("\t");
				
				if (fields.length > 8) {
					isTransferLine = false;
					return;
				}
				
				if ("SALDO ANTERIOR".equals(fields[3])) {
					isTransferLine = false;
					return;
				} else if ("S A L D O".equals(fields[3])) {
					isTransferLine = false;
					return;
				} else if ("SDO CTA/APL AUTOMATICAS".equals(fields[3])) {
					isTransferLine = false;
					return;
				}
				
				String dataStr = fields[0];
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
				
				data = GregorianCalendar.getInstance();
				
				try {
					data.setTime(sdf.parse(dataStr));
				} catch (ParseException e) {
					threwException = e;
					isTransferLine = false;
				}
				
				data.set(Calendar.YEAR, GregorianCalendar.getInstance().get(Calendar.YEAR));
				
				isTransferLine = true;
			}
			
			@Override
			public Exception getThrewException() {
				return threwException;
			}
			
			@Override
			public Calendar getData() {
				return isTransferLine ? data : null;
			}

			@Override
			public boolean isTransferLine() {
				return isTransferLine;
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

			@Override
			public boolean isTransferLine() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public Exception getThrewException() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		
		return santanderParser;
	}
}
