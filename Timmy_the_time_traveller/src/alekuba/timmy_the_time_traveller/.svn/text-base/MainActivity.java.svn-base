package alekuba.timmy_the_time_traveller;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
/**
 * Class for the main menu.
 * @author Kuba
 *
 */
public class MainActivity extends Activity implements OnCompletionListener {
	
	private static final String TAG = MainActivity.class.getSimpleName();
	private MediaPlayer player;
	private boolean soundEnabled;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		soundEnabled = this.getIntent().getBooleanExtra("alekuba.timmy_the_time_traveller.playSound", true);
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		AudioManager audioManager = (AudioManager)getSystemService(MainActivity.AUDIO_SERVICE);
		int volID = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC/2);
		
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volID, 0);
		
		player = MediaPlayer.create(this, R.raw.mainmusic);
		player.setOnCompletionListener(this);
		ImageButton sound = (ImageButton)findViewById(R.id.soundButton);
		if(!soundEnabled)
			sound.setImageResource(R.drawable.no_sound);
		else{
			sound.setImageResource(R.drawable.sound);
			player.start();}
			
		Log.d(TAG, "onCreate");
	}
	
	@Override
	protected void onResume(){
		super.onResume();
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		try {
			if(!player.isPlaying() && soundEnabled) {
				player.start();
			}
		} catch(IllegalStateException ex) {
			AudioManager audioManager = (AudioManager)getSystemService(MainActivity.AUDIO_SERVICE);
			int volID = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC/2);
			
			audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volID, 0);
			
			player = MediaPlayer.create(this, R.raw.mainmusic);
			player.setOnCompletionListener(this);
			if(soundEnabled) {
				player.start();
			}
		}
		Log.d(TAG, "onRestart");
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Intent intent = new Intent(this, VideoActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.putExtra("EXIT", true);
		startActivity(intent);
		System.exit(0);
		this.finish();

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	/**
	 * Method for button trigger to start the game.
	 * @param view
	 */
	public void startGame(View view){ //trigger for Startbutton
		Log.d(TAG,"Start button pressed, starting game Activity");
		Intent intent = new Intent(this, GameActivity.class);
		intent.putExtra("alekuba.timmy_the_time_traveller.playSound", soundEnabled);
		intent.putExtra("alekuba.timmy_the_time_traveller.level", 1);
		player.stop();
		player.release();
		startActivity(intent);	
	}
	/**
	 * Method for button trigger to show highscores.
	 * @author Aleks
	 * @param view
	 */
	public void showHighscore(View view){ //trigger for Highscorebutton
		Intent intent = new Intent(this,HighscoreActivityMain.class);
		startActivity(intent);
	}
	/**
	 * Method for button trigger to show help.
	 * @param view
	 */
	public void showHelp(View view){ //trigger for Helpbutton
		Intent intent = new Intent(this,HelpActivity.class);
		startActivity(intent);
	}
	/**
	 * Method for button trigger to end the game.
	 * @param view
	 */
	public void endGame(View view){ //trigger for Endbutton
		player.stop();
		player.release();
		this.onDestroy();
	}
	/**
	 * Method for button trigger to turn sound on/off.
	 * @author Aleks
	 * @param view
	 */
	public void switchSound(View view) {
		ImageButton sound = (ImageButton)findViewById(R.id.soundButton);
		if(soundEnabled) {
			soundEnabled=false;
			sound.setImageResource(R.drawable.no_sound);
			player.pause();
		} else {
			soundEnabled = true;
			sound.setImageResource(R.drawable.sound);
			player.start();
		}
	}
	
	@Override
	public void onCompletion(MediaPlayer arg0) {
		arg0.start();
	}
	

}
