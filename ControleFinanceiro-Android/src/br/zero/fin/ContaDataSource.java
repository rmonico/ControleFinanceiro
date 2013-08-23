package br.zero.fin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.zero.fin.database.DBHelper;
import br.zero.fin.database.DatabaseStructure;
import br.zero.fin.model.Conta;

public class ContaDataSource extends AbstractDataSource<Conta> {
	
	public ContaDataSource() {
		// TODO Mover isso para uma classe de structure que agora est� no DBHelper anonima
		super("conta", new String[] {"_id", "nome", "observacao"});
	}
	
	public Conta convertCurrentPositionToModel(Cursor cursor) {
		Conta conta = new Conta();
		
		conta.setId(cursor.getInt(0));
		conta.setNome(cursor.getString(1));
		conta.setObservacao(cursor.getString(2));
		
		return conta;
	}

	@Override
	public Cursor getCursorForAll() {
		DBHelper dbHelper = new DBHelper(Application.getAppContext(), DatabaseStructure.instance);
		
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		
		Cursor cursor = database.query(getTableName(), getAllColumns(), null, null, null, null, null);
		
		return cursor;
	}
	
}
