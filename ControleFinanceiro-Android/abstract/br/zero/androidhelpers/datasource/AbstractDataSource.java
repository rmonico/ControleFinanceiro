package br.zero.androidhelpers.datasource;

import br.zero.androidhelpers.databasestructure.raw.AbstractDBHelper;
import br.zero.androidhelpers.databasestructure.raw.DatabaseStructure;
import br.zero.fin.Application;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class AbstractDataSource<T> implements DataSource<T> {

	protected SQLiteDatabase getDatabase() {
		AbstractDBHelper dbHelper = new AbstractDBHelper(Application.getAppContext(), DatabaseStructure.instance);
		
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		return database;
	}

	public abstract void create(T obj);
}
