package br.zero.fin.datasource;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.zero.android.helpers.appdefaults.AppDefaults;
import br.zero.androidhelpers.datasource.AbstractDataSource;
import br.zero.fin.model.modelo.Lancamento;

public class LancamentoDataSource extends AbstractDataSource<Lancamento> {

	@Override
	public void create(Lancamento obj) {
		SQLiteOpenHelper helper = AppDefaults.get.createHelper();
		
		SQLiteDatabase database = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("contaorigemid", obj.getOrigem().getID());
		values.put("contadestinoid", obj.getDestino().getID());
		values.put("valor", obj.getValor());
		values.put("observacao", obj.getObservacao());
		
		database.insert("lancamento", null, values);
	}

}
