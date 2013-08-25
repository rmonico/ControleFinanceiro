package br.zero.fin.database;

import br.zero.androidhelpers.databasestructure.table.AbstractTableStructure;
import br.zero.androidhelpers.databasestructure.table.DatabaseStructureException;
import br.zero.androidhelpers.databasestructure.table.Field;
import br.zero.androidhelpers.databasestructure.table.FieldType;
import br.zero.fin.model.modelo.Modelo;

public class ModeloModeloTableStructure extends AbstractTableStructure {

	private static ModeloModeloTableStructure get;
	public final Field nome;
	public final Field observacao;

	public ModeloModeloTableStructure() throws DatabaseStructureException {
		super(Modelo.class);

		nome = new Field("nome", FieldType.TEXT, Modelo.class, "getNome", "setNome");
		observacao = new Field("observacao", FieldType.TEXT, Modelo.class, "getObservacao", "setObservacao");
	}
	
	public static final ModeloModeloTableStructure get() {
		try {
			return get == null ? get = new ModeloModeloTableStructure() : get;
		} catch (DatabaseStructureException e) {
			throw new RuntimeException(e);
		}
	}

	public String getName() {
		return "modelo_modelo";
	}

	protected Field[] createFields() {
		return new Field[] { _id, nome, observacao };
	}

}
