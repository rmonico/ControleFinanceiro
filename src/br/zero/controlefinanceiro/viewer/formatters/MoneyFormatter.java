package br.zero.controlefinanceiro.viewer.formatters;

import java.text.NumberFormat;

import br.zero.observer.Formatter;
import br.zero.observer.ObserverException;

public class MoneyFormatter implements Formatter {

	@Override
	public String parse(Object value) throws ObserverException {
		if (value == null) {
			return "--";
		}

		if (!(value instanceof Double)) {
			throw new ObserverException("MoneyFormatter: Must be used only with java.lang.Double fields.");
		}

		Double money = (Double) value;
		
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		String finalValue = nf.format(money);

		return finalValue;
	}

}
