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
 * Level 2
 * @author Aleks
 *
 */
public class Level2 extends Level {
	private Paint actionPaint;
	private ArrayList<Boolean> actionButtonPressed;
	private int moveCounter = 0;
	
	public Level2(Canvas canvas, Resources resources) {
		actionButtonPressed = new ArrayList<Boolean>();
		this.canvasHeight = canvas.getHeight();
		this.canvasWidth = canvas.getWidth();
		this.resources = resources;
		
		actionPaint = new Paint();
		actionPaint.setColor(Color.rgb(192, 192, 192));
		
		init();
		for(int i=0;i<actionRects.size();i++) {
			actionButtonPressed.add(false);
		}
	}
	
	private void init() {
		nonHarmingRects = new ArrayList<Rect>();
		harmingRects = new ArrayList<Rect>();
		actionRects = new ArrayList<Rect>();
		hiddenRects = new ArrayList<Rect>();
		Bitmap actionButtonUnpressed = BitmapFactory.decodeResource(resources, R.drawable.actionbuttonunpressed_faceright);
		Bitmap finishFlag = BitmapFactory.decodeResource(resources, R.drawable.finish);
		first = new Rect(0,canvasHeight-150,200,canvasHeight-110);
		nonHarmingRects.add(first);
		nonHarmingRects.add(new Rect(300,canvasHeight-200,400,canvasHeight-160));
		nonHarmingRects.add(new Rect(500,canvasHeight-260,600,canvasHeight-200));
		nonHarmingRects.add(new Rect(725,canvasHeight-310,800,canvasHeight-250));
		nonHarmingRects.add(new Rect(900,canvasHeight-360,950,canvasHeight-320));
		nonHarmingRects.add(new Rect(1050,canvasHeight-410,1225,canvasHeight-350));
		nonHarmingRects.add(new Rect(750,canvasHeight-480,925,canvasHeight-470));
		nonHarmingRects.add(new Rect(450,canvasHeight-540,650,canvasHeight-500));

		nonHarmingRects.add(new Rect(0,canvasHeight-590,340,canvasHeight-570));
		nonHarmingRects.add(new Rect(0,canvasHeight-800,100,canvasHeight-590));
		nonHarmingRects.add(new Rect(1800,canvasHeight-600,2000,canvasHeight-580));
		nonHarmingRects.add(new Rect(1400,canvasHeight-660,1650,canvasHeight-650));
		nonHarmingRects.add(new Rect(1400,canvasHeight-900,1450,canvasHeight-660));
		nonHarmingRects.add(new Rect(2125,canvasHeight-660,2400,canvasHeight-640));
		
		
		hiddenRects.add(new Rect(1275,canvasHeight-480,1525,canvasHeight-460));
		hiddenRects.add(new Rect(1600,canvasHeight-550,1700,canvasHeight-520));
		hiddenRects.add(new Rect(1000,canvasHeight-570,1200,canvasHeight-560));
		hiddenRects.add(new Rect(1050,canvasHeight-570-actionButtonUnpressed.getHeight(),1050+actionButtonUnpressed.getWidth(),canvasHeight-570));
		
		last = new Rect(10000,canvasHeight-5000,20000,canvasHeight-7000);
		
		
		
		actionRects.add(new Rect(100,canvasHeight-750,100+actionButtonUnpressed.getWidth(),canvasHeight-750+actionButtonUnpressed.getHeight()));
		actionRects.add(new Rect(1450,canvasHeight-800,1450+actionButtonUnpressed.getWidth(),canvasHeight-800+actionButtonUnpressed.getHeight()));
		//finish ist hidden:
		finish = new Rect();
		hiddenRects.add(new Rect(200,canvasHeight-590-finishFlag.getHeight(),200+finishFlag.getWidth(),canvasHeight-590));
	}
	
	@Override
	public void restart() {
		init();
		redoAllActions();
	}

	@Override
	public void drawLevel(Canvas canvas) {
		for(Rect rect: nonHarmingRects) {
			canvas.drawRect(rect, actionPaint);
		}

		for(int i=0;i<harmingRects.size();i++) {
			canvas.drawBitmap(BitmapFactory.decodeResource(resources, R.drawable.spikeup), null, harmingRects.get(i),null);
		}
		
		for(int i=0;i<actionRects.size();i++) {
			if(actionButtonPressed.get(i) == false) {
				if(i!=2) {
					canvas.drawBitmap(BitmapFactory.decodeResource(resources, R.drawable.actionbuttonunpressed_faceright), null, actionRects.get(i), null);
				} else {
					canvas.drawBitmap(BitmapFactory.decodeResource(resources, R.drawable.actionbuttonunpressed_faceup), null, actionRects.get(i), null);
				}
			} else {
				if(i!=2) {
					canvas.drawBitmap(BitmapFactory.decodeResource(resources, R.drawable.actionbuttonpressed_faceright), null, actionRects.get(i), null);				
				} else {
					canvas.drawBitmap(BitmapFactory.decodeResource(resources, R.drawable.actionbuttonpressed_faceup), null, actionRects.get(i), null);
				}
			}
		}
		if(actionButtonPressed.get(1) == true) {
			canvas.drawBitmap(BitmapFactory.decodeResource(resources, R.drawable.finish), null, finish, null);
		}

	}

	@Override
	public void action(Rect r) {
		for(int i=0;i<actionRects.size();i++) {
			if(actionRects.get(i).equals(r)) {
				
				if(i==0 && actionButtonPressed.get(i) == false) {
					nonHarmingRects.remove(6);
					nonHarmingRects.remove(6);
					nonHarmingRects.add(hiddenRects.get(0));
					nonHarmingRects.add(hiddenRects.get(1));
					hiddenRects.remove(0);
					hiddenRects.remove(0);
					actionButtonPressed.set(i, true);
				}
				if(i==1 && actionButtonPressed.get(i) == false) {
					nonHarmingRects.remove(11);
					nonHarmingRects.remove(12);
					nonHarmingRects.remove(8);
					nonHarmingRects.add(hiddenRects.get(0));
					actionRects.add(hiddenRects.get(1));
					actionButtonPressed.add(false);
					hiddenRects.remove(0);
					hiddenRects.remove(0);
					
					finish = hiddenRects.get(0);
					hiddenRects.remove(0);
					actionButtonPressed.set(i, true);
				}
				if(i==2 && actionButtonPressed.get(i) == false) {
					actionButtonPressed.set(i, true);
				}
			}
		}
	}
	
	private void redoAllActions() {
		for(int i=0;i<actionButtonPressed.size();i++) {
			if(actionButtonPressed.get(i) == true) {
				if(i==0) {
					nonHarmingRects.remove(6);
					nonHarmingRects.remove(6);
					nonHarmingRects.add(hiddenRects.get(0));
					nonHarmingRects.add(hiddenRects.get(1));
					hiddenRects.remove(0);
					hiddenRects.remove(0);
				}
				if(i==1) {
					nonHarmingRects.remove(11);
					nonHarmingRects.remove(12);
					nonHarmingRects.remove(8);
					nonHarmingRects.add(hiddenRects.get(0));
					actionRects.add(hiddenRects.get(1));
					hiddenRects.remove(0);
					hiddenRects.remove(0);
					finish = hiddenRects.get(0);
					hiddenRects.remove(0);
				}
				
			}
		}
	}
	
	public boolean movePlatform() {
		if(actionButtonPressed.size() > 2 && actionButtonPressed.get(2) == true) {
			Rect toMove = nonHarmingRects.get(nonHarmingRects.size()-1);
			if(moveCounter < 110) {
				platformMoving = true;
				toMove.left -= Timmy.speed;
				toMove.right -= Timmy.speed;
				actionRects.get(2).left -= Timmy.speed;
				actionRects.get(2).right -= Timmy.speed;
				moveCounter ++;
				return true;
			}
		}
		platformMoving = false;
		return false;
	}

}
