package br.zero.fin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import br.zero.fin.datasource.LancamentoDataSource;
import br.zero.fin.model.Conta;
import br.zero.fin.model.modelo.Lancamento;

public class LancAdd extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lanc_add);

		View view = findViewById(R.id.listView1);

		ListView expandableView = (ListView) view;

		List<Map<String, String>> data = new ArrayList<Map<String, String>>();

		Map<String, String> item = new HashMap<String, String>();
		item.put("Key 1", "value 1");
		data.add(item);

		item = new HashMap<String, String>();
		item.put("Key 1", "value 2");
		data.add(item);

		item = new HashMap<String, String>();
		item.put("Key 3", "value 3");
		data.add(item);

		ListAdapter adapter = new SimpleAdapter(this, data,
				android.R.layout.simple_selectable_list_item, new String[] {},
				new int[] {});
		// ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, values);

		expandableView.setAdapter(adapter);
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
		// TODO
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