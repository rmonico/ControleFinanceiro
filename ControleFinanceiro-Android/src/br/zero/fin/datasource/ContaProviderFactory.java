package br.zero.fin.datasource;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.zero.androidhelpers.datasource.ProviderFactory;
import br.zero.fin.database.ContaTableStructure;
import br.zero.fin.model.Conta;

public class ContaProviderFactory implements ProviderFactory<Conta> {
	
	public ContaProviderFactory() {
		
	}

	public Conta convertCurrentPositionToModel(Cursor cursor) {
		Conta conta = new Conta();
		
		conta.setId(cursor.getInt(0));
		conta.setNome(cursor.getString(1));
		conta.setObservacao(cursor.getString(2));
		
		return conta;
	}

	public Cursor getCursorForAll(SQLiteDatabase database) {
		ContaTableStructure structure = ContaTableStructure.get;
		
		Cursor cursor = database.query(structure.getName(), structure.getFieldNames(), null, null, null, null, null);
		
		return cursor;
	}

}
