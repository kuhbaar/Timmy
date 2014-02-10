package alekuba.timmy_the_time_traveller;

import alekuba.persistence.HighscoreTable;
import alekuba.persistence.PersistenceOpenHelper;
import android.app.Fragment;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Helperclass for the creation of level2 highscore.
 * @author Aleks
 *
 */
public class HighscoreFragment2 extends Fragment {
	private View view;
	private PersistenceOpenHelper helper;
	private SQLiteDatabase db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LayoutInflater infl = getActivity().getLayoutInflater();
		view = infl.inflate(R.layout.activity_highscore_activity_lvl2, container, false);
		showLvl2Highscore();
		return view;
	}
	
	private void showLvl2Highscore() {
		if(helper == null) {
			helper = new PersistenceOpenHelper(this.getActivity().getApplicationContext());
		}
		if(db == null) {
			db = helper.getWritableDatabase();
		}
		
		TextView rankLvl2 = (TextView)view.findViewById(R.id.rankTextView2);
		TextView scoreLvl2 = (TextView)view.findViewById(R.id.scoreTextView2);
		TextView nameLvl2 = (TextView)view.findViewById(R.id.nameTextView2);
		
		String[] columns = new String[] {
				HighscoreTable.COLUMN_NAME,HighscoreTable.COLUMN_TIME
		};
		String selection = HighscoreTable.COLUMN_LEVEL + "= 2";
		String orderBy = HighscoreTable.COLUMN_TIME + " ASC";
		Cursor lvl2 = db.query(HighscoreTable.HIGHSCORE_TABLE_NAME, columns, selection, null, null, null, orderBy);
		
		int i = 0;
		Log.d("CURSOR", lvl2.getColumnNames()[0]);
		if(lvl2.moveToFirst()) {
			do {
				String name = lvl2.getString(lvl2.getColumnIndex(HighscoreTable.COLUMN_NAME));
				int score = lvl2.getInt(lvl2.getColumnIndex(HighscoreTable.COLUMN_TIME));
				if(i==0) {
					nameLvl2.setText(name);
					scoreLvl2.setText(String.valueOf(score));
					i++;
					rankLvl2.setText(String.valueOf(i));
				} else {
					nameLvl2.append("\n" + name);
					scoreLvl2.append("\n" + String.valueOf(score));
					i++;
					rankLvl2.append("\n" + String.valueOf(i));
				}
			} while(lvl2.moveToNext() && i<=9);
		}
	}
	
}
