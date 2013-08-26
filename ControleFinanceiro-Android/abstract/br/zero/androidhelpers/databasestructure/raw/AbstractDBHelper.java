package br.zero.androidhelpers.databasestructure.raw;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.zero.androidhelpers.databasestructure.table.DatabaseStructureException;
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
			List<String> sqls;
			try {
				sqls = object.getObjectCreationSQLs();
			} catch (DatabaseStructureException e) {
				throw new RuntimeException(e);
			}

			execSQLList(database, sqls);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion,
			int newVersion) {
		log.log("Updating database...");

		List<RawDatabaseObjectStructure> objects = structure.populateRawObjectStructures();

		for (RawDatabaseObjectStructure object : objects) {
			List<String> sqls;
			try {
				sqls = object.getUpgradeSQL(oldVersion, newVersion);
			} catch (DatabaseStructureException e) {
				throw new RuntimeException(e);
			}

			execSQLList(database, sqls);
		}
	}

	private void execSQLList(SQLiteDatabase database, List<String> sqls) {
		for (String sql : sqls) {
			database.execSQL(sql);
		}
	}

}
