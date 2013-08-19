package br.zero.fin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;


public class MainMenu extends Activity implements OnItemClickListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.mainmenu);
		
		View view = findViewById(R.id.listView1);
		
		ListView listView = (ListView) view;
		
		listView.setOnItemClickListener(this);
	}

	public void onItemClick(AdapterView<?> list, View item, int arg2, long arg3) {
		TextView textView = (TextView) item;
		String selectedItemText = textView.getText().toString();

		if ("lanc-add".equals(selectedItemText)) {
			callLancAdd();
		} else if ("lanc-addfull".equals(selectedItemText)) {
			callLancAddFull();
		} else if ("sair".equals(selectedItemText)) {
			finish();
		} else {
			assert false;
		}
	}

	private void callLancAdd() {
		Intent intent = new Intent(this, LancAdd.class);

		startActivity(intent);
	}

	private void callLancAddFull() {
		Intent intent = new Intent(this, LancAddFull.class);

		startActivity(intent);
	}

}
