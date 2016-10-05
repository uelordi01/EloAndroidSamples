package com.example.seekbar;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.SeekBar;

public class Recording_progress_bar extends View {
	private View m_recording_layout;
	private Context m_context;
	private SeekBar m_seek_recording;
	private FrameLayout m_parent;
	private String LOG_TAG="RecordingProgressBar";

	public Recording_progress_bar(Context context) {
		super(context);
		m_context=context;
		// TODO Auto-generated constructor stub
	}
	public void setParent(View parent)
	{
		m_parent=(FrameLayout)parent;
		initializeRecordBar();
	}
	public void initializeRecordBar()
	{
		 LayoutInflater factory = LayoutInflater.from(m_context);
		 m_recording_layout = factory.inflate(R.layout.record_progressbar_layout, null);
		 m_seek_recording=(SeekBar)m_recording_layout.findViewById(R.id.recording_seek_bar);
		 m_parent.addView(m_recording_layout);
		 m_recording_layout.setVisibility(View.GONE);
	}
	public void showRecordingBar()
	{
		m_recording_layout.setVisibility(View.VISIBLE);
	}
	public void startRecProgress()
	{
		ProgressBarAsync tarea=new ProgressBarAsync();
		tarea.execute();
	}
	private class ProgressBarAsync extends AsyncTask<Void, Integer, Boolean> {
		 
	    @Override
	    protected Boolean doInBackground(Void... params) {
	 
	        for(int i=1; i<=10; i++) {
	 
	            publishProgress(i*10);
	            try {
					Thread.sleep(150);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            if(isCancelled())
	                break;
	        }
	 
	        return true;
	    }
	 
	    @Override
	    protected void onProgressUpdate(Integer... values) {
	        int progreso = values[0].intValue();
	        m_seek_recording.setProgress(progreso);
	       // m_progress_bar.setProgress(progreso);
	    }
	 
	    @Override
	    protected void onPreExecute() {
	    	m_seek_recording.setMax(100);
	    	m_seek_recording.setProgress(0);
	    }
	 
	    @Override
	    protected void onPostExecute(Boolean result) 
	    {
	        if(result)
	        {
	        	Log.v(LOG_TAG, "___ Animation BAR onPostExecute!");
	        	//you have to change the FSM STATE MODE
	           // Log.v(LOG_TAG, "___ Animation BAR onPostExecute!");
	        	//FSMController.getInstance().updateState(ACTION_BUTTON_LABELS.A_OK);
	        	//progress_bar_finished=true;
	        }
	    }
	 
	    @Override
	    protected void onCancelled() {
	    	Log.v(LOG_TAG, "___Tarea cancelada!");
	    }

}
}
