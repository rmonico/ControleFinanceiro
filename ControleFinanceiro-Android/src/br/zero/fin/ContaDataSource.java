package br.zero.fin;

import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import br.zero.fin.database.DBHelper;
import br.zero.fin.database.DatabaseStructure;
import br.zero.fin.model.Conta;

public class ContaDataSource extends AbstractDataSource<Conta> {
	
	public ContaDataSource() {
		super("conta", new String[] {"id", "nome", "observacao"});
	}

	public Conta getContaByName(String nomeConta) {
		return null;
	}

	@Override
	public Cursor getCursorForAll() {
		// TODO Testar Application.getAppContext, est‡ definido nesta app
		DBHelper dbHelper = new DBHelper(Application.getAppContext(), DatabaseStructure.instance);
		
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		
		Cursor cursor = database.query(getTableName(), getAllColumns(), null, null, null, null, null);
		
		return cursor;
	}
	
	@Override
	public List<Conta> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
