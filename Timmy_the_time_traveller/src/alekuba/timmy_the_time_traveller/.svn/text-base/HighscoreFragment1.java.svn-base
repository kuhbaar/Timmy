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
 * Helperclass for the creation of level1 highscore.
 * @author Aleks
 *
 */
public class HighscoreFragment1 extends Fragment {
	private PersistenceOpenHelper helper;
	private SQLiteDatabase db;
	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LayoutInflater infl = getActivity().getLayoutInflater();
		view = infl.inflate(R.layout.activity_highscore, container, false);
		showLvl1Highscore();
		return view;
	}

	private void showLvl1Highscore() {
		helper = new PersistenceOpenHelper(this.getActivity().getApplicationContext());
		if(db == null) {
			db = helper.getWritableDatabase();
		}
		
		TextView rankLvl1 = (TextView)view.findViewById(R.id.rankTextView1);
		TextView scoreLvl1 = (TextView)view.findViewById(R.id.scoreTextView1);
		TextView nameLvl1 = (TextView)view.findViewById(R.id.nameTextView1);
		
		String[] columns = new String[] {
				HighscoreTable.COLUMN_NAME,HighscoreTable.COLUMN_TIME
		};
		String selection = HighscoreTable.COLUMN_LEVEL + "= 1";
		String orderBy = HighscoreTable.COLUMN_TIME + " ASC";
		Cursor lvl1 = db.query(HighscoreTable.HIGHSCORE_TABLE_NAME, columns, selection, null, null, null, orderBy);
		
		int i = 0;
		Log.d("CURSOR", lvl1.getColumnNames()[0]);
		if(lvl1.moveToFirst()) {
			do {
				String name = lvl1.getString(lvl1.getColumnIndex(HighscoreTable.COLUMN_NAME));
				int score = lvl1.getInt(lvl1.getColumnIndex(HighscoreTable.COLUMN_TIME));
				if(i==0) {
					nameLvl1.setText(name);
					scoreLvl1.setText(String.valueOf(score));
					i++;
					rankLvl1.setText(String.valueOf(i));
				} else {
					nameLvl1.append("\n" + name);
					scoreLvl1.append("\n" + String.valueOf(score));
					i++;
					rankLvl1.append("\n" + String.valueOf(i));
				}
			} while(lvl1.moveToNext() && i<=9);
		}
	}
	
}
