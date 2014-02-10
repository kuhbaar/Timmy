package alekuba.timmy_the_time_traveller;

import android.os.Bundle;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.view.Menu;
import android.view.View;
import android.view.Window;
/**
 * Class for help option. Uses the 2 layouts activity_help2 and activity_help1.
 * @author Kuba
 *
 */
public class HelpActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		setContentView(R.layout.activity_help1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.help, menu);
		return true; 
	}
	
	public void forward(View view){
		setContentView(R.layout.activity_help2);
	}
	
	public void backward(View view){
		setContentView(R.layout.activity_help1);
	}

}
