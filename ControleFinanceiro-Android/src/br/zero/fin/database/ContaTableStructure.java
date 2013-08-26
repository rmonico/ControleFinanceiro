package br.zero.fin.database;

import br.zero.androidhelpers.databasestructure.table.AbstractTableStructure;
import br.zero.androidhelpers.databasestructure.table.DatabaseStructureException;
import br.zero.androidhelpers.databasestructure.table.Field;
import br.zero.androidhelpers.databasestructure.table.FieldType;
import br.zero.androidhelpers.databasestructure.table.ForeignFieldType;
import br.zero.fin.model.Conta;

public class ContaTableStructure extends AbstractTableStructure<Conta> {
	
	private static ForeignFieldType<Conta> foreignFieldType;
	private static ContaTableStructure get;
	public final Field nome;
	public final Field observacao;
	public final Field contabilizavel;
	
	public ContaTableStructure() throws DatabaseStructureException {
		super(Conta.class);
		
		nome = new Field("nome", FieldType.TEXT, Conta.class, "getNome", "setNome");
		observacao = new Field("observacao", FieldType.TEXT, Conta.class, "getObservacao", "setObservacao");
		contabilizavel = new Field("contabilizavel", FieldType.BOOLEAN, Conta.class, "getContabilizavel", "setContabilizavel");
	}

	public static ForeignFieldType<Conta> getForeignFieldType() {
		if (foreignFieldType == null) {
			foreignFieldType = new ForeignFieldType<Conta>(ContaTableStructure.get());
		}
		
		return foreignFieldType;
	}
	
	public static ContaTableStructure get() {
		try {
			return get == null ? get = new ContaTableStructure() : get;
		} catch (DatabaseStructureException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String getName() {
		return "conta";
	}

	@Override
	protected Field[] createFields() {
		return new Field[] { _id, nome, observacao, contabilizavel };
	}

}
