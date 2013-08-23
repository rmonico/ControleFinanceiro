package br.zero.fin.datasource;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
		SQLiteDatabase database = getDatabase();
		
		ContentValues values = new ContentValues();
		values.put("origemid", obj.getOrigem().getId());
		values.put("destinoid", obj.getDestino().getId());
		values.put("valor", obj.getValor());
		values.put("observacao", obj.getObservacao());
		
		database.insert("lancamento", null, values);
	}

}
