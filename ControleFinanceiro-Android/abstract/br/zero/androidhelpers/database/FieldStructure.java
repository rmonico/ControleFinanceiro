package br.zero.androidhelpers.database;

public class FieldStructure {

	private String name;
	private FieldType type;
	private FieldModifier[] modifiers;

	public FieldStructure(String name, FieldType type) {
		this(name, type, (FieldModifier[]) null);
	}

	public FieldStructure(String name, FieldType type,
			FieldModifier... modifiers) {
		this.name = name;
		this.type = type;
		this.modifiers = modifiers;
	}

	String getName() {
		return name;
	}

	FieldType getType() {
		return type;
	}

	FieldModifier[] getModifiers() {
		return modifiers;
	}
}
