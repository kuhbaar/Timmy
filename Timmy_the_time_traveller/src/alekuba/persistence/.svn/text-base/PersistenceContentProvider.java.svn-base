package alekuba.persistence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

public class PersistenceContentProvider extends ContentProvider {
	private PersistenceOpenHelper pOpenHelper;
	
	private static final int HIGHSCORE = 1;	// fetch content from this table
	private static final int HIGHSCORE_ID = 2;  //fetch a single row from this table
	
	private static final String AUTHORITY = "alekuba.persistence.persistenceprovider";
	
	
	private static final String HIGHSCORE_PATH = "highscore";
	
	
	private static final UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	static {
		sUriMatcher.addURI(AUTHORITY, HIGHSCORE_PATH, HIGHSCORE);
		sUriMatcher.addURI(AUTHORITY, HIGHSCORE_PATH + "/#", HIGHSCORE_ID);
	}
	
	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		int uriType = sUriMatcher.match(uri);
		
		SQLiteDatabase db = pOpenHelper.getWritableDatabase();
		
		int rowsDeleted = 0;
		String id;
		switch(uriType) {
			case HIGHSCORE:
				rowsDeleted = db.delete(HighscoreTable.HIGHSCORE_TABLE_NAME, selection, selectionArgs);
				break;
			case HIGHSCORE_ID:
				id = uri.getLastPathSegment();
				if(TextUtils.isEmpty(selection)) {
					rowsDeleted = db.delete(HighscoreTable.HIGHSCORE_TABLE_NAME, HighscoreTable.COLUMN_ID + "=" + id, null);
				} else {
					rowsDeleted = db.delete(HighscoreTable.HIGHSCORE_TABLE_NAME, HighscoreTable.COLUMN_ID + "=" + id + "AND" + selection, selectionArgs);
				}
				break;
			default:
				throw new IllegalArgumentException("Unknown URI:" + uri);
		}
		
		getContext().getContentResolver().notifyChange(uri, null);
		return rowsDeleted;
	}

	@Override
	public String getType(Uri uri) {
		// Not needed
		throw new UnsupportedOperationException("Not supported!");
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		int uriType = sUriMatcher.match(uri);
	    
		SQLiteDatabase db = pOpenHelper.getWritableDatabase();
	    
		long id = 0;
		String path = "";
	    switch (uriType) {
	    case HIGHSCORE:
	    	id = db.insert(HighscoreTable.HIGHSCORE_TABLE_NAME, null, values);
	    	path = HIGHSCORE_PATH;
	    	break;
	    default:
	    	throw new IllegalArgumentException("Unknown URI: " + uri);
	    }
	    
	    // Notify all listeners about the changed entry!
	    getContext().getContentResolver().notifyChange(uri, null);
	    
	    return Uri.parse(path + "/" + id);
	}

	@Override
	public boolean onCreate() {
		pOpenHelper = new PersistenceOpenHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
			String sortOrder) {
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
		
		int uriType = sUriMatcher.match(uri);
		switch(uriType) {
			case HIGHSCORE_ID:
				queryBuilder.appendWhere(HighscoreTable.COLUMN_ID + "=" + uri.getLastPathSegment());
			case HIGHSCORE:
				queryBuilder.setTables(HighscoreTable.HIGHSCORE_TABLE_NAME);
				break;
			default:
				throw new IllegalArgumentException("URI not known:" + uri);
		}
		SQLiteDatabase db = pOpenHelper.getWritableDatabase();
		
		Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
	    // make sure that potential listeners are getting notified
	    cursor.setNotificationUri(getContext().getContentResolver(), uri);
	    
		return cursor;
		
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
		int uriType = sUriMatcher.match(uri);
		SQLiteDatabase db = pOpenHelper.getWritableDatabase();
		
		int rowsUpdated = 0;
		String id;
		switch(uriType) {
			case HIGHSCORE:
				rowsUpdated = db.update(HighscoreTable.HIGHSCORE_TABLE_NAME,values, selection, selectionArgs);
				break;
			case HIGHSCORE_ID:
				id = uri.getLastPathSegment();
				if(TextUtils.isEmpty(selection)) {
					rowsUpdated = db.update(HighscoreTable.HIGHSCORE_TABLE_NAME, values, HighscoreTable.COLUMN_ID + "=" + id, null);
				} else {
					rowsUpdated = db.update(HighscoreTable.HIGHSCORE_TABLE_NAME, values, HighscoreTable.COLUMN_ID + "=" + id + "AND" + selection, selectionArgs);
				}
				break;
			default:
				throw new IllegalArgumentException("Unknown URI:" + uri);
		}
		
		getContext().getContentResolver().notifyChange(uri, null);
		
		return rowsUpdated;
	}

}
