package br.zero.androidhelpers.databasestructure.table;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import br.zero.androidhelpers.databasestructure.raw.RawDatabaseObjectStructure;

public class TableToRawSQLAdapter implements RawDatabaseObjectStructure {

	private TableStructure table;
	private List<? extends Object> initialData;

	public TableToRawSQLAdapter(TableStructure table) {
		this(table, null);
	}
	
	public TableToRawSQLAdapter(TableStructure table, List<? extends Object> initialData) {
		this.table = table;
		this.initialData = initialData;
	}

	public List<String> getObjectCreationSQLs() throws DatabaseStructureException {
		List<String> sqls = new ArrayList<String>();
		
		sqls.add(getCreateTableStatement());
		
		createInitialDataStatements(sqls);
		
		return sqls;
	}

	private String getCreateTableStatement() {
		StringBuilder s = new StringBuilder();
		
		s.append("create table " + table.getName());

		if (table.getFields().length > 0) {
			putFields(s, table.getFields());
		}
		
		s.append(";");
		
		return s.toString();
	}

	private void putFields(StringBuilder s, Field[] fields) {
		s.append("(");
		
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];

			s.append(field.getName() + " " + field.getType().getSQLiteTypeName());
			
			FieldModifier[] modifiers = field.getModifiers();
			
			if ((modifiers != null) && (modifiers.length > 0)) {
				putModifiers(s, modifiers);
			}
			
			if (i < fields.length - 1) {
				s.append(", ");
			}
		}
		
		s.append(")");
	}

	private void putModifiers(StringBuilder s, FieldModifier[] modifiers) {
		for (FieldModifier modifier : modifiers) {
			s.append(" ");
			s.append(modifier.toString());
		}
	}

	private void createInitialDataStatements(List<String> sqls) throws DatabaseStructureException {
		for (Object obj : initialData) {
			String insertIntoStatement = createInsertIntoStatement(obj);
			
			sqls.add(insertIntoStatement);
		}
		
	}

	private String createInsertIntoStatement(Object obj) throws DatabaseStructureException {
		StringBuilder s = new StringBuilder();
		
		s.append("insert into " + table.getName() + " (");
		
		putFieldValues(obj, s);
		
		s.append(");");
		
		return s.toString();
	}

	@SuppressWarnings("unchecked")
	private void putFieldValues(Object obj, StringBuilder s)
			throws DatabaseStructureException {
		Field[] fields = table.getFields();
		
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			
			Method getter = field.getGetter();
			
			Object value = getData(obj, getter);
			
			@SuppressWarnings("rawtypes")
			FieldType type = field.getType();
			
			if ((value != null) && (!(type.getJavaCorrespondingType().equals(value.getClass())))) {
				throw new DatabaseStructureException("Tipo do getter n‹o corresponde ao tipo do campo definido na cria‹o da estrutura da tabela.");
			}
			
			// O if acima garante que nunca vai acontecer problema aqui
			s.append(type.formatToSQL(value));
			
			if (i < fields.length) {
				s.append(", ");
			}
		}
	}

	private Object getData(Object obj, Method getter)
			throws DatabaseStructureException {
		Object value;
		
		try {
			value = getter.invoke(obj);
		} catch (Exception e) {
			throw new DatabaseStructureException(e);
		}
		return value;
	}

	public List<String> getUpgradeSQL(int oldVersion, int newVersion) throws DatabaseStructureException {
		return null;
	}

}
