package br.zero.fin;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import br.zero.fin.datasource.LancamentoDataSource;
import br.zero.fin.model.Conta;
import br.zero.fin.model.modelo.Lancamento;

public class LancAdd extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lanc_add);

		setSpinnerContaOrigemAdapter();
	}

	private void setSpinnerContaOrigemAdapter() {
		Spinner spinnerContaOrigem = (Spinner) findViewById(R.id.spinnerContaOrigem);

		ContaDataSource contaDataSource = new ContaDataSource();
		
		Cursor cursor = contaDataSource.getCursorForAll();
				
		SpinnerAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, cursor, new String[] {"nome"}, new int[] { android.R.id.text1 }, 0);
		
		spinnerContaOrigem.setAdapter(adapter);
	}

	public void okClick(View view) {
		Lancamento lancamento = new Lancamento();
		
		ContaDataSource contaDataSource = new ContaDataSource();

		String nomeContaOrigem = getContaOrigem();
		
		Conta origem = contaDataSource.getContaByName(nomeContaOrigem);
		
		lancamento.setOrigem(origem);
		
		String nomeContaDestino = getContaDestino();
		
		Conta destino = contaDataSource.getContaByName(nomeContaDestino);
		
		lancamento.setDestino(destino);

		LancamentoDataSource lancamentoSource = new LancamentoDataSource();

		lancamentoSource.open();
		
		lancamentoSource.create(lancamento);
		
		lancamentoSource.close();
	}

	private String getContaOrigem() {
//		Spinner listView = (Spinner) findViewById(R.id.spinnerContaOrigem);
//		
//		int selectedItemPosition = listView.getSelectedItemPosition();
		
		return "";
	}

	private String getContaDestino() {
		// TODO
		return "";
	}

	public void cancelClick(View view) {
		finish();
	}
}