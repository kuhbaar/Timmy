package alekuba.timmy_the_time_traveller;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
/**
 * Class for highscore option.
 * @author Aleks
 *
 */
public class HighscoreActivityMain extends Activity {
	private ActionBar.Tab tab1, tab2;
	private Fragment fragmentTab1 = new HighscoreFragment1();
	private Fragment fragmentTab2 = new HighscoreFragment2();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_highscore_activity_main);
		// Show the Up button in the action bar.
		ActionBar actionBar = getActionBar();
		
		// Hide Actionbar Icon
        actionBar.setDisplayShowHomeEnabled(false);
 
        // Hide Actionbar Title
        actionBar.setDisplayShowTitleEnabled(false);
        
     // Create Actionbar Tabs
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
 
        // Set Tab Icon and Titles
        tab1 = actionBar.newTab().setText("Highscore Level 1");
        tab2 = actionBar.newTab().setText("Highscore Level 2");
 
        // Set Tab Listeners
        tab1.setTabListener(new TabListener(fragmentTab1));
        tab2.setTabListener(new TabListener(fragmentTab2));
 
        // Add tabs to actionbar
        actionBar.addTab(tab1);
        actionBar.addTab(tab2);
	}

}
