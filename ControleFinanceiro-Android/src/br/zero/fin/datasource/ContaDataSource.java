package br.zero.fin.datasource;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.zero.androidhelpers.datasource.AbstractDataSource;
import br.zero.fin.model.Conta;

public class ContaDataSource extends AbstractDataSource<Conta> {
	
	public Conta convertCurrentPositionToModel(Cursor cursor) {
		Conta conta = new Conta();
		
		conta.setId(cursor.getInt(0));
		conta.setNome(cursor.getString(1));
		conta.setObservacao(cursor.getString(2));
		
		return conta;
	}

	@Override
	public Cursor getCursorForAll() {
		SQLiteDatabase database = getDatabase();
		
		Cursor cursor = database.query(getTableName(), getAllColumns(), null, null, null, null, null);
		
		return cursor;
	}

	@Override
	public void create(Conta obj) {
		// TODO Auto-generated method stub
		
	}
	
}
