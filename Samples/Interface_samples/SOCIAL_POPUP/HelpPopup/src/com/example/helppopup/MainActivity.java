package com.example.helppopup;

import java.util.ArrayList;


import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

private RelativeLayout parent;
private Context m_context;
private PopUp m_popup;
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		m_context=getApplicationContext();
		m_popup=new PopUp(m_context);
		parent=(RelativeLayout)findViewById(R.id.mainView);
		m_popup.setParent(parent);
		SocialManager.getInstance().init(this, getPackageName());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onOpenDialog(View view)
	{
		m_popup.showPopUpPrincipalShareLayout();
	}

}
