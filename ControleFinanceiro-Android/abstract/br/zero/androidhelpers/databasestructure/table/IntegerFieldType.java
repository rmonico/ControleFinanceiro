package br.zero.androidhelpers.databasestructure.table;

class IntegerFieldType extends ConcreteFieldType<Integer> {

	IntegerFieldType() {
		super("integer", Integer.class);
	}

	public String formatToSQL(Integer value) {
		return value.toString();
	}

}
