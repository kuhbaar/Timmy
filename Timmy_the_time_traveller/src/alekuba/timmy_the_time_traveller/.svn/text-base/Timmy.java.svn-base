package alekuba.timmy_the_time_traveller;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
/**
 * Class containing all data about the game's hero. For controlls see TimmyController.java
 * @author Kuba, Aleks
 * @see TimmyController.java
 */
public class Timmy {
	public static final int timmy_left = 1;
	public static final int timmy_right = 2;
	private Bitmap bmp;
	private int x;
	private int y;
	private int bmp_rows = 4;
	private int bmp_columns = 4;
	private int fps;
	private boolean isStopped = false;
	private int currentFrame = 0;
	private int currentRow = 0;
	private int spriteWidth;
	private int spriteHeight;
	private long frameTicker = 0l;
	private Rect srcRect;
	public static final int speed = 4;
	
	public Timmy(Bitmap bmp, int x, int y, int fps, boolean loop ) {
		this.bmp = bmp;
		this.x = x;
		this.y = y;
		this.fps = fps;
		this.spriteWidth = bmp.getWidth() / bmp_columns;
		this.spriteHeight = bmp.getHeight() / bmp_rows;
		srcRect = new Rect(0,0,spriteWidth,spriteHeight);
	}
	/**
	 * Call this to draw Timmy.
	 */
	public void draw(Canvas canvas) {
		update(System.currentTimeMillis());
		Rect dst = new Rect(x,y,x+spriteWidth,y+spriteHeight);
		canvas.drawBitmap(bmp, srcRect, dst, null);
	}
	
	private void update(long gameTime) {
		if(!isStopped) {
			if(gameTime > frameTicker+(1000/fps)) {
				frameTicker = gameTime;
				currentFrame = ++currentFrame % bmp_columns;
			}
			this.srcRect.left = currentFrame * spriteWidth;
			this.srcRect.right = this.srcRect.left + spriteWidth;
			this.srcRect.top = currentRow * spriteHeight;
			this.srcRect.bottom = this.srcRect.top + spriteHeight;
		} else {
			this.srcRect.left = 0;
			this.srcRect.right = spriteWidth;
			this.srcRect.top = currentRow * spriteHeight;
			this.srcRect.bottom = this.srcRect.top + spriteHeight;
		}
		
	}
	
	public boolean isStopped() {
		return this.isStopped;
	}
	
	public void setStopped(boolean stop) {
		this.isStopped = stop;
	}
	public void setCurrentRow(int row) {
		this.currentRow = row;
	}
	
	public int getCurrentRow() {
		return this.currentRow;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Rect getHitbox(){
		return new Rect(x,y,x+spriteWidth,y+spriteHeight);
	}
	
	public Bitmap getBitmap(){
		return this.bmp;
	}
}
