package br.zero.fin.database;

import br.zero.androidhelpers.databasestructure.table.AbstractTableStructure;
import br.zero.androidhelpers.databasestructure.table.Field;
import br.zero.androidhelpers.databasestructure.table.FieldType;

public class ModeloModeloTableStructure extends AbstractTableStructure {

	public static final ModeloModeloTableStructure get = new ModeloModeloTableStructure();
	
	public String getName() {
		return "modelo_modelo";
	}

	protected Field[] createFields() {
		Field nome = new Field("nome", FieldType.TEXT);
		Field observacao = new Field("observacao",
				FieldType.TEXT);

		return new Field[] { Field.new_IdField(), nome, observacao };
	}

}
