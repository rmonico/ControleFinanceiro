package br.zero.fin.datasource;

import android.database.Cursor;
import br.zero.fin.AbstractDataSource;
import br.zero.fin.model.modelo.Lancamento;

public class LancamentoDataSource extends AbstractDataSource<Lancamento> {

	public LancamentoDataSource() {
		// TODO
		super("", null);
	}

	@Override
	public Cursor getCursorForAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lancamento convertCurrentPositionToModel(Cursor cursor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Lancamento obj) {
	}

}
