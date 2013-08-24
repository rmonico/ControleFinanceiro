package br.zero.androidhelpers.databasestructure.table;

public class Field {

	private String name;
	private FieldType type;
	private FieldModifier[] modifiers;

	public Field(String name, FieldType type) {
		this(name, type, (FieldModifier[]) null);
	}

	public Field(String name, FieldType type,
			FieldModifier... modifiers) {
		this.name = name;
		this.type = type;
		this.modifiers = modifiers;
	}

	public String getName() {
		return name;
	}

	public FieldType getType() {
		return type;
	}

	/**
	 * May be null.
	 * @return
	 */
	public FieldModifier[] getModifiers() {
		return modifiers;
	}

	public static Field new_IdField() {
		return new Field("_id", FieldType.INTEGER,
				FieldModifier.PRIMARY_KEY, FieldModifier.AUTO_INCREMENT);
	}
}
