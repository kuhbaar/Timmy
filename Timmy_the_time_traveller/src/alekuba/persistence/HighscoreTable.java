package alekuba.persistence;

import android.database.sqlite.SQLiteDatabase;

public class HighscoreTable {
	public static final String HIGHSCORE_TABLE_NAME = "highscore";
	public static final String COLUMN_ID = "_ID";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_TIME = "time";
	public static final String COLUMN_LEVEL = "level";
	
	private static final String HIGHSCORE_TABLE_CREATE = 
			"CREATE TABLE " + HIGHSCORE_TABLE_NAME + " (" +
			COLUMN_ID + " integer primary key autoincrement, " +
			COLUMN_NAME + " TEXT not null, " + 
			COLUMN_TIME + " TEXT not null, " +
			COLUMN_LEVEL + " integer not null);";
	
	public static void onCreate(SQLiteDatabase db) {
		db.execSQL(HIGHSCORE_TABLE_CREATE);
	}
	
	public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + HIGHSCORE_TABLE_NAME);
		onCreate(db);
	}
	
}
