package com.nitologin;

import com.nitologin.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
public static String error="there has been error";
public static String ok="your system has been logged";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void login(View view)
	{
		Intent intent = new Intent(this,ControlPanel.class);
		EditText editText = (EditText) findViewById(R.id.user_editText);
		String user=editText.getText().toString();
		EditText passText= (EditText) findViewById(R.id.pass_editText);
		String pass=passText.getText().toString();
		if((user.compareTo("admin")==0) && pass.compareTo("admin")==0)
		{
			intent.putExtra("error", ok);
		}
		else
		{
			intent.putExtra("error", error);
		}
		startActivity(intent);
	}

}
