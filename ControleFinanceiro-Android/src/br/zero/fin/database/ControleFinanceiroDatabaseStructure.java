package br.zero.fin.database;

import java.util.ArrayList;
import java.util.List;

import br.zero.androidhelpers.databasestructure.raw.DatabaseStructure;
import br.zero.androidhelpers.databasestructure.raw.RawDatabaseObjectStructure;
import br.zero.androidhelpers.databasestructure.table.TableToRawSQLAdapter;
import br.zero.fin.model.Conta;
import br.zero.fin.model.modelo.Modelo;

public class ControleFinanceiroDatabaseStructure implements DatabaseStructure {

	private int nextId;
	
	public static final ControleFinanceiroDatabaseStructure get = new ControleFinanceiroDatabaseStructure();

	public String getName() {
		return "controlefinanceiro.sqlite";
	}

	public Integer getVersion() {
		return 1;
	}

	public void populateRawObjectStructures(
			List<RawDatabaseObjectStructure> objects) {
		objects.add(new TableToRawSQLAdapter(ModeloModeloTableStructure.get, createModeloModeloInitialData()));
		objects.add(new TableToRawSQLAdapter(ContaTableStructure.get, createContaInitialData()));
	}

	private List<Modelo> createModeloModeloInitialData() {
		List<Modelo> data = new ArrayList<Modelo>();
		
		Modelo modelo = new Modelo();
		modelo.setId(1);
		modelo.setNome("Main");
		
		data.add(modelo);
		
		return data;
	}

	private List<Conta> createContaInitialData() {
		List<Conta> data = new ArrayList<Conta>();
		
		nextId = 1;
		
		data.add(newConta("academia"));
		data.add(newConta("aesa"));
		data.add(newConta("almoco"));
		data.add(newConta("cabelo"));
		data.add(newContaContabilizavel("carteira"));
		data.add(newConta("casa"));
		data.add(newConta("claro - 7653-0794"));
		data.add(newConta("claro - 96587-4452"));
		data.add(newConta("claro tv"));
		data.add(newConta("conferencia"));
		data.add(newConta("despesas bancarias"));
		data.add(newConta("diversos"));
		data.add(newConta("emprestimo"));
		data.add(newConta("greenline"));
		data.add(newConta("inss"));
		data.add(newConta("internet"));
		data.add(newContaContabilizavel("itau"));
		data.add(newConta("itau credito"));
		data.add(newConta("lanche"));
		data.add(newConta("luz"));
		data.add(newConta("medico"));
		data.add(newConta("metro"));
		data.add(newContaContabilizavel("salario"));
		data.add(newConta("santander credito"));
		data.add(newConta("semasa"));
		data.add(newConta("vivo"));
		
		return data;
	}

	
	private Conta newContaContabilizavel(String nome) {
		Conta conta = newConta(nome);
		
		// TODO Classe do modelo desatualizada
//		conta.setContabilizavel(true);
		
		return conta;
	}

	private Conta newConta(String nome) {
		Conta conta = new Conta();
		
		// TODO Testar o ++ nessa situação
		conta.setId(nextId++);
		conta.setNome(nome);
		
		return conta;
	}
	
}
