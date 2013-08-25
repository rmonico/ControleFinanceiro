package br.zero.androidhelpers.datasource;

public abstract class AbstractDataSource<T> implements DataSource<T> {

	public abstract void create(T obj);
}
