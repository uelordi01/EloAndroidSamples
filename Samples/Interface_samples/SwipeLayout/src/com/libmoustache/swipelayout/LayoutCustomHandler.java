package com.libmoustache.swipelayout;



import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class LayoutCustomHandler {
	enum LAYOUT_TRANSITION_ORIENTATION
	{
		HORIZONTAL,
		VERTICAL
	}
	enum LAYOUT_CURRENT_OFFSET
	{
		NOT_DEFINED_TRANSLATION,
		TOPTOBOTTOM,
		BOTTOMTOTOP,
		LEFTTORIGHT,
		RIGHTTOLEFT
	}
	enum LAYOUT_CURRENT_STATE
	{
		ONLEFTSTATE,
		ONRIGHTSTATE,
		ONTOPSTATE,
		ONBOTTOMSTATE,
		NOT_DEFINED
	}
	enum LAYOUT_ORIENTATION
	{
		L_LEFT,
		L_TOP,
		L_BOTTOM,
		L_RIGHT
	}
	enum LAYOUT_VISIBLE
	{
		L_VISIBLE,
		L_HIDED
	}
	private Context m_context;
	
	private LAYOUT_ORIENTATION m_lo;
	private LAYOUT_VISIBLE m_lv;
	
	
	private static int m_offset=25;
	ViewGroup m_app_parent;
	View m_current_inflatedView;
	ViewGroup m_current_layout;
	ObjectAnimator m_animator;
	int m_layout_id;
	private final static String LOG_TAG="Green";
	public LayoutCustomHandler(Context context)
	{
		m_context=context;
	}
	
	public boolean initialize(View parent, View inflated_layout,View layout_view)
	{
		m_app_parent=(ViewGroup)parent;
		m_current_inflatedView=(View)inflated_layout;
		m_current_layout=(ViewGroup)layout_view;
		if(m_app_parent!=null)
		{
			Log.v(LOG_TAG,"___Parent layout is initialized ");
			initializeCallbacks();
			return true;
		}
		else
		{
			Log.v(LOG_TAG,"___Parent layout is not initialized ");
			return false;
		}
	}
	public void initializeCallbacks()
	{
		m_current_layout.setOnTouchListener(new OnFlingGestureListener() {

	        @SuppressLint("NewApi")
			@Override
	        public void onTopToBottom() {
	           //Your code here
	        	int currentPositions[]=new int[2];
	        	/*if((m_lto.equals(LAYOUT_TRANSITION_ORIENTATION.VERTICAL)) && (m_lcs.equals(LAYOUT_CURRENT_STATE.ONTOPSTATE)))
	        	{*/
	        		
	        		calculateLayoutTranslation(currentPositions);
	        		m_current_layout.bringToFront();
	        		m_animator=ObjectAnimator.ofFloat(m_current_layout, "translationY",currentPositions[0],currentPositions[1]).setDuration(800);
	        		m_animator.start();
	        		//setLayoutCurrentTranslationState(LAYOUT_CURRENT_STATE.ONBOTTOMSTATE);
	        	//}
	        	Log.v(LOG_TAG," GREEN TOPTOBOTTOM");
	        	//m_green_anim.start();
	        }

	        @SuppressLint("NewApi")
			@Override
	        public void onRightToLeft() {
	           //Your code here
	        	int currentPositions[]=new int[2];
	        	Log.v(LOG_TAG," GREEN RIGHTTOLEFT");
	        	/*if((m_lto.equals(LAYOUT_TRANSITION_ORIENTATION.HORIZONTAL)) && (m_lcs.equals(LAYOUT_CURRENT_STATE.ONRIGHTSTATE)))
	        	{*/
	        		calculateLayoutTranslation(currentPositions);
	        		m_animator=ObjectAnimator.ofFloat(m_current_layout, "translationX",currentPositions[0],currentPositions[1]).setDuration(800);
	        		m_animator.start();
	        		//setLayoutCurrentTranslationState(LAYOUT_CURRENT_STATE.ONLEFTSTATE);
	        	//}
	        }

	        @SuppressLint("NewApi")
			@Override
	        public void onLeftToRight() {
	           //Your code here
	        	int currentPositions[]=new int[2];
	        	/*if((m_lto.equals(LAYOUT_TRANSITION_ORIENTATION.HORIZONTAL))&&(m_lcs.equals(LAYOUT_CURRENT_STATE.ONLEFTSTATE)))
	        	{*/
	        		
	        		calculateLayoutTranslation(currentPositions);
	        		m_animator=ObjectAnimator.ofFloat(m_current_layout, "translationX",currentPositions[0],currentPositions[1]).setDuration(800);
	        		m_animator.start();
	        		//setLayoutCurrentTranslationState(LAYOUT_CURRENT_STATE.ONRIGHTSTATE);
	        	//}
	        	Log.v(LOG_TAG," GREEN LEFTORIGHT");
	        }

	        @SuppressLint("NewApi")
			@Override
	        public void onBottomToTop() {
	           //Your code here
	        	int currentPositions[]=new int[2];
	        	/*if((m_lto.equals(LAYOUT_TRANSITION_ORIENTATION.VERTICAL))&&(m_lcs.equals(LAYOUT_CURRENT_STATE.ONBOTTOMSTATE)))
	        	{*/
	        		calculateLayoutTranslation(currentPositions);
	        		m_animator=ObjectAnimator.ofFloat(m_current_layout, "translationY",currentPositions[0],currentPositions[1]).setDuration(800);
	        		m_animator.start();
	        		//setLayoutCurrentTranslationState(LAYOUT_CURRENT_STATE.ONTOPSTATE);
	        	//}
	        	Log.v(LOG_TAG," GREEN BOTTOMTOTOP");
	        }
	     });
	}
	@SuppressLint("NewApi")
	public void setLayoutOrientation(LAYOUT_ORIENTATION lo)
	{
		m_lo=lo;
	}
	public void setVisibilityState(boolean flag)
	{
		if(flag)
		{
			m_lv=LAYOUT_VISIBLE.L_VISIBLE;
		}
		else
		{
			m_lv=LAYOUT_VISIBLE.L_HIDED;
		}
	}
	public void calculateLayoutTranslation(int positions[])
	{
		int position[] = new int[2];
		m_current_layout.getLocationOnScreen(position);

		int width=m_current_inflatedView.getMeasuredWidth();
		int height=m_current_inflatedView.getMeasuredHeight();
		positions[0]=0;
		positions[1]=0;
			 switch(m_lo)
			 {
				 case L_TOP:
				 {
					 switch(m_lv)
					 {
						 case L_HIDED:
						 {
							 positions[0]=-1*height;
							 positions[1]=0-m_offset;
							 m_lv=LAYOUT_VISIBLE.L_VISIBLE;
							 break;
						 }
						 case L_VISIBLE:
						 {
							 positions[0]=0-m_offset;
							 positions[1]=-1*height+m_offset;
							 m_lv=LAYOUT_VISIBLE.L_HIDED;
							 break;
						 }
					 } 
				   break;
				 }
				 case L_BOTTOM:
				 {
					 switch(m_lv)
					 {
						 case L_HIDED:
						 {
							 positions[0]=height-m_offset;
							 positions[1]=0+m_offset;
							 m_lv=LAYOUT_VISIBLE.L_VISIBLE;
							 break;
						 }
						 case L_VISIBLE:
						 {
							 positions[0]=0+m_offset;
							 positions[1]=height-m_offset;
							 m_lv=LAYOUT_VISIBLE.L_HIDED;
							 break;
						 }
					 }
					 break;
				 }
				 case L_LEFT:
				 {
					 switch(m_lv)
					 {
						 case L_HIDED:
						 {
							 positions[0]=-1*width;
							 positions[1]=0-m_offset;
							 m_lv=LAYOUT_VISIBLE.L_VISIBLE;
							 break;
						 }
						 case L_VISIBLE:
						 {
							 positions[0]=0-m_offset;
							 positions[1]=-1*width+m_offset;
							 m_lv=LAYOUT_VISIBLE.L_HIDED;
							 break;
						 }
					 } 
					 break;
				 }
				 case L_RIGHT:
				 {
					 switch(m_lv)
					 {
						 case L_HIDED:
						 {
							 positions[0]=width-m_offset;
							 positions[1]=0+m_offset;
							 m_lv=LAYOUT_VISIBLE.L_VISIBLE;
							 break;
						 }
						 case L_VISIBLE:
						 {
							 positions[0]=0-m_offset;
							 positions[1]=width-m_offset;
							 m_lv=LAYOUT_VISIBLE.L_HIDED;
							 break;
						 }
					 } 
					 break;
				 }
				 default:
				 {
					 break;
				 }
			 }
		 
		/*if(m_lto.equals(LAYOUT_TRANSITION_ORIENTATION.HORIZONTAL))
		{
			
			if(current_layout_x==-1)
			{
				positions[0]=position[0];
			}
			else
			{
				positions[0]=current_layout_x;
			}
			if(lco.equals(LAYOUT_CURRENT_OFFSET.LEFTTORIGHT))
			{
				positions[1]=positions[0]+width-offset;
			}
			if(lco.equals(LAYOUT_CURRENT_OFFSET.RIGHTTOLEFT))
			{
				positions[1]=positions[0]-width+offset;

			}
			current_layout_x=positions[1];
		}
		else if(m_lto.equals(LAYOUT_TRANSITION_ORIENTATION.VERTICAL))
		{
			/*if(current_layout_y==-1)
			{
				positions[0]=position[0];
			}
			else
			{
				positions[0]=current_layout_y;
			}*/
		/*	if(lco.equals(LAYOUT_CURRENT_OFFSET.TOPTOBOTTOM))
			{
				//positions[1]=positions[0]+height-offset;
				if(current_layout_y==-1)
				{
					if(m_lcs.equals(LAYOUT_CURRENT_STATE.ONBOTTOMSTATE))
					{
						
					}
					if(m_lcs.equals(LAYOUT_CURRENT_STATE.ONTOPSTATE))
					{
						
					}
				}
				positions[0]=-1*height;
				positions[1]=0;
			}
			if(lco.equals(LAYOUT_CURRENT_OFFSET.BOTTOMTOTOP))
			{
				//positions[1]=positions[0]-height+offset;
				positions[0]=0;
				positions[1]=-1*height + offset;
			}
			current_layout_y=positions[1];
		}*/
		Log.v(LOG_TAG,"Current position ["+position[0]+","+position[1]+"]");
	}
	public void setOffset(int offset)
	{
		m_offset=offset;
	}
	public void addLayout()
	{
		m_app_parent.addView(m_current_inflatedView);
	}
	public void showLayout(boolean flag)
	{
		if(flag)
		{
			m_current_layout.setVisibility(View.VISIBLE);
		}
		else
		{
			m_current_layout.setVisibility(View.GONE);
		}

	}
	public void setLayoutId(int id)
	{
		m_layout_id=id;
	}
	public ViewGroup getView()
	{
		return m_current_layout;
	}
	public void removeLayout()
	{
		m_app_parent.removeView(m_current_inflatedView);
	}

}
