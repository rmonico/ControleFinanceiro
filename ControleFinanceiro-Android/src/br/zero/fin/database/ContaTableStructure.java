package br.zero.fin.database;

import br.zero.androidhelpers.databasestructure.table.AbstractTableStructure;
import br.zero.androidhelpers.databasestructure.table.Field;
import br.zero.androidhelpers.databasestructure.table.FieldType;

public class ContaTableStructure extends AbstractTableStructure {
	
	public static final ContaTableStructure get = new ContaTableStructure();

	@Override
	public String getName() {
		return "conta";
	}

	@Override
	protected Field[] createFields() {
		Field nome = new Field("nome", FieldType.TEXT);
		Field observacao = new Field("observacao", FieldType.TEXT);
		Field contabilizavel = new Field("contabilizavel", FieldType.BOOLEAN);
		
		return new Field[] { Field.new_IdField(), nome, observacao, contabilizavel };
	}

}
