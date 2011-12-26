package br.zero.controlefinanceiro.commandlineparser;

import java.util.ArrayList;

import br.zero.commandlineparser.CommandLineArgumentParserMethod;
import br.zero.commandlineparser.ComplexParserParameter;
import br.zero.commandlineparser.ComplexParserReturn;

public class ManualReferencesParser {

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

		final ArrayList<ManualReference> list = new ArrayList<ManualReference>();

		for (int i = 0; i < values.length; i += 2) {

			ManualReference mr = new ManualReference();

			mr.setNomeConta(values[i]);
			mr.setRegex(values[i + 1]);

			list.add(mr);
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

	public String getError() {
		return error;
	}
}
