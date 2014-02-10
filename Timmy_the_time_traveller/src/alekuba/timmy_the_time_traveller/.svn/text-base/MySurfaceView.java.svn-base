package alekuba.timmy_the_time_traveller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v4.view.MotionEventCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
/**
 * Class where all the game logic happens.
 * @author Kuba, Aleks
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
	private Context context;
	private int levelNo;
	private MainThread thread;
	private TimmyController timmy_controller;
	private Rect arrow_left, arrow_right, arrow_up, cancel_rect, sound_rect, heart_rect1, heart_rect2, heart_rect3, time_rect, pause_rect;
	private Level level;
	private SoundPool soundPool;
	private boolean soundEnabled, paused=false;
	private int lifeLostSound;
	private int timeTravelSound;
	private int jumpSound;
	private long startTime=0, elapsedTime=0,startPausedTime = 0, pausedTime;
	
	private static final String TAG = MySurfaceView.class.getSimpleName();
	
	public MySurfaceView(Context context){
		super(context);
	}
	
	public MySurfaceView(Context context, boolean soundEnabled,int level){
		super(context);
		this.levelNo = level;
		this.context = context;
		this.soundEnabled = soundEnabled;
		getHolder().addCallback(this);
		setFocusable(true);
		thread = new MainThread(getHolder(), this);
		timmy_controller = new TimmyController(BitmapFactory.decodeResource(getResources(), R.drawable.timmy));
		
		soundPool = new SoundPool(3,AudioManager.STREAM_MUSIC,0);
		
		lifeLostSound = soundPool.load(this.getContext(), R.raw.lifelost, 1);
		timeTravelSound = soundPool.load(this.getContext(), R.raw.timetravel, 1);
		jumpSound = soundPool.load(this.getContext(),R.raw.jump,1);
	}
	
	public void surfaceCreated(SurfaceHolder holder){
		startTime = System.currentTimeMillis();
		thread.setRunning(true);
		thread.start();
	}
	
	public boolean onTouchEvent(MotionEvent event){
		int ind = MotionEventCompat.getActionIndex(event);
		int action = MotionEventCompat.getActionMasked(event);
		int x = (int)MotionEventCompat.getX(event, ind);
		int y = (int)MotionEventCompat.getY(event, ind);
		
		if(action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN){
			if(controlCancelPressed(x,y)){
				backToMain();
			} else if(controlLeftPressed(x,y) && !paused) {
				timmy_controller.goLeft();
				Log.d(TAG,"ControlbuttonLeft pressed");			
			} else if(controlRightPressed(x,y) && !paused) {
				timmy_controller.goRight();
				Log.d(TAG,"ControlbuttonRight pressed");
			} else if(controlJumpPressed(x,y) && !paused) {
				if(!timmy_controller.getSurpressJump()){
					timmy_controller.jump();
					if(soundEnabled) {
						soundPool.play(jumpSound, 1, 1, 1, 0, 1);
					}
				}
				Log.d(TAG,"ControlbuttonJump pressed");
			} else if(controlSoundPressed(x,y) && !paused) {
				if(soundEnabled) {
					soundEnabled = false;
					soundPool.autoPause();
				} else {
					soundEnabled = true;
					soundPool.autoResume();
				}
			} else if(controlTimebackPressed(x,y) && !timmy_controller.isDepleted() && !paused){
				level.restart();
				timmy_controller.goBackInTime();
				if(soundEnabled) {
					soundPool.play(timeTravelSound, 1, 1, 1, 0, 1);
				}
			} else if(controlPausePressed(x, y)){
				paused = !paused;
				thread.pause();
				if(paused){
					startPausedTime = System.currentTimeMillis();
				}
				else{
					pausedTime += System.currentTimeMillis()-startPausedTime;
					startPausedTime = 0;
				}
			}
		}
		if(action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP) {
			if(controlLeftPressed(x,y) || controlRightPressed(x,y)) {
				timmy_controller.buttonReleased();
			}
			Log.d(TAG, "Button Released");
		}
		return true;
	}
	/**
	 * Gameloop calls this to draw (aka render).
	 */
	protected void onDraw(Canvas canvas){ //Thread ruft diese Methode zum Zeichnen auf!
		loadLevel(canvas);
		drawBackground(canvas);
		level.drawLevel(canvas);
		drawControlButtons(canvas);
		drawTime(canvas);
		timmy_controller.drawTimmy(canvas);
		if(paused){
			drawPaused(canvas);
		}
	}
	
	private void drawPaused(Canvas canvas){
		Paint red = new Paint();
		red.setTextSize(80);
		red.setColor(Color.RED);
		canvas.drawText("PAUSED", canvas.getWidth()/2-200, canvas.getHeight()/2, red);
	}
	
	private void drawBackground(Canvas canvas){
		canvas.drawBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.stars), null, canvas.getClipBounds(),null);
	}
	
	private void loadLevel(Canvas canvas){
		if(level == null) {
			if(levelNo==1) level = new Level1(canvas,this.getResources());
			else if(levelNo==2) level = new Level2(canvas,this.getResources());
		}
	}
	
	private void drawTime(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.WHITE);
		paint.setTextSize(40);
		canvas.drawText("Score: " + String.valueOf(elapsedTime) + "s", 300, 45, paint);
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		thread.interrupt();
		thread.setRunning(false);
		soundPool.release();
		
	}
	/**
	 * Gameloop calls this for updating coordinates of all objects in the game world.
	 */
	public void update(){
		if(level != null) {
		updateElapsedTime();
		level.updateRectCoordinates(timmy_controller.getTimmy(), this.timmy_controller);
		timmy_controller.calculateMovements();
		
			if(level instanceof Level2) {
				if(((Level2) level).movePlatform()) {
					timmy_controller.setX(timmy_controller.getX()-Timmy.speed);
				}
			}
			
			checkCollision();
		}
	}
	
	private void updateElapsedTime() {
		elapsedTime = (System.currentTimeMillis() - startTime - pausedTime) / 1000;
	}

	private void drawControlButtons(Canvas canvas) {
		Bitmap leftarrow = BitmapFactory.decodeResource(getResources(),R.drawable.left);
		Bitmap rightarrow = BitmapFactory.decodeResource(getResources(),R.drawable.right);
		Bitmap uparrow = BitmapFactory.decodeResource(getResources(),R.drawable.up);
		Bitmap cancel = BitmapFactory.decodeResource(getResources(),R.drawable.dialog_cancel);
		Bitmap heart = BitmapFactory.decodeResource(getResources(),R.drawable.heart);
		Bitmap timeback = BitmapFactory.decodeResource(getResources(),R.drawable.time_machine);
		Bitmap pause = BitmapFactory.decodeResource(getResources(),paused? R.drawable.play:R.drawable.pause);
		Bitmap sound;
		if(soundEnabled) {
			sound = BitmapFactory.decodeResource(getResources(), R.drawable.sound);
		} else {
			sound = BitmapFactory.decodeResource(getResources(), R.drawable.no_sound);
		}
		arrow_left = new Rect(50,getHeight()-leftarrow.getHeight()-50,50+leftarrow.getWidth(),getHeight()-50);
		arrow_right = new Rect(200,getHeight()-rightarrow.getHeight()-50,200+rightarrow.getWidth(),getHeight()-50);
		arrow_up = new Rect(getWidth()-100,getHeight()-uparrow.getHeight()-50,getWidth()-100+uparrow.getWidth(),getHeight()-50);
		cancel_rect = new Rect(getWidth()-75, 20, getWidth()-75+cancel.getWidth(), 20+cancel.getHeight());
		sound_rect = new Rect(getWidth()-150,20,getWidth()-150+sound.getWidth(),20+cancel.getHeight());
		heart_rect1 = new Rect (50, 20, 50+heart.getWidth(), 20+heart.getHeight());
		heart_rect2 = new Rect(heart_rect1);
		heart_rect2.offset(heart.getWidth()+10, 0);
		heart_rect3 = new Rect(heart_rect2);
		heart_rect3.offset(heart.getWidth()+10, 0);
		time_rect = new Rect(getWidth()-100,getHeight()-timeback.getHeight()-250,getWidth()-100+timeback.getWidth(),getHeight()-250);
		pause_rect = new Rect(getWidth()-225, 20, getWidth()-225+pause.getWidth(), 20+pause.getHeight());
		canvas.drawBitmap(leftarrow, null, arrow_left, null);
		canvas.drawBitmap(rightarrow, null, arrow_right, null);
		canvas.drawBitmap(uparrow, null, arrow_up, null);
		canvas.drawBitmap(cancel, null, cancel_rect, null);
		canvas.drawBitmap(sound, null, sound_rect, null);
		canvas.drawBitmap(pause, null, pause_rect, null);
		if(!timmy_controller.isDepleted())
			canvas.drawBitmap(timeback, null, time_rect, null);
		drawHp(canvas);
		
	}
	
	private void checkCollision(){
		boolean flag = false;
		for(Rect r : level.getHarmingRects())
			if(timmy_controller.getHitbox().intersect(r))
				if(!timmy_controller.isHarmed()){
					timmy_controller.harm(r);
					if(soundEnabled) {
						soundPool.play(lifeLostSound, 1, 1, 1, 0, 1);
					}
					if(timmy_controller.getHp() == 0)
						splashDead();
				}
		for(Rect r : level.getActionRects())
			if(timmy_controller.getHitbox().intersect(r)){
				level.action(r);
			}
		for(Rect r : level.getNonHarmingRects())
			if(timmy_controller.getHitbox().intersect(r)){
				timmy_controller.collision(r);
				flag = true;
			}
		if(!new Rect(0,0, level.getCanvasWidth(), level.getCanvasHeight()).intersect(timmy_controller.getHitbox())){
			level.restart();
			timmy_controller.harmFall();
			if(soundEnabled) {
				soundPool.play(lifeLostSound, 1, 1, 1, 0, 1);
			}
			if(timmy_controller.getHp() == 0)
				splashDead();
		}
		if(timmy_controller.getHitbox().intersect(level.getFinish())) {
			splashFinish();
		}
		if(!flag)
			timmy_controller.noCollision();
	}
	private void backToMain(){
		Intent intent = new Intent(context, MainActivity.class);
		Log.d(TAG, "SoundEnabled: "+soundEnabled);
		intent.putExtra("alekuba.timmy_the_time_traveller.playSound", soundEnabled);
		context.startActivity(intent);
		thread.setRunning(false);
		((Activity) context).finish();
	}
	private void splashDead(){
		Intent intent = new Intent(context, DeadActivity.class);
		intent.putExtra("alekuba.timmy_the_time_traveller.level", levelNo); //which level just ended
		intent.putExtra("alekuba.timmy_the_time_traveller.playSound", soundEnabled);
		context.startActivity(intent);
		thread.setRunning(false);
		((Activity) context).finish();
	}
	private void splashFinish(){
		Intent intent = new Intent(context,FinishActivity.class);
		intent.putExtra("alekuba.timmy_the_time_traveller.score", elapsedTime); //time for highscore
		intent.putExtra("alekuba.timmy_the_time_traveller.level", levelNo); //which level just ended
		intent.putExtra("alekuba.timmy_the_time_traveller.playSound", soundEnabled);
		context.startActivity(intent);
		thread.setRunning(false);
		((Activity) context).finish();
	}
	private void drawHp(Canvas canvas){
		int hp = timmy_controller.getHp();
		Bitmap emptyHeart = BitmapFactory.decodeResource(getResources(),R.drawable.heart_empty);
		Bitmap heart = BitmapFactory.decodeResource(getResources(),R.drawable.heart);
		switch(hp){
			case 3: canvas.drawBitmap(heart, null, heart_rect1, null);
					canvas.drawBitmap(heart, null, heart_rect2, null);
					canvas.drawBitmap(heart, null, heart_rect3, null);
					break;
			case 2: canvas.drawBitmap(heart, null, heart_rect1, null);
					canvas.drawBitmap(heart, null, heart_rect2, null);
					canvas.drawBitmap(emptyHeart, null, heart_rect3, null);
					break;
			case 1: canvas.drawBitmap(heart, null, heart_rect1, null);
					canvas.drawBitmap(emptyHeart, null, heart_rect2, null);
					canvas.drawBitmap(emptyHeart, null, heart_rect3, null);
					break;
					
		}
	}
	
	private boolean controlLeftPressed(int x, int y) {
		if(arrow_left.contains(x, y)) {
			return true;
		}
		return false;
	}
	private boolean controlRightPressed(int x, int y) {
		if(arrow_right.contains(x, y)) {
			return true;
		}
		return false;
	}
	private boolean controlJumpPressed(int x, int y) {
		if(arrow_up.contains(x, y)) {
			return true;
		}
		return false;
	}
	private boolean controlSoundPressed(int x, int y) {
		if(sound_rect.contains(x,y)) {
			return true;
		}
		return false;
	}
	private boolean controlCancelPressed(int x, int y) {
		if(cancel_rect.contains(x, y)) {
			return true;
		}
		return false;
	}
	private boolean controlTimebackPressed(int x, int y) {
		if(time_rect.contains(x, y)) {
			return true;
		}
		return false;
	}
	private boolean controlPausePressed(int x, int y){
		if(pause_rect.contains(x, y)) {
			return true;
		}
		return false;
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		
	}
}
