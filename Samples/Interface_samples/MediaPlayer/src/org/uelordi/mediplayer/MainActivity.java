package org.uelordi.mediplayer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends Activity {
Context m_context;
private Playback_progress_bar m_pl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		m_context=getApplicationContext();
		m_pl=new Playback_progress_bar(m_context);
		FrameLayout parent=(FrameLayout)findViewById(R.id.parent); 
		m_pl.setParent(parent);
		m_pl.initializePlaybackBar();
		m_pl.showLayout();
		
		
	}
	public void showLayout(View view)
	{
		m_pl.initializePlaybackBar();
		m_pl.showLayout();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
