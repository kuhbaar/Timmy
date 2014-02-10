package alekuba.timmy_the_time_traveller;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
/**
 * Class for the death splashscreen.
 * @author Kuba
 *
 */
public class DeadActivity extends Activity {
	private int level;
	private boolean soundEnabled;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		soundEnabled = this.getIntent().getBooleanExtra("alekuba.timmy_the_time_traveller.playSound", true);
		level = this.getIntent().getIntExtra("alekuba.timmy_the_time_traveller.level", 0);
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_dead);
		
		startViewAnimation();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dead, menu);
		return true;
	}
	
	private void startViewAnimation() {
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.rip_anim);
		ImageView imageView = (ImageView) findViewById(R.id.ripImageView);
        animation.reset();
        
        imageView.clearAnimation();
        imageView.startAnimation(animation);
	}
	/**
	 * Method for button trigger to exit to main menu (MainActivity).
	 * @param view
	 * @see MainActivity
	 */
	public void exitToMenu(View view){
		Intent intent = new Intent(this, MainActivity.class);
		intent.putExtra("alekuba.timmy_the_time_traveller.playSound", soundEnabled);
		startActivity(intent);
		this.finish();
	}
	/**
	 * Method for button trigger to restart the current level.
	 * @param view
	 */
	public void restart(View view){
		Intent intent = new Intent(this, GameActivity.class);
		intent.putExtra("alekuba.timmy_the_time_traveller.level", level);
		intent.putExtra("alekuba.timmy_the_time_traveller.playSound", soundEnabled);
		startActivity(intent);
		this.finish();
	}

}
