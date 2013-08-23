package br.zero.fin;

import android.database.Cursor;

public interface DataSource<T> {
	String getTableName();
	
	String[] getAllColumns(); 
	
	Cursor getCursorForAll();

	T convertCurrentPositionToModel(Cursor cursor);
	
	void create(T obj);
}
