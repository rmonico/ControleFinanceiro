package br.zero.androidhelpers.datasource;

import android.database.Cursor;

public interface ProviderFactory<T> {

	T convertCurrentPositionToModel(Cursor cursor);

	Cursor getCursorForAll();
}
