package br.zero.androidhelpers.datasource;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public interface ProviderFactory<T> {

	T convertCurrentPositionToModel(Cursor cursor);
	
	Cursor getCursorForAll(SQLiteDatabase database);
}
