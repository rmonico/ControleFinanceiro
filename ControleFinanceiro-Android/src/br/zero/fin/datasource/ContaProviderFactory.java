package br.zero.fin.datasource;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.zero.android.helpers.appdefaults.AppDefaults;
import br.zero.androidhelpers.datasource.ProviderFactory;
import br.zero.fin.database.ContaTableStructure;
import br.zero.fin.model.Conta;

public class ContaProviderFactory implements ProviderFactory<Conta> {
	
	public Conta convertCurrentPositionToModel(Cursor cursor) {
		Conta conta = new Conta();
		
		conta.setId(cursor.getInt(0));
		conta.setNome(cursor.getString(1));
		conta.setObservacao(cursor.getString(2));
		
		return conta;
	}

	public Cursor getCursorForAll() {
		SQLiteOpenHelper helper = AppDefaults.get.createHelper();
		
		SQLiteDatabase database = helper.getWritableDatabase();
		
		// TODO Esta Ž a œnica coisa que Ž espec’fica desta classe. Pode ser passada por invers‹o de controle
		ContaTableStructure structure = ContaTableStructure.get();
		
		Cursor cursor = database.query(structure.getName(), structure.getFieldNames(), null, null, null, null, null);
		
		return cursor;
	}

}
