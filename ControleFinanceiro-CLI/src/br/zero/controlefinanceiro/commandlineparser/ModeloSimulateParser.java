package br.zero.controlefinanceiro.commandlineparser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import br.zero.commandlineparser.CommandLineArgumentParserMethod;
import br.zero.commandlineparser.ComplexParserParameter;
import br.zero.commandlineparser.ComplexParserReturn;

public class ModeloSimulateParser {
	
	private String error;
	
	@CommandLineArgumentParserMethod(messageMethod = "getError")
	public ComplexParserReturn parse(ComplexParserParameter parameter) {

		if (!(parameter.getValuesObject() instanceof String[])) {
			throw new RuntimeException("Tipo de propriedade não suportada por este parser!");
		}

		String[] values = (String[]) parameter.getValuesObject();

		if ((values.length % 2) != 0) {
			throw new RuntimeException("A quantidade de parâmetros deve ser par.");
		}

		final ArrayList<ModeloSimulate> list = new ArrayList<ModeloSimulate>();

		for (int i = 0; i < values.length; i += 2) {

			ModeloSimulate ms = new ModeloSimulate();

			ms.setNomeModelo(values[i]);
			ms.setDataBase(parseDatabase(values[i + 1]));

			list.add(ms);
		}
		
		
		return new ComplexParserReturn() {

			@Override
			public Object getComplexSwitchValue() {
				return list;
			}

			@Override
			public Object getSubObjectValue() {
				return null;
			}

		};
	}

	private Calendar parseDatabase(String s) {
		Calendar c = GregorianCalendar.getInstance();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy");

		try {
			c.setTime(sdf.parse(s));
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		
		return c;
	}

	public String getError() {
		return error;
	}
}
