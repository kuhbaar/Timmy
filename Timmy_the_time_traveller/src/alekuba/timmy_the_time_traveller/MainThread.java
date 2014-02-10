package alekuba.timmy_the_time_traveller;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;
/**
 * The game loop.
 * @author Kuba
 *
 */
public class MainThread extends Thread {
	
	private SurfaceHolder surfaceHolder;
	private MySurfaceView myView;
	private boolean running, paused;
	
	private static final String TAG = MainThread.class.getSimpleName();
	
	private final static int maxFPS = 50;
	private final static int maxFskip = 5;
	private final static int period = 1000 / maxFPS;

	public MainThread(SurfaceHolder sh, MySurfaceView v){
		super();
		this.surfaceHolder = sh;
		this.myView = v;
		this.paused = false;
	}
	/**
	 * Starts the game loop if running == true. To stop call setRunning(false).
	 * @see setRunning(boolean b)
	 */
	@SuppressLint("WrongCall")
	public void run(){
		Log.d(TAG, "Starting game loop");
		Canvas canvas;
		long begin, diff, sleep, fskip;
		sleep = 0;
		while(running){
			canvas = null;
			try{
				canvas = this.surfaceHolder.lockCanvas(null);
				synchronized (this.surfaceHolder){
					if(!paused){
					begin = System.currentTimeMillis();
					fskip = 0;
					//update & render game state
					this.myView.update();
					this.myView.onDraw(canvas);
					diff = System.currentTimeMillis() - begin;
					sleep = (int) (period - diff);
					if(sleep > 0){	//when we are not behind - thread sleep
						try{
							Thread.sleep(sleep);
						} catch (InterruptedException e){
							e.printStackTrace();
						}
					}
					while(sleep < 0 && fskip < maxFskip){ //else we need to catch up
						this.myView.update();//update without rendering
						sleep += period;
						fskip ++;
					}
					}
					else //pause
						try {
							Thread.sleep(300);
							this.myView.onDraw(canvas);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
				}
			} finally{
					if(canvas != null)
						this.surfaceHolder.unlockCanvasAndPost(canvas);
			}
			
		}
	}
	/**
	 * Sets running to true/false. Use it for starting/stoping the loop.
	 * @param b
	 */
	public void setRunning(boolean b){
		this.running = b;
	}
	/**
	 * use it to trigger the state of the loop between paused/not paused.
	 */
	public void pause(){
		this.paused = !paused;
	}
}
