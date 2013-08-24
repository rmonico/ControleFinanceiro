package br.zero.fin.database;

import java.util.ArrayList;
import java.util.List;

import br.zero.androidhelpers.database.DatabaseRawObjectStructure;
import br.zero.androidhelpers.database.DatabaseStructure;

public class ControleFinanceiroDatabaseStructure implements DatabaseStructure {

	public String getName() {
		return "controlefinanceiro.sqlite";
	}

	public Integer getVersion() {
		return 1;
	}

	public List<TableStructure> getTables() {
		List<TableStructure> tables = new ArrayList<TableStructure>();

		tables.add(createModeloModeloTableStructure());
		tables.add(createContaTableStructure());

		return tables;
	}

	private TableStructure createModeloModeloTableStructure() {
		TableStructure table = new TableStructure() {

			public String getDatabaseCreationSQL() {
				return "CREATE TABLE modelo_modelo ("
						+ "  id integer primary key autoincrement,"
						+ "  nome text," + "  observacao text);";
			}

			public String getUpgradeSQL(int oldVersion, int newVersion) {
				// TODO Auto-generated method stub
				return null;
			}

			public void getPopulationSQLs(List<String> list) {
				list.add("INSERT INTO \"modelo_modelo\" VALUES(1,'main',NULL);");
			}

		};

		return table;
	}

	private TableStructure createContaTableStructure() {
		TableStructure table = new TableStructure() {

			public String getDatabaseCreationSQL() {
				return "CREATE TABLE conta ("
						+ "  _id integer primary key autoincrement,"
						+ "  nome text,"
						+ "  observacao text,"
						+ "  contabilizavel boolean);";
			}

			public String getUpgradeSQL(int oldVersion, int newVersion) {
				return "";
			}

			public void getPopulationSQLs(List<String> list) {
				list.add("INSERT INTO \"conta\" VALUES(97,'academia',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(98,'aesa',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(99,'almoco',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(100,'cabelo',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(101,'carteira',NULL,1);");
				list.add("INSERT INTO \"conta\" VALUES(102,'casa',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(103,'claro - 7653-0794',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(104,'claro - 96587-4452',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(105,'claro tv',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(106,'conferencia',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(107,'coop',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(108,'despesas bancarias',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(109,'diversos',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(110,'emprestimo',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(111,'greenline',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(112,'inss',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(113,'internet',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(114,'itau',NULL,1);");
				list.add("INSERT INTO \"conta\" VALUES(115,'itau credito',NULL,1);");
				list.add("INSERT INTO \"conta\" VALUES(116,'lanche',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(117,'luz',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(118,'medico',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(119,'metro',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(120,'salario',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(121,'santander credito',NULL,1);");
				list.add("INSERT INTO \"conta\" VALUES(122,'semasa',NULL,0);");
				list.add("INSERT INTO \"conta\" VALUES(123,'vivo',NULL,0);");
			}

		};

		return table;
	}

	public void populateRawObjectStructures(
			List<DatabaseRawObjectStructure> objects) {
		// TODO Auto-generated method stub
		
	}

}
