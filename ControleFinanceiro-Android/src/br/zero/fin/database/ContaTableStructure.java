package br.zero.fin.database;

import br.zero.androidhelpers.databasestructure.table.AbstractTableStructure;
import br.zero.androidhelpers.databasestructure.table.Field;
import br.zero.androidhelpers.databasestructure.table.FieldType;

public class ContaTableStructure extends AbstractTableStructure {
	
	public static final ContaTableStructure get = new ContaTableStructure();
	public final Field nome = new Field("nome", FieldType.TEXT);
	public final Field observacao = new Field("observacao", FieldType.TEXT);
	public final Field contabilizavel = new Field("contabilizavel", FieldType.BOOLEAN);

	@Override
	public String getName() {
		return "conta";
	}

	@Override
	protected Field[] createFields() {
		return new Field[] { _id, nome, observacao, contabilizavel };
	}

}
