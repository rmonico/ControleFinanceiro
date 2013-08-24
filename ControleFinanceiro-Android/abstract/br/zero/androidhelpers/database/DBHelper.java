package br.zero.androidhelpers.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.zero.fin.database.Logger;

public abstract class DBHelper extends SQLiteOpenHelper {

	private Logger log = Logger.global();
	private DatabaseStructure structure;

	public DBHelper(Context context, DatabaseStructure structure) {
		super(context, structure.getName(), null, structure.getVersion());

		this.structure = structure;
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		log.log("Creating database...");

		List<DatabaseRawObjectStructure> objects = new ArrayList<DatabaseRawObjectStructure>();

		structure.populateRawObjectStructures(objects);

		for (DatabaseRawObjectStructure object : objects) {
			execObjectCreationSQLs(database, object);
		}
	}

	private void execObjectCreationSQLs(SQLiteDatabase database,
			DatabaseRawObjectStructure object) {
		List<String> sqls = new ArrayList<String>();

		object.populateObjectCreationSQLs(sqls);

		for (String sql : sqls) {
			database.execSQL(sql);
		}
		;
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		log.log("Updating database...");

		List<DatabaseRawObjectStructure> objects = new ArrayList<DatabaseRawObjectStructure>();

		structure.populateRawObjectStructures(objects);

		for (DatabaseRawObjectStructure object : objects) {
			execObjectUpgradeSQLs(database, object, oldVersion, newVersion);
		}
	}

	private void execObjectUpgradeSQLs(SQLiteDatabase database,
			DatabaseRawObjectStructure object, int oldVersion, int newVersion) {
		List<String> sqls = new ArrayList<String>();

		object.populateUpgradeSQL(sqls, oldVersion, newVersion);

		for (String sql : sqls) {
			database.execSQL(sql);
		}
		;
	}

}
