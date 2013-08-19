package br.zero.fin;

import java.util.List;

import android.database.Cursor;

public abstract class AbstractDataSource<T> implements DataSource<T> {

	private String tableName;
	private String[] allColumns;
	
	public AbstractDataSource(String tableName, String[] allColumns) {
		this.tableName = tableName;
		this.allColumns = allColumns;
	}

	public String getTableName() {
		return tableName;
	}
	
	public String[] getAllColumns() {
		return allColumns;
	}

	public abstract Cursor getCursorForAll();
	
	public abstract List<T> getAll();

}
