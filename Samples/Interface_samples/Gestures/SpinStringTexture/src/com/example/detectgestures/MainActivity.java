package com.example.detectgestures;

import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity { 
    
    private GestureDetectorCompat mDetector; 
    private int counter=0;
    List<String> strings = Arrays.asList(new String[] {"foo", "bar", "baz"});
    
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<strings.size();i++)
        {
        	String value=strings.get(i);
        }
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());
    }

    @Override 
    public boolean onTouchEvent(MotionEvent event){ 
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
    
    class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private static final String DEBUG_TAG = "Gestures"; 
        
        @Override
        public boolean onDown(MotionEvent event) { 
            Log.d(DEBUG_TAG,"onDown: " + event.toString()); 
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, 
                float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString()+event2.toString());
            TextView viewt=(TextView)findViewById(R.id.textView1);
            if(counter==3)
            {
            	counter=0;
            }
            String tx = strings.get(counter);
            viewt.setText(tx);
            counter++;
            return true;
        }
    }
}