package br.zero.androidhelpers.databasestructure.table;

class ConcreteFieldModifier implements FieldModifier {

	private String toString;

	public ConcreteFieldModifier(String toString) {
		this.toString = toString;
	}
	
	@Override
	public String toString() {
		return toString;
	}
}
