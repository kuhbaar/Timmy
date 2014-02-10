package alekuba.timmy_the_time_traveller;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
/**
 * Level 1
 * @author aleks
 *
 */

public class Level1 extends Level {
	private Paint earthPaint;
	private boolean actionButtonPressed;
	
	public Level1(Canvas canvas, Resources resources) {
		actionButtonPressed = false;
		earthPaint = new Paint();
		earthPaint.setColor(Color.rgb(115, 69, 35));
		canvasWidth = canvas.getWidth();
		canvasHeight = canvas.getHeight();
		this.resources = resources;
		init();
	}
	
	private void init() {
		nonHarmingRects = new ArrayList<Rect>();
		harmingRects = new ArrayList<Rect>();
		actionRects = new ArrayList<Rect>();
		hiddenRects = new ArrayList<Rect>();
		first = new Rect(0,canvasHeight-200,300,canvasHeight);
		nonHarmingRects.add(first);
		nonHarmingRects.add(new Rect(400,canvasHeight-150,750,canvasHeight));
		nonHarmingRects.add(new Rect(850,canvasHeight-150,1400,canvasHeight));
		nonHarmingRects.add(new Rect(2800,canvasHeight-545,3500,canvasHeight-400));
		last = new Rect(1500,canvasHeight-200,3500,canvasHeight);
		nonHarmingRects.add(new Rect(2750,canvasHeight-550,3500,canvasHeight-500));
		nonHarmingRects.add(new Rect(3300,canvasHeight-550,3500,canvasHeight-200));
		//nonHarmingRects.add(new Rect(2750,canvasHeight-550,2800,canvasHeight-500));
		nonHarmingRects.add(new Rect(2700,canvasHeight-500,2750,canvasHeight-450));
		nonHarmingRects.add(new Rect(2650,canvasHeight-450,2700,canvasHeight-400));
		nonHarmingRects.add(new Rect(2600,canvasHeight-400,2650,canvasHeight-350));
		nonHarmingRects.add(last);
		
		Bitmap spikeup = BitmapFactory.decodeResource(resources, R.drawable.spikeup);
		int spikeupHeight = spikeup.getHeight();
		int spikeupWidth = spikeup.getWidth();
		
		this.harmingRects.add(new Rect(190,canvasHeight-200-spikeupHeight,190+spikeupWidth,canvasHeight-200));
		this.harmingRects.add(new Rect(1000,canvasHeight-150-spikeupHeight,1000+spikeupWidth,canvasHeight-150));
		this.harmingRects.add(new Rect(1200,canvasHeight-150-spikeupHeight,1200+spikeupWidth,canvasHeight-150));
		this.harmingRects.add(new Rect(1700,canvasHeight-200-spikeupHeight,1700+spikeupWidth,canvasHeight-200));
		
		Bitmap actionbuttonunpressed = BitmapFactory.decodeResource(resources,R.drawable.actionbuttonunpressed_faceleft);
		
		this.actionRects.add(new Rect(3300-actionbuttonunpressed.getWidth(),canvasHeight-300-actionbuttonunpressed.getHeight(),3300,canvasHeight-300));
		
		hiddenRects.add(new Rect(2550,canvasHeight-350,2600,canvasHeight-300));
		hiddenRects.add(new Rect(2500,canvasHeight-300,2550,canvasHeight-250));
		hiddenRects.add(new Rect(2450,canvasHeight-250,2500,canvasHeight-200));
		
		Bitmap finishFlag = BitmapFactory.decodeResource(resources, R.drawable.finish);
		finish = new Rect(3200,canvasHeight-550-finishFlag.getHeight(),3200+finishFlag.getWidth(),canvasHeight-550);
	}

	public void drawLevel(Canvas canvas) {
		for(Rect rect: nonHarmingRects) {
				canvas.drawRect(rect, earthPaint);
		}
	
		for(int i=0;i<harmingRects.size();i++) {
				if(i >= 0 && i < 4) {
					canvas.drawBitmap(BitmapFactory.decodeResource(resources, R.drawable.spikeup), null, harmingRects.get(i),null);
				}
		}
		
		for(int i=0;i<this.actionRects.size();i++) {
				if(actionButtonPressed == false) {
					canvas.drawBitmap(BitmapFactory.decodeResource(resources, R.drawable.actionbuttonunpressed_faceleft), null, actionRects.get(0),null);
				} else {
					canvas.drawBitmap(BitmapFactory.decodeResource(resources, R.drawable.actionbuttonpressed_faceleft), null, actionRects.get(0),null);
				}
		}
		canvas.drawBitmap(BitmapFactory.decodeResource(resources, R.drawable.finish), null, finish, null);
	}
	
	/**
	 * Calls handling upon pressing the actionButton of Level1
	 * @author aleks, kuba
	 */
	@Override
	public void action(Rect r) {
		if(actionButtonPressed == false) {
			nonHarmingRects.addAll(hiddenRects);
			hiddenRects = new ArrayList<Rect>();
			actionButtonPressed=true;
		}
	}
	
	@Override
	public void restart(){
		init();
		if(actionButtonPressed) {
			actionButtonPressed = false;
			action(new Rect());
		}
	}
}
