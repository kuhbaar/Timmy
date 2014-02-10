package alekuba.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PersistenceOpenHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "database.db";
	private static final int DATABASE_VERSION = 1;
	
	public PersistenceOpenHelper(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		HighscoreTable.onCreate(arg0);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int oldVersion, int newVersion) {
		HighscoreTable.onUpgrade(arg0, oldVersion, newVersion);
	}
}
