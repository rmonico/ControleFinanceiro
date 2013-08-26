package br.zero.androidhelpers.databasestructure.table;

import java.text.NumberFormat;
import java.util.Locale;

class RealFieldType extends ConcreteFieldType<Double> {

	private NumberFormat formatter;

	RealFieldType() {
		super("real", Double.class);
		formatter = NumberFormat.getNumberInstance(Locale.US);
	}

	@Override
	public String formatToSQL(Double value) {
		if (value == null) {
			return "NULL";
		}
		
		return formatter.format(value);
	}

}
