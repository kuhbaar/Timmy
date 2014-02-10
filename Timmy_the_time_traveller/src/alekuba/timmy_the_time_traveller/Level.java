package alekuba.timmy_the_time_traveller;

import java.util.ArrayList;
import alekuba.timmy_the_time_traveller.TimmyController.State;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
/**
 * Abstract class Level, for game levels.
 * @author Aleks
 *
 */
public abstract class Level {
	protected ArrayList<Rect> initNonHarmingRects, initActionRects, initHarmingRects, initHiddenRects;
	protected ArrayList<Rect> nonHarmingRects;
	protected ArrayList<Rect> actionRects;
	protected ArrayList<Rect> harmingRects; //lose one life if colliding with these
	protected ArrayList<Rect> hiddenRects;
	protected int canvasWidth;
	protected int canvasHeight;
	protected Rect first;
	protected Rect last;
	protected Rect finish;
	protected Resources resources;
	protected int heightMovedCounter = 0;
	protected boolean platformMoving = false;
	
	/**
	 * Update method, for moving the level past the canvas borders.
	 * @param timmy
	 * @param controller
	 */
	public void updateRectCoordinates(Timmy timmy, TimmyController controller) {
		if((timmy.getX() > this.canvasWidth / 2 && controller.getState() == State.MOVE_RIGHT && last.right > canvasWidth)) {
			int difference = timmy.getX() - (this.canvasWidth/2);
			timmy.setX(canvasWidth/2);
			for(Rect rect: nonHarmingRects) {
				rect.left -= difference;
				rect.right -= difference;
			}
			
			for(Rect rect: harmingRects) {
				rect.left -= difference;
				rect.right -= difference;
			}
			
			for(Rect rect: actionRects) {
				rect.left -= difference;
				rect.right -= difference;
				Log.d("TAG",rect.toShortString());
			}
			
			for(Rect rect: hiddenRects) {
				rect.left -= difference;
				rect.right -= difference;
			}
			
			finish.left -= difference;
			finish.right -= difference;

		}
		if((timmy.getX() < this.canvasWidth/2 && controller.getState() == State.MOVE_LEFT && first.left < 0) || platformMoving) {
			
			int difference = (this.canvasWidth/2) - timmy.getX();
			timmy.setX(canvasWidth/2);
			for(Rect rect: nonHarmingRects) {
				rect.left += difference;
				rect.right += difference;
			}
			
			for(Rect rect: harmingRects) {
				rect.left += difference;
				rect.right += difference;
			}
			
			for(Rect rect: actionRects) {
				rect.left += difference;
				rect.right += difference;
			}
			
			for(Rect rect: hiddenRects) {
				rect.left += difference;
				rect.right += difference;
			}
			
			finish.left += difference;
			finish.right += difference;
		}
		
		if(timmy.getY() < this.canvasHeight*0.4 && controller.getState() != State.NOT_MOVING) {
			for(Rect rect: nonHarmingRects) {
				rect.top += Timmy.speed;
				rect.bottom += Timmy.speed;
			}
			
			for(Rect rect: harmingRects) {
				rect.top += Timmy.speed;
				rect.bottom += Timmy.speed;
			}
			
			for(Rect rect: actionRects) {
				rect.top += Timmy.speed;
				rect.bottom += Timmy.speed;
			}
			
			for(Rect rect: hiddenRects) {
				rect.top += Timmy.speed;
				rect.bottom += Timmy.speed;
			}
			
			finish.top += Timmy.speed;
			finish.bottom += Timmy.speed;
			timmy.setY(timmy.getY()+Timmy.speed);
			heightMovedCounter++;
		}
		
		if(timmy.getY() > this.canvasHeight*0.6 && heightMovedCounter > 0) {
			for(Rect rect: nonHarmingRects) {
				rect.top -= Timmy.speed;
				rect.bottom -= Timmy.speed;
			}
			
			for(Rect rect: harmingRects) {
				rect.top -= Timmy.speed;
				rect.bottom -= Timmy.speed;
			}
			
			for(Rect rect: actionRects) {
				rect.top -= Timmy.speed;
				rect.bottom -= Timmy.speed;
			}
			
			for(Rect rect: hiddenRects) {
				rect.top -= Timmy.speed;
				rect.bottom -= Timmy.speed;
			}
			
			finish.top -= Timmy.speed;
			finish.bottom -= Timmy.speed;
			timmy.setY(timmy.getY()-Timmy.speed);
			heightMovedCounter--;
		}
	}
	
	/**
	 * Method to be triggered when collision with an action button happens.
	 * @param r the Rect collided with
	 */
	public abstract void action(Rect r);
	
	public ArrayList<Rect> getNonHarmingRects() {
		return this.nonHarmingRects;
	}
	
	public ArrayList<Rect> getHarmingRects() {
		return this.harmingRects;
	}
	
	public ArrayList<Rect> getActionRects() {
		return this.actionRects;
	}
	
	public int getCanvasWidth(){
		return this.canvasWidth;
	}
	public int getCanvasHeight(){
		return this.canvasHeight;
	}
	/**
	 * Method for restarting the level.
	 */
	public abstract void restart();
	
	public Rect getFinish() {
		return this.finish;
	}
	/**
	 * Draw method, for drawing the updated level.
	 * @param canvas
	 */
	public abstract void drawLevel(Canvas canvas);
}
