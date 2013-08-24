package br.zero.androidhelpers.datasource;

import android.database.Cursor;

public interface DataSource<T> {
	
	Cursor getCursorForAll();

	T convertCurrentPositionToModel(Cursor cursor);
	
	void create(T obj);
}
