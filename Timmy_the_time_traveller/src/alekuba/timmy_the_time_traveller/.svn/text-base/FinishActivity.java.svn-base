package alekuba.timmy_the_time_traveller;

import alekuba.persistence.HighscoreTable;
import alekuba.persistence.PersistenceOpenHelper;
import android.os.Bundle;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
/**
 * Class for the level-finished splashscreen.
 * @author Kuba
 *
 */
public class FinishActivity extends Activity {
	private int level;
	private long score;
	private boolean soundEnabled;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		soundEnabled = this.getIntent().getBooleanExtra("alekuba.timmy_the_time_traveller.playSound", true);
		level = this.getIntent().getIntExtra("alekuba.timmy_the_time_traveller.level", 0);
		score = this.getIntent().getLongExtra("alekuba.timmy_the_time_traveller.score", 0);
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_finish);
		
		startViewAnimation();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dead, menu);
		return true;
	}
	
	private void startViewAnimation() {
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.finish_anim);
		ImageView imageView = (ImageView) findViewById(R.id.finishImageView);
        animation.reset();
        
        imageView.clearAnimation();
        imageView.startAnimation(animation);
	}
	/**
	 * Method for button trigger. Saves user's name to the highscore database, and inits GameActivity with next level.
	 * @param view
	 * @see GameActivity
	 */
	public void nextLevel(View view){
		//save highscore to daatabase
		EditText mEdit   = (EditText)findViewById(R.id.editText1);
		PersistenceOpenHelper pers = new PersistenceOpenHelper(this);
		SQLiteDatabase sql = pers.getWritableDatabase();
		ContentValues vals = new ContentValues();
		vals.put(HighscoreTable.COLUMN_NAME, mEdit.getText().toString());
		vals.put(HighscoreTable.COLUMN_TIME, (int)score);
		vals.put(HighscoreTable.COLUMN_LEVEL, level);
		sql.insert(HighscoreTable.HIGHSCORE_TABLE_NAME, null, vals);
		//init Gameactivity with level 2
		Intent intent = new Intent(this, GameActivity.class);
		intent.putExtra("alekuba.timmy_the_time_traveller.level", level+1);
		intent.putExtra("alekuba.timmy_the_time_traveller.playSound", soundEnabled);
		startActivity(intent);
		this.finish();
	}
}