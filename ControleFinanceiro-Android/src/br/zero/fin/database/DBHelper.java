package br.zero.fin.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {
	
	private Logger log = Logger.global();
	private DatabaseStructure structure;

	public DBHelper(Context context, DatabaseStructure structure) {
		super(context, structure.getName(), null, structure.getVersion());
		
		this.structure = structure;
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		log.log("Creating database...");
		
		for (TableStructure table : structure.getTables()) {
			createTableStructureAndPopulate(database, table);
		}
	}

	private void createTableStructureAndPopulate(SQLiteDatabase database, TableStructure table) {
		String databaseCreationSQL = table.getDatabaseCreationSQL();
		
		if (!databaseCreationSQL.isEmpty()) {
			database.execSQL(databaseCreationSQL);
		}
		
		List<String> statements = new ArrayList<String>();
		
		table.getPopulationSQLs(statements);
		
		for (String statement : statements) {
			database.execSQL(statement);
		};
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		log.log("Updating database...");
		
		for (TableStructure table : structure.getTables()) {
			String upgradeSQL = table.getUpgradeSQL(oldVersion, newVersion);
			
			if (!upgradeSQL.isEmpty()){
				database.execSQL(upgradeSQL);
			}
		}
	}

}
