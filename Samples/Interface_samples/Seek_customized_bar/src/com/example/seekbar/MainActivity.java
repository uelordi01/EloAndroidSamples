package com.example.seekbar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
	private Context m_context;
	Recording_progress_bar m_rec;
	private FrameLayout m_parent; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		m_context=getApplicationContext();
		 m_rec=new Recording_progress_bar(m_context);
		
	}
	public void onShowProgressbar(View view)
	{
		m_parent=(FrameLayout)findViewById(R.id.screen_parent);
		m_rec.setParent(m_parent);
		m_rec.initializeRecordBar();
		m_rec.showRecordingBar();
		m_rec.startRecProgress();
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
