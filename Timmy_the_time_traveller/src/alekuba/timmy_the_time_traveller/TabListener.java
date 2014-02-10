package alekuba.timmy_the_time_traveller;

import android.app.ActionBar.Tab;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ActionBar;
/**
 * Tab's controller for highscoring.
 * @author Aleks
 *
 */
public class TabListener implements ActionBar.TabListener {
	private Fragment fr;
	
	public TabListener(Fragment fr) {
		this.fr = fr;
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		ft.replace(R.id.fragment_container, fr);
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		ft.remove(fr);
		
	}
	
}
