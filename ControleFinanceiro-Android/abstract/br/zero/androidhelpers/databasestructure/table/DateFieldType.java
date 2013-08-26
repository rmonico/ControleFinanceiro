package br.zero.androidhelpers.databasestructure.table;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

class DateFieldType extends ConcreteFieldType<Calendar> {

	private SimpleDateFormat sdf;

	DateFieldType() {
		super("date", Calendar.class);
		
//		sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss.SSSS", Locale.US);
		sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
	}

	public String formatToSQL(Calendar value) {
		if (value == null) {
			return "NULL";
		}
		
		return sdf.format(value.getTime());
	}

}
