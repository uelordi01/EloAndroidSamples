package com.libmoustache.swipelayout;

import com.libmoustache.swipelayout.LayoutCustomHandler.LAYOUT_CURRENT_STATE;
import com.libmoustache.swipelayout.LayoutCustomHandler.LAYOUT_ORIENTATION;
import com.libmoustache.swipelayout.LayoutCustomHandler.LAYOUT_TRANSITION_ORIENTATION;

import android.os.Bundle;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	LinearLayout m_green;
	LinearLayout m_black;
	LinearLayout m_blue;
	LinearLayout m_yellow;
	
	LayoutCustomHandler pruebaGreen;
	LayoutCustomHandler pruebaBlue;
	LayoutCustomHandler pruebaYellow; 
	LayoutCustomHandler pruebaBlack;
	View m_current_greenInflatedView;
	LinearLayout m_current_greenView;
	LinearLayout m_current_blueView;
	View m_current_blueInflatedView;
	LinearLayout m_current_yellowView;
	View m_current_yellowInflatedView;
	View m_current_blackInflatedView;
	LinearLayout m_current_blackView;
	
	private Context m_context;
	
	private final static String LOG_TAG="swipe_layout";
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		m_context=getApplicationContext();
		m_green=(LinearLayout)findViewById(R.id.green);
		
		
		
		
		pruebaGreen=new LayoutCustomHandler(m_context);
		LayoutInflater factory = LayoutInflater.from(m_context);
		m_current_greenInflatedView= factory.inflate(R.layout.green_layout, null);
		m_current_greenView=(LinearLayout)m_current_greenInflatedView.findViewById(R.id.green_layout_lin);
		
		
		pruebaGreen.initialize(m_green,m_current_greenInflatedView, m_current_greenView);
		pruebaGreen.setLayoutOrientation(LAYOUT_ORIENTATION.L_TOP);
		pruebaGreen.setOffset(25);
		pruebaGreen.setVisibilityState(true);
		pruebaGreen.addLayout();

		pruebaGreen.showLayout(true);
		
		pruebaBlue=new LayoutCustomHandler(m_context);
		
		LayoutInflater factory1 = LayoutInflater.from(m_context);
		m_current_blueInflatedView= factory1.inflate(R.layout.blue_layout, null);
		m_current_blueView=(LinearLayout)m_current_blueInflatedView.findViewById(R.id.blue_layout_lin);
		
		m_blue=(LinearLayout)findViewById(R.id.blue);
		pruebaBlue.initialize(m_blue,m_current_blueInflatedView, m_current_blueView);
		pruebaBlue.setLayoutOrientation(LAYOUT_ORIENTATION.L_LEFT);
		pruebaBlue.setOffset(25);
		pruebaBlue.setVisibilityState(true);
		pruebaBlue.addLayout();

		pruebaBlue.showLayout(true);
		
		pruebaYellow=new LayoutCustomHandler(m_context);
		
		LayoutInflater factory2 = LayoutInflater.from(m_context);
		m_current_yellowInflatedView= factory2.inflate(R.layout.yellow_layout, null);
		m_current_yellowView=(LinearLayout)m_current_yellowInflatedView.findViewById(R.id.yellow_layout_lin);
		
		m_yellow=(LinearLayout)findViewById(R.id.yellow);
		pruebaYellow.initialize(m_yellow,m_current_yellowInflatedView, m_current_yellowView);
		pruebaYellow.setLayoutOrientation(LAYOUT_ORIENTATION.L_RIGHT);
		pruebaYellow.setOffset(25);
		pruebaYellow.setVisibilityState(true);
		pruebaYellow.addLayout();

		pruebaYellow.showLayout(true);
		
		LayoutInflater factory3 = LayoutInflater.from(m_context);
		m_current_blackInflatedView= factory3.inflate(R.layout.black_layout, null);
		m_current_blackView=(LinearLayout)m_current_blackInflatedView.findViewById(R.id.black_layout_lin);
		
		pruebaBlack=new LayoutCustomHandler(m_context);
		
		m_black=(LinearLayout)findViewById(R.id.black);
		pruebaBlack.initialize(m_black,m_current_blackInflatedView, m_current_blackView);
		pruebaBlack.setLayoutOrientation(LAYOUT_ORIENTATION.L_BOTTOM);
		pruebaBlack.setOffset(25);
		pruebaBlack.setVisibilityState(true);
		pruebaBlack.addLayout();

		pruebaBlack.showLayout(true);
		
		
		
		
		/*m_blue=(LinearLayout)findViewById(R.id.blue);
		m_yellow=(LinearLayout)findViewById(R.id.yellow);
		m_black=(LinearLayout)findViewById(R.id.black);
		m_green_anim = ObjectAnimator.ofFloat(m_green, "translationY",0,100).setDuration(800);
		m_green.setOnTouchListener(new OnFlingGestureListener() {

	        @Override
	        public void onTopToBottom() {
	           //Your code here
	        	Log.v(LOG_TAG," GREEN TOPTOBOTTOM");
	        	m_green_anim.start();
	        }

	        @Override
	        public void onRightToLeft() {
	           //Your code here
	        	Log.v(LOG_TAG," GREEN RIGHTTOLEFT");
	        }

	        @Override
	        public void onLeftToRight() {
	           //Your code here
	        	Log.v(LOG_TAG," GREEN LEFTORIGHT");
	        }

	        @Override
	        public void onBottomToTop() {
	           //Your code here
	        	Log.v(LOG_TAG," GREEN BOTTOMTOTOP");
	        }
	     });
		m_blue.setOnTouchListener(new OnFlingGestureListener() {

	        @Override
	        public void onTopToBottom() {
	           //Your code here
	        	Log.v(LOG_TAG," BLUE TOPTOBOTTOM");
	        }

	        @Override
	        public void onRightToLeft() {
	           //Your code here
	           	Log.v(LOG_TAG," BLUE RIGHTTOLEFT");
	        }

	        @Override
	        public void onLeftToRight() {
	           //Your code here
	        	Log.v(LOG_TAG,"BLUE LEFTORIGHT");
	        }

	        @Override
	        public void onBottomToTop() {
	           //Your code here
	        	Log.v(LOG_TAG," BLUE BOTTOMTOTOP");
	        }
	     });

		m_yellow.setOnTouchListener(new OnFlingGestureListener() {
	
	        @Override
	        public void onTopToBottom() {
	           //Your code here
	        	Log.v(LOG_TAG," YELLOW TOPTOBOTTOM");
	        }
	
	        @Override
	        public void onRightToLeft() {
	           //Your code here
	        	Log.v(LOG_TAG," YELLOW RIGHTTOLEFT");
	        }
	
	        @Override
	        public void onLeftToRight() {
	           //Your code here
	        	Log.v(LOG_TAG,"YELLOW LEFTORIGHT");
	        }
	
	        @Override
	        public void onBottomToTop() {
	           //Your code here
	        	Log.v(LOG_TAG," YELLOW BOTTOMTOTOP");
	        	
	        }
	     });

	m_black.setOnTouchListener(new OnFlingGestureListener() {
	
	    @Override
	    public void onTopToBottom() {
	       //Your code here
	    	Log.v(LOG_TAG," BLACK TOPTOBOTTOM");
	    }
	
	    @Override
	    public void onRightToLeft() {
	       //Your code here
	    	Log.v(LOG_TAG," BLACK RIGHTTOLEFT");
	    }
	
	    @Override
	    public void onLeftToRight() {
	       //Your code here
	    	Log.v(LOG_TAG,"BLACK LEFTORIGHT");
	    }
	
	    @Override
	    public void onBottomToTop() {
	       //Your code here
	    	Log.v(LOG_TAG,"BLACK BOTTOMTOTOP");
	    }
	 });*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
