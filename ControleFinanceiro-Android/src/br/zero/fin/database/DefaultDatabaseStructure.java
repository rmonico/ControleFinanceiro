package br.zero.fin.database;


public class DefaultDatabaseStructure implements DatabaseStructure {
	
	public String getName() {
		return "controlefinanceiro.db";
	}

	public Integer getVersion() {
		return 1;
	}

	public String getDatabaseCreationSQL() {
		// TODO Ler do arquivo controlefinanceiro.sqlite
		String databaseCreation = 
				  "--attach database \"controlefinanceiro.db\" as controlefinanceiro;\n"
						  + "\n"
						  + "create table controlefinanceiro.modelo_modelo (\n"
						  + "  id integer primary key autoincrement,\n"
						  + "  nome text,\n"
						  + "  observacao text);\n"
						  + "\n"
						  + "\n"
						  + "create table controlefinanceiro.conta (\n"
						  + "  id integer primary key autoincrement,\n"
						  + "  nome text,\n"
						  + "  observacao text);\n"
						  + "\n"
						  + "\n"
						  + "create table controlefinanceiro.modelo_lancamento (\n"
						  + "  id integer primary key autoincrement,\n"
						  + "  modeloid integer,\n"
						  + "  diavencimento integer,\n"
						  + "  contaorigemid integer,\n"
						  + "  contadestinoid integer,\n"
						  + "  valor double precision,\n"
						  + "  observacao text,\n"
						  + "\n"
						  + "  FOREIGN KEY(modeloid) REFERENCES \"controlefinanceiro.modelo_modelo\"(id),\n"
						  + "  FOREIGN KEY(contaorigemid) REFERENCES \"controlefinanceiro.conta\"(id),\n"
						  + "  FOREIGN KEY(contadestinoid) REFERENCES \"controlefinanceiro.conta\"(id));\n"
						  + "\n"
						  + "create table controlefinanceiro.extrato_arquivo (\n"
						  + "  id integer primary key autoincrement,\n"
						  + "  conteudo text);\n"
						  + "\n"
						  + "\n"
						  + "create table controlefinanceiro.extrato_lancamento (\n"
						  + "  id integer primary key autoincrement,\n"
						  + "  bancoid integer,\n"
						  + "  original text,\n"
						  + "  arquivoid integer,\n"
						  + "\n"
						  + "  FOREIGN KEY(bancoid) REFERENCES \"controlefinanceiro.conta\"(id),\n"
						  + "  FOREIGN KEY(arquivoid) REFERENCES \"controlefinanceiro.extrato_arquivo\"(id));\n"
						  + "\n"
						  + "\n"
						  + "create table controlefinanceiro.lancamento (\n"
						  + "  id integer primary key autoincrement,\n"
						  + "  lancamentomodeloid integer,\n"
						  + "  data date,\n"
						  + "  contaorigemid integer,\n"
						  + "  contadestinoid integer,\n"
						  + "  valor real,\n"
						  + "  observacao text,\n"
						  + "  n integer,\n"
						  + "  extratoid integer,\n"
						  + "  FOREIGN KEY(lancamentomodeloid) REFERENCES \"controlefinanceiro.modelo_lancamento\"(id),\n"
						  + "  FOREIGN KEY(contaorigemid) REFERENCES \"controlefinanceiro.conta\"(id),\n"
						  + "  FOREIGN KEY(contadestinoid) REFERENCES \"controlefinanceiro.conta\"(id),\n"
						  + "  UNIQUE (n),\n"
						  + "  FOREIGN KEY(extratoid) REFERENCES \"controlefinanceiro.extrato_lancamento\"(id));\n"
						  + "\n"
						  + "\n"
						  + "create table controlefinanceiro.extrato_referenciaextrato (\n"
						  + "  id integer primary key autoincrement,\n"
						  + "  contaid integer,\n"
						  + "  bancoid integer,\n"
						  + "  referencia text,\n"
						  + "  n integer,\n"
						  + "  FOREIGN KEY(contaid) REFERENCES \"controlefinanceiro.conta\"(id),\n"
						  + "  FOREIGN KEY(bancoid) REFERENCES \"controlefinanceiro.conta\"(id),\n"
						  + "  UNIQUE (n));";
		
		return databaseCreation;
						  
	}

	public String getUpgradeSQL(int oldVersion, int newVersion) {
		return "";
	}

}
