package alekuba.timmy_the_time_traveller;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.VideoView;
/**
 * Class for the intro.
 * @author Zacky
 *
 */
public class VideoActivity extends Activity implements OnClickListener, OnCompletionListener, OnTouchListener {
	private VideoView videoView;
	private Button skipButton;
	private static final String TAG = VideoActivity.class.getSimpleName();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		if (getIntent().getBooleanExtra("EXIT", false)) 
		{
		        finish();
		}
		
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
 				WindowManager.LayoutParams.FLAG_FULLSCREEN);
 		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_video);
		videoView = (VideoView)findViewById(R.id.videoView);
		
		Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.intro);
		videoView.setVideoURI(videoUri);
		
		skipButton = (Button)findViewById(R.id.skipButton);
		skipButton.setOnClickListener(this);
		
		videoView.setOnCompletionListener(this);
		
		videoView.setOnTouchListener(this);
		videoView.requestFocus();
		
		videoView.start();
	}
	
	@Override
	public void onClick(View arg0) {
		Log.d(TAG, "onClick");
		skipVideo();
		
	}

	public void onResume(View arg0){
		Log.d(TAG, "onResume");
		skipVideo();
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		skipVideo();
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		skipVideo();
		return false;
	}
	
	private void skipVideo() {
		if(videoView.isPlaying()) {
			videoView.stopPlayback();
		}
		Intent intent = new Intent(this,MainActivity.class);
		startActivity(intent);
		
		
	}

}
