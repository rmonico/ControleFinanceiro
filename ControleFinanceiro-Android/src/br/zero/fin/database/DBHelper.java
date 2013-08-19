package br.zero.fin.database;

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
		database.execSQL(structure.getDatabaseCreationSQL());
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		String upgradeSQL = structure.getUpgradeSQL(oldVersion, newVersion);
		
		if (!upgradeSQL.isEmpty()) {
			log.log("Database: Upgrading database from " + oldVersion + " to " + newVersion);
			database.execSQL(upgradeSQL);
		} else {
			log.log("Database: nothing to upgrade.");
		}
		
	}

}
