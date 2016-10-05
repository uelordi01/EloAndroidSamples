package org.uelordi.mediplayer;


import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

public class Playback_progress_bar extends View {
	private View m_playback_layout;
	private Context m_context;
	private SeekBar m_seek_playback;
	private FrameLayout m_parent;
	private ProgressBarAsync m_play_tarea;
	private FrameLayout playback_frame_layout;
	private ImageButton m_play_button;
	private ImageButton m_pause_button;
	private ImageButton m_stop_button;
	private static final String LOG_TAG ="Playback_progress_bar";
	private VideoView m_videoView;
	private MediaController m_mediaController;
	private String m_videoName;
	private PLAYBACK_STATES m_state;
	enum PLAYBACK_STATES
	{
		PLAYING,
		PAUSED,
		STOPPED,
		FINISHED,
	}
	public Playback_progress_bar(Context context) {
		super(context);
		m_context=context;
		// TODO Auto-generated constructor stub
	}
	public void setParent(View parent)
	{
		m_parent=(FrameLayout)parent;
		initializePlaybackBar();
	}
	public void initializePlaybackBar()
	{
		 LayoutInflater factory = LayoutInflater.from(m_context);
		 m_playback_layout = factory.inflate(R.layout.playback_progressbar_layout, null);
		 
		 m_seek_playback=(SeekBar)m_playback_layout.findViewById(R.id.playback_seek_bar);
		 m_seek_playback.setProgress(0);
		 m_parent.addView(m_playback_layout);
		 
		 
		 playback_frame_layout=(FrameLayout)m_playback_layout.findViewById(R.id.playback_frame_layout);
		 
		 
		 m_play_button=(ImageButton)m_playback_layout.findViewById(R.id.play_button);
		 m_pause_button=(ImageButton)m_playback_layout.findViewById(R.id.pause_button);
		 m_stop_button=(ImageButton)m_playback_layout.findViewById(R.id.stop_button);
		 m_state=PLAYBACK_STATES.STOPPED;
		 m_play_button.setOnClickListener(new OnClickListener() {
			    public void onClick(View v)
			    {
			        //DO SOMETHING! {RUN SOME FUNCTION ... DO CHECKS... ETC}
			    	//startPlaybackProgress();
			    	m_pause_button.setVisibility(View.VISIBLE);
			    	m_stop_button.setVisibility(View.VISIBLE);
			    	m_play_button.setVisibility(View.GONE);
			    	
			    	try {
						play();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	Log.v(LOG_TAG,"___playback play button pressed");
			    	
			    } 
			});
		 
		 
		 m_pause_button.setOnClickListener(new OnClickListener() {
			    public void onClick(View v)
			    {
			        //DO SOMETHING! {RUN SOME FUNCTION ... DO CHECKS... ETC}
			    	 try {
						pause();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			    	 m_pause_button.setVisibility(View.GONE);
			    	 m_stop_button.setVisibility(View.VISIBLE);
			    	 m_play_button.setVisibility(View.VISIBLE);
			    	Log.v(LOG_TAG,"___playback pause button pressed");
			    	
			    } 
			});
		 	m_stop_button.setOnClickListener(new OnClickListener() {
			    public void onClick(View v)
			    {
			        //DO SOMETHING! {RUN SOME FUNCTION ... DO CHECKS... ETC}

					 stop();
			    	 m_pause_button.setVisibility(View.GONE);
			    	 m_stop_button.setVisibility(View.GONE);
			    	 m_play_button.setVisibility(View.VISIBLE);
			    	Log.v(LOG_TAG,"___playback stop button pressed");
			    	
			    } 
			});
		 
		 
		 m_pause_button.setVisibility(View.GONE);
		 
		 //media player
		 m_videoView =(VideoView)m_playback_layout.findViewById(R.id.playback_video_view);
		 m_mediaController= new MediaController(m_context);
		 configVideo("");
		 m_state=PLAYBACK_STATES.STOPPED;
		 m_play_tarea=new ProgressBarAsync();
		 
		 
		 playback_frame_layout.setVisibility(View.GONE);
	}
	public void RemoveLayout()
	{
		 m_parent.removeView(m_playback_layout);
	}
	public void configVideo(String videoName)
	{
		m_videoName=videoName;
		String m_videoPath="android.resource://"+m_context.getPackageName()+"/raw/video";
		m_mediaController.setAnchorView(m_videoView);        
	    Uri uri=Uri.parse(m_videoPath);    
	    try
	    {
	    m_videoView.setMediaController(m_mediaController);
	    m_videoView.setVideoURI(uri);        
	    m_videoView.requestFocus();
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	}
	public void showLayout()
	{
		playback_frame_layout.setVisibility(View.VISIBLE);
	}
	public void hideLayout()
	{
		playback_frame_layout.setVisibility(View.GONE);
	}
	public void startPlaybackProgress()
	{
		
		m_play_tarea.execute();
	}
	public void stopRecProgress()
	{
		m_play_tarea.cancel(true);
	}
	
	public void play() throws InterruptedException
	{	
		if(m_state.compareTo(PLAYBACK_STATES.STOPPED)==0)
		{
			m_play_tarea.execute();
			Log.v(LOG_TAG,"___playButton Stopped to play");
			
		}
		if(m_state.compareTo(PLAYBACK_STATES.PAUSED)==0)
		{
			Log.v(LOG_TAG,"___playButton paused to play");
			//m_play_tarea.notify();
		}
		if(m_state.compareTo(PLAYBACK_STATES.FINISHED)==0)
		{
			m_play_tarea=new ProgressBarAsync();
			configVideo("");
			Log.v(LOG_TAG,"___playButton finished to play");
			m_play_tarea.execute();
			
		}
		m_state=PLAYBACK_STATES.PLAYING;
		m_videoView.start();
	}
        // Close the progress bar and play the video
        
	public void pause() throws InterruptedException
	{
		if(m_state.compareTo(PLAYBACK_STATES.PLAYING)==0)
		{
			m_state=PLAYBACK_STATES.PAUSED;
			m_videoView.pause();
		}
	}
	public void stop()
	{
			m_state=PLAYBACK_STATES.FINISHED;
			m_videoView.stopPlayback();
			m_play_tarea.cancel(true);
			RemoveLayout();
	}
	private class ProgressBarAsync extends AsyncTask<Void, Integer, Boolean> {
		 private int m_videoDuration;
		 private int counter;
	    @Override
	    protected Boolean doInBackground(Void... params) {
	 
	    	while((counter<m_videoDuration) && ((m_state.compareTo(PLAYBACK_STATES.FINISHED)!=0)))
	    	{
	   
	    		if(m_state.compareTo(PLAYBACK_STATES.PLAYING)==0)
	    		{
	    			counter++;
	    			publishProgress(counter*10);
	    			
	    			try {
	 					Thread.sleep(150);
	 				} catch (InterruptedException e) {
	 					// TODO Auto-generated catch block
	 					e.printStackTrace();
	 				}
	    		}
	    		if(isCancelled())
	   		 	{
	    		Log.v(LOG_TAG," ___AsyncThreadcancelled");
		         break;
	   			}	
	    	}
	    
	 
	        return true;
	    }
	 
	    @Override
	    protected void onProgressUpdate(Integer... values) {
	    	int position=m_videoView.getCurrentPosition();
	        m_seek_playback.setProgress(position);
	       
	       // m_progress_bar.setProgress(progreso);
	    }
	 
	    @Override
	    protected void onPreExecute() {
	    	m_videoDuration=m_videoView.getDuration();
	    	m_seek_playback.setMax(m_videoDuration);
	    	m_seek_playback.setProgress(0);
	    	Log.v(LOG_TAG, "___ASYNCTASK BAR ONPREEXECUTE!");
	    	counter=0;
	    	
	    	
	    	
	    }
	 
	    @Override
	    protected void onPostExecute(Boolean result) 
	    {
	        if(result)
	        {
	        	Log.v(LOG_TAG, "___ ASINCTASK BAR onPostExecute!");
	        	m_state=PLAYBACK_STATES.FINISHED;
	        }
	    }
	 
	    @Override
	    protected void onCancelled() {
	    	Log.v(LOG_TAG, " ___ASYNCTASK ___Tarea cancelada!");
     
	    }

}
}
