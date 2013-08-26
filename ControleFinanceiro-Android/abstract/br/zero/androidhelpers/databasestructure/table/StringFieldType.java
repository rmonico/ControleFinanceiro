package br.zero.androidhelpers.databasestructure.table;

class StringFieldType extends ConcreteFieldType<String> {

	StringFieldType() {
		super("text", String.class);
	}
	
	@Override
	public String formatToSQL(String value) {
		if (value == null) {
			return "NULL";
		}
		
		// TODO Testar com valores com "
		value = value.replaceAll("\"", "");
		
		return "'" + value.toString() + "'";
	}

}
