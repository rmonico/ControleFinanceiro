package br.zero.androidhelpers.databasestructure.table;

class BooleanFieldType extends ConcreteFieldType<Boolean> {

	BooleanFieldType() {
		super("boolean", Boolean.class);
	}

	public String formatToSQL(Boolean value) {
		if (value == null) {
			return "NULL";
		}
		
		return value ? "1" : "0";
	}

}
