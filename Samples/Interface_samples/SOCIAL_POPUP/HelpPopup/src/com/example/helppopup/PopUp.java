package com.example.helppopup;

import java.util.ArrayList;

import com.example.helppopup.SocialManager.SM_PACKAGES;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.AdapterView.OnItemClickListener;
public class PopUp extends View
{
	private ArrayList<PopUp_List_Pannel> datosBack; 
	private Context m_context;
	private RelativeLayout m_screenLayout;
	private View m_popup_layout;
	private View m_share_text_layout;
	private ListView m_list_share_pannel;
	private static String LOG_TAG="PopUp";
	private SM_PACKAGES m_application_name;
	private EditText m_user_message;
	
	
	public PopUp(Context context) {
		super(context);
		m_context=context;
		
		// TODO Auto-generated constructor stub
	}
	public void hidePopUpPrincipalShareLayout()
	{
		//m_screenLayout.removeView(m_popup_layout);
		m_popup_layout.setVisibility(View.GONE);
	}
	public void setParent(View parent)
	{
		m_screenLayout=(RelativeLayout)parent;
		initializeSharePopUp();
	}
	public void hidePopUpTextShareLayout()
	{
		m_share_text_layout.setVisibility(View.GONE);
	}
	public void showPopUpTextShareLayout()
	{
		m_share_text_layout.setVisibility(View.VISIBLE);
	}
	public void showPopUpPrincipalShareLayout()
	{
		m_popup_layout.setVisibility(View.VISIBLE);
	}
	public void initializeSharePopUp()
	{
		//initialize the share popup dialog
		LayoutInflater factory = LayoutInflater.from(m_context);
		m_popup_layout = factory.inflate(R.layout.share_layout, null);
		m_screenLayout.addView(m_popup_layout);
		m_popup_layout.setVisibility(View.GONE);
		//initialize share_text_popup
		LayoutInflater factory1 = LayoutInflater.from(m_context);
		m_share_text_layout = factory1.inflate(R.layout.share_text_layout, null);
		m_screenLayout.addView(m_share_text_layout);
		m_share_text_layout.setVisibility(View.GONE);
		initializeShareList();
	       
		
		
		ImageButton close=(ImageButton)m_popup_layout.findViewById(R.id.share_close_button);
		close.setOnClickListener(new OnClickListener() {
		    public void onClick(View v)
		    {
		        //DO SOMETHING! {RUN SOME FUNCTION ... DO CHECKS... ETC}
		    	Log.v("POPUP","closeclicked");
		    	hidePopUpPrincipalShareLayout();
		    } 
		});
		ImageButton cancel=(ImageButton)m_share_text_layout.findViewById(R.id.text_share_cancel);
		cancel.setOnClickListener(new OnClickListener() {
		    public void onClick(View v)
		    {
		        //DO SOMETHING! {RUN SOME FUNCTION ... DO CHECKS... ETC}
		    	Log.v("POPUP","___onCancel");
		    	hidePopUpTextShareLayout();
		    	showPopUpPrincipalShareLayout();
		    } 
		});
		
		ImageButton submit=(ImageButton)m_share_text_layout.findViewById(R.id.submit_share_text);
		submit.setOnClickListener(new OnClickListener() {
		    public void onClick(View v)
		    {
		        //DO SOMETHING! {RUN SOME FUNCTION ... DO CHECKS... ETC}
		    	SubmitSocialAction(m_application_name);
		    	Log.v("POPUP","___onSubmitSharing");
		    } 
		});
		m_user_message=(EditText)m_share_text_layout.findViewById(R.id.share_message_text_back);
	}
	public void initializeShareList()
	{
		datosBack= new ArrayList<PopUp_List_Pannel>();
		datosBack.add(new PopUp_List_Pannel(R.drawable.facebook,SM_PACKAGES.S_FACEBOOK));
		datosBack.add(new PopUp_List_Pannel(R.drawable.email,SM_PACKAGES.S_EMAIL));
		datosBack.add(new PopUp_List_Pannel(R.drawable.google_plus,SM_PACKAGES.S_GOOGLE_PLUS));
		datosBack.add(new PopUp_List_Pannel(R.drawable.twitter,SM_PACKAGES.S_TWITTER));
		datosBack.add(new PopUp_List_Pannel(R.drawable.wechat,SM_PACKAGES.S_WECHAT));
		datosBack.add(new PopUp_List_Pannel(R.drawable.tumbler,SM_PACKAGES.S_TUMBLR));
		datosBack.add(new PopUp_List_Pannel(R.drawable.whats_app,SM_PACKAGES.S_WHATSAPP));
		datosBack.add(new PopUp_List_Pannel(R.drawable.line,SM_PACKAGES.S_LINE));
		datosBack.add(new PopUp_List_Pannel(R.drawable.sms,SM_PACKAGES.S_SMS));
		
		
		m_list_share_pannel = (ListView) m_popup_layout.findViewById(R.id.share_list);
		m_list_share_pannel.setAdapter(new Lista_adaptador(m_context, R.layout.list_share_button_single, datosBack)
        {
			@Override
			public void onEntrada(Object entrada, View view)
			{
		        if (entrada != null) 
		        {
		            ImageView imagen_entrada = (ImageView) view.findViewById(R.id.img_share); 
		            if (imagen_entrada != null)
		            	imagen_entrada.setImageResource(((PopUp_List_Pannel) entrada).get_idImagen());
		        }
			}
		});
		m_list_share_pannel.setOnItemClickListener(new OnItemClickListener() { 
			@Override
			public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
				Log.v("EventController","BACKGROUND_PANNEL on click position-> "+posicion);
				ImageView imagen_entrada = (ImageView) view.findViewById(R.id.img_share);
				int listSize=m_list_share_pannel.getChildCount();
				for(int i=0;i<listSize;i++)
				{
					View vs=m_list_share_pannel.getChildAt(i);
					ImageView im=(ImageView)vs.findViewById(R.id.img_share);
					im.setBackgroundResource(R.drawable.round_corners);			
				}
				imagen_entrada.setBackgroundResource(R.drawable.round_corners_pressed);
				openShareTextDialog(posicion);
			}
        });
		
	}
	public boolean SubmitSocialAction(SM_PACKAGES application_name)
	{
		String message="";
		message=m_user_message.getText().toString();
		switch(application_name)
		{
			case S_FACEBOOK:
			{
				
				SocialManager.getInstance().sendAFacebookIntent("Nito", "Nito Test example", message, "badge", "deepLink");
				Log.v(LOG_TAG,"___S_FACEBOOK");
				break;
			}
			case S_EMAIL:
			{
				Log.v(LOG_TAG,"___S_EMAIL");
				SocialManager.getInstance().sendEMail("", "Nito Test Check", message);
				break;
			}
			case S_GOOGLE_PLUS:
			{
				Log.v(LOG_TAG,"___S_GOOGLEPLUS");
				SocialManager.getInstance().shareGooglePlus(message);
				break;
			}
			case S_LINE:
			{
				SocialManager.getInstance().shareLine(message);
				Log.v(LOG_TAG,"___S_LINE");
				break;
			}
			case S_SMS:
			{
				Log.v(LOG_TAG,"___S_SMS");
				SocialManager.getInstance().sendSms(message);
				break;
			}
			case S_TUMBLR:
			{
				SocialManager.getInstance().shareTumblr(message);
				Log.v(LOG_TAG,"___S_TUMBLR");
				
				break;
			}
			case S_TWITTER:
			{
				SocialManager.getInstance().sendATweetIntent(message);
				Log.v(LOG_TAG,"___S_TWITTER");
				break;
			}
			case S_WECHAT:
			{
				SocialManager.getInstance().shareWeChat(message);
				Log.v(LOG_TAG,"___S_WECHAT");
				break;
			}
			case S_WHATSAPP:
			{
				Log.v(LOG_TAG,"___S_WHATSAPP");
				
				SocialManager.getInstance().shareWhatsApp(message);
				break;
			}
			default:
			{
				Log.v(LOG_TAG,"ERROR IN SELECTING APPLICATION");
			}
		}
		return true;
	}
	public boolean cancelSocialAction()
	{
		hidePopUpTextShareLayout();
		return true;
	}
	public void openShareTextDialog(int position)
	{
		Log.v(LOG_TAG,"___openShareTextDialog");
		m_application_name=datosBack.get(position).getApplicationName();
		showPopUpTextShareLayout();
		hidePopUpPrincipalShareLayout();
	}
}
