package br.zero.fin;

import br.zero.androidhelpers.database.DBHelper;
import br.zero.androidhelpers.database.DatabaseStructure;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public abstract class AbstractDataSource<T> implements DataSource<T> {

	private String tableName;
	private String[] allColumns;
	
	public AbstractDataSource(String tableName, String[] allColumns) {
		this.tableName = tableName;
		this.allColumns = allColumns;
	}
	
	protected SQLiteDatabase getDatabase() {
		DBHelper dbHelper = new DBHelper(Application.getAppContext(), DatabaseStructure.instance);
		
		SQLiteDatabase database = dbHelper.getWritableDatabase();
		return database;
	}

	public String getTableName() {
		return tableName;
	}
	
	public String[] getAllColumns() {
		return allColumns;
	}

	public abstract Cursor getCursorForAll();
	
	public abstract T convertCurrentPositionToModel(Cursor cursor);
	
	public abstract void create(T obj);
}
