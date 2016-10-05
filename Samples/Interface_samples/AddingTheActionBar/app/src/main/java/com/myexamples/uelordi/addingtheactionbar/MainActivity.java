package com.myexamples.uelordi.addingtheactionbar;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_main, menu);
        getMenuInflater().inflate(R.menu.main_activity_actions, menu);
        return true;
    }
    public void openSearch()
    {
        Toast.makeText(this,"openSearch", Toast.LENGTH_LONG);
    }
    public void openMsg()
    {
        Toast.makeText(getApplicationContext(),"openMsg", Toast.LENGTH_LONG);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_search:
            {
                openSearch();
                return true;
            }
            case R.id.action_msg:
            {
                openMsg();
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
