package br.zero.fin;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import br.zero.fin.datasource.ContaDataSource;
import br.zero.fin.datasource.LancamentoDataSource;
import br.zero.fin.model.Conta;
import br.zero.fin.model.modelo.Lancamento;

public class LancAdd extends Activity {

	private Cursor contaOrigemCursor;
	private Cursor contaDestinoCursor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lanc_add);

		Spinner spinnerContaOrigem = (Spinner) findViewById(R.id.spinnerContaOrigem);

		contaOrigemCursor = setSpinnerContaAdapter(spinnerContaOrigem);

		Spinner spinnerContaDestino = (Spinner) findViewById(R.id.spinnerContaDestino);

		contaDestinoCursor = setSpinnerContaAdapter(spinnerContaDestino);
	}

	private Cursor setSpinnerContaAdapter(Spinner spinner) {
		ContaDataSource contaDataSource = new ContaDataSource();

		Cursor cursor = contaDataSource.getCursorForAll();

		SpinnerAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_spinner_item, cursor,
				new String[] { "nome" }, new int[] { android.R.id.text1 }, 0);

		spinner.setAdapter(adapter);
		
		return cursor;
	}

	public void okClick(View view) {
		Lancamento lancamento = new Lancamento();

		ContaDataSource contaDataSource = new ContaDataSource();

		Conta origem = contaDataSource.convertCurrentPositionToModel(contaOrigemCursor);

		lancamento.setOrigem(origem);

		Conta destino = contaDataSource.convertCurrentPositionToModel(contaDestinoCursor);

		lancamento.setDestino(destino);

		LancamentoDataSource lancamentoSource = new LancamentoDataSource();

		lancamentoSource.create(lancamento);
	}

	public void cancelClick(View view) {
		finish();
	}
}