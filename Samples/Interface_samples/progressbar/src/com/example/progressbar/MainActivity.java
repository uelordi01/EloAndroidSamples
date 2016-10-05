package com.example.progressbar;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ProgressBar pb;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		pb=(ProgressBar)findViewById(R.id.progressBar1);

		MiTareaAsincrona a=new MiTareaAsincrona();
		a.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private class MiTareaAsincrona extends AsyncTask<Void, Integer, Boolean> {
		 
	    @Override
	    protected Boolean doInBackground(Void... params) {
	 
	        for(int i=1; i<=10; i++) {
	 
	            publishProgress(i*10);
	            try {
					Thread.sleep(1000);
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
	        pb.setProgress(progreso);
	    }
	 
	    @Override
	    protected void onPreExecute() {
	    	pb.setMax(100);
	    	pb.setProgress(0);
	    }
	 
	    @Override
	    protected void onPostExecute(Boolean result) {
	        if(result)
	            Log.v("a", "Tarea finalizada!");
	    }
	 
	    @Override
	    protected void onCancelled() {
	    	Log.v("a", "Tarea cancelada!");
	    }

}
}
