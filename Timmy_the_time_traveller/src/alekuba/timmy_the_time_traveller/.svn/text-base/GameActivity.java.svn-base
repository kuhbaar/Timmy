package alekuba.timmy_the_time_traveller;
/**
 * Starts the game with given Level (= starts MySurfaceView).
 * @author Kuba
 * @see MySurfaceView
 */
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
/**
 * Class for the Start Game option
 * @author Zacky
 *
 */
public class GameActivity extends Activity {
	private int level;
	private static final String TAG = GameActivity.class.getSimpleName();
	private boolean soundEnabled;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		soundEnabled = this.getIntent().getBooleanExtra("alekuba.timmy_the_time_traveller.playSound", true);
		level = this.getIntent().getIntExtra("alekuba.timmy_the_time_traveller.level", 0);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		if(level==3){
			Intent intent = new Intent(this, MainActivity.class);
			intent.putExtra("alekuba.timmy_the_time_traveller.playSound", soundEnabled);
			this.startActivity(intent);
			this.finish();
		}
		setContentView(new MySurfaceView(this,soundEnabled, level));
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Log.d(TAG, "View added");
	}
	
	protected void onDestroy(){
		Log.d(TAG, "destroying");
		super.onDestroy();
	}
	protected void onStop(){
		Log.d(TAG, "stoping");
		super.onStop();
	}
	protected void onResume(){
		Log.d(TAG, "resuming");
		super.onResume();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.game, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
