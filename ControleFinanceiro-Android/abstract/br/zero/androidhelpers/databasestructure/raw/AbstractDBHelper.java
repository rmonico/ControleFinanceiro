package br.zero.androidhelpers.databasestructure.raw;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.zero.androidhelpers.logger.Logger;

public abstract class AbstractDBHelper extends SQLiteOpenHelper {

	private Logger log = Logger.global();
	private DatabaseStructure structure;

	public AbstractDBHelper(Context context, DatabaseStructure structure) {
		super(context, structure.getName(), null, structure.getVersion());

		this.structure = structure;
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		log.log("Creating database...");

		List<RawDatabaseObjectStructure> objects = structure.populateRawObjectStructures();

		for (RawDatabaseObjectStructure object : objects) {
			List<String> sqls = object.getObjectCreationSQLs();

			for (String sql : sqls) {
				database.execSQL(sql);
			}
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		log.log("Updating database...");

		List<RawDatabaseObjectStructure> objects = new ArrayList<RawDatabaseObjectStructure>();

		structure.populateRawObjectStructures(objects);

		for (RawDatabaseObjectStructure object : objects) {
			execObjectUpgradeSQLs(database, object, oldVersion, newVersion);
		}
	}

	private void execObjectUpgradeSQLs(SQLiteDatabase database,
			RawDatabaseObjectStructure object, int oldVersion, int newVersion) {
		StringBuilder s = object.getUpgradeSQL(oldVersion, newVersion);

		database.execSQL(s.toString());
	}

}
