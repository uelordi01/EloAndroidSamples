package com.moustache.androidanimationsmove;


import java.util.ArrayList;

import com.moustache.androidanimationsmove.Lista_adaptador;
import com.moustache.androidanimationsmove.Lista_entrada;
import com.moustache.androidanimationsmove.MainActivity;
import com.moustache.androidanimationsmove.R;


import android.os.Bundle;
import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;



public class MainActivity extends Activity {
	private FrameLayout m_favatar_left_panel;
	private ListView lista;
	//private Animation m_move_left;
	//private Animation m_move_right;
	int offset;
	//private TranslateAnimation m_move_left;
	//private TranslateAnimation m_move_right;
	ObjectAnimator anim;
	private boolean isLeft=true;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		 m_favatar_left_panel=(FrameLayout) findViewById(R.id.favatar_mode_left_panel);
	        ArrayList<Lista_entrada> datos = new ArrayList<Lista_entrada>();  

	        datos.add(new Lista_entrada(R.drawable.gotham_01));
	        datos.add(new Lista_entrada(R.drawable.gotham_02));
	        datos.add(new Lista_entrada(R.drawable.jungle_01));
	        datos.add(new Lista_entrada(R.drawable.jungle_02));
	      
	        lista = (ListView) findViewById(R.id.list_back_view);
	        lista.setAdapter(new Lista_adaptador(this, R.layout.list_back_single, datos){
				@Override
				public void onEntrada(Object entrada, View view) {
			        if (entrada != null) {

			            ImageView imagen_entrada = (ImageView) view.findViewById(R.id.img_back); 
			            if (imagen_entrada != null)
			            	imagen_entrada.setImageResource(((Lista_entrada) entrada).get_idImagen());
			        }
				}
			});

	        lista.setOnItemClickListener(new OnItemClickListener() { 
				@Override
				public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
					Log.v("MainActivity","on click position-> "+posicion);
				}
	        });

    	m_favatar_left_panel.setOnTouchListener(new OnTouchListener() 
    	{
    		    @Override
    		    public boolean onTouch(View v, MotionEvent event) {
    		     float deltaFromX;
    		     float deltaXTo;
    		     int width=v.getLayoutParams().width;
    		     int position[]=new int[2];
    		     v.getLocationOnScreen(position);
    		     
    		     if(isLeft)
    		     {
    		    	 deltaFromX=0;
    		    	 deltaXTo=316;
    		    	 Log.v("MainActivity","move right");
    		     }
    		     else
    		     {
    		    	 Log.v("MainActivity","move left");
    		    	 deltaFromX=316;
    		    	 deltaXTo=0f;
    		     }
    		    	 try
    		    	{
    		    		 anim = ObjectAnimator.ofFloat(v, "translationX",deltaFromX,deltaXTo).setDuration(800);
    		    		 anim.addListener(new AnimatorListener()
    		    		 {

							@Override
							public void onAnimationEnd(Animator arg0) {
								// TODO Auto-generated method stub
								Log.v("Main activity","animation ended");
								isLeft=!isLeft;
							}
	
							@Override
							public void onAnimationStart(Animator arg0) {
								// TODO Auto-generated method stub
								
							}
	
							@Override
							public void onAnimationCancel(Animator animation) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void onAnimationRepeat(Animator animation) {
								// TODO Auto-generated method stub
								
							}
    		 			
    		 			
    		    		 });
    		    		 
    				anim.start();
    		    	 }
    		    	 catch(Exception e)
    		    	 {
    		    		 Log.e("MainActivity","FATAL ERROR "+e.getMessage());
    		    	 }
    		     return true;
    		    }
    		    });
    	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	

}
