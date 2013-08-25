package br.zero.androidhelpers.databasestructure.table;

import java.lang.reflect.Method;

public class Field {

	private String name;
	private FieldType type;
	private FieldModifier[] modifiers;
	private Method getter;
	private Method setter;

	public Field(String name, FieldType type, Class<?> beanClass, String getterName, String setterName) throws DatabaseStructureException {
		this(name, type, beanClass, getterName, setterName, (FieldModifier[]) null);
	}

	public Field(String name, FieldType type, Class<?> beanClass, String getterName, String setterName, FieldModifier... modifiers) throws DatabaseStructureException {
		this.name = name;
		this.type = type;
		this.modifiers = modifiers;
		
		try {
			getter = beanClass.getMethod(getterName, (Class<?>) null);

			Class<?> setterParameterType = type.getJavaCorrespondingType();
			
			setter = beanClass.getMethod(setterName, setterParameterType);
		} catch (NoSuchMethodException e) {
			throw new DatabaseStructureException(e);
		}
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

	public static Field new_IdField(Class<?> beanClass) throws DatabaseStructureException {
		return new Field("_id", FieldType.INTEGER, beanClass, "getID", "setID", 
				FieldModifier.PRIMARY_KEY, FieldModifier.AUTO_INCREMENT);
	}

	public Method getGetter() {
		return getter;
	}

	public Method getSetter() {
		return setter;
	}

}
