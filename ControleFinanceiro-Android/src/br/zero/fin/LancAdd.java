package br.zero.fin;

import java.text.NumberFormat;
import java.text.ParseException;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import br.zero.fin.database.ContaTableStructure;
import br.zero.fin.datasource.ContaProviderFactory;
import br.zero.fin.datasource.LancamentoDataSource;
import br.zero.fin.model.Conta;
import br.zero.fin.model.modelo.Lancamento;

public class LancAdd extends Activity {

	private Cursor contaOrigemCursor;
	private Cursor contaDestinoCursor;
	private EditText editTextValor;
	private EditText editTextObservacao;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lanc_add);

		Spinner spinnerContaOrigem = (Spinner) findViewById(R.id.spinnerContaOrigem);

		contaOrigemCursor = setSpinnerContaAdapter(spinnerContaOrigem);

		Spinner spinnerContaDestino = (Spinner) findViewById(R.id.spinnerContaDestino);

		contaDestinoCursor = setSpinnerContaAdapter(spinnerContaDestino);
		
		editTextValor = (EditText) findViewById(R.id.editTextValor);
		
		editTextObservacao = (EditText) findViewById(R.id.editTextObservacao);
	}

	private Cursor setSpinnerContaAdapter(Spinner spinner) {
		ContaProviderFactory provider = new ContaProviderFactory();
		
		Cursor cursor = provider.getCursorForAll();

		SpinnerAdapter adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_spinner_item, cursor,
				new String[] { ContaTableStructure.get().nome.getName() }, new int[] { android.R.id.text1 }, 0);

		spinner.setAdapter(adapter);
		
		return cursor;
	}

	public void okClick(View view) {
		Lancamento lancamento = new Lancamento();

		ContaProviderFactory provider = new ContaProviderFactory();

		Conta origem = provider.convertCurrentPositionToModel(contaOrigemCursor);

		lancamento.setOrigem(origem);

		Conta destino = provider.convertCurrentPositionToModel(contaDestinoCursor);

		lancamento.setDestino(destino);

		LancamentoDataSource lancamentoSource = new LancamentoDataSource();
		
		String valorText = editTextValor.getText().toString();
		
		double valor = 0.0;
		
		try {
			valor = NumberFormat.getNumberInstance().parse(valorText).doubleValue();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		lancamento.setValor(valor);
		
		String observacao = editTextObservacao.getText().toString();
		
		if (!observacao.isEmpty()) {
			lancamento.setObservacao(observacao);
		}
		
		lancamentoSource.create(lancamento);
		
		finish();
	}

	public void cancelClick(View view) {
		finish();
	}
}