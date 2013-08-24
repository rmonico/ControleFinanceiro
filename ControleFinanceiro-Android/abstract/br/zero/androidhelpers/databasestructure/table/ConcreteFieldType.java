package br.zero.androidhelpers.databasestructure.table;

class ConcreteFieldType implements FieldType {
	private String toString;

	ConcreteFieldType(String toString) {
		this.toString = toString;
	}
	
	@Override
	public String toString() {
		return toString;
	}
}