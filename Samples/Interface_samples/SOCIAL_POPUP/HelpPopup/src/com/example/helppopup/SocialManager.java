package com.example.helppopup;

import java.util.List;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.util.Log;
import android.widget.Toast;


public class SocialManager
{
	private Activity _androidActivity = null;
	private String _packName = "";
	private static final String LOG_TAG = "SocialManager";
	private static final Object sSyncObj = new Object();
	private static volatile SocialManager sInstance;
	public enum SM_PACKAGES
	{
		S_FACEBOOK ("com.facebook.katana"),
		S_EMAIL ("text/html"),
		S_TWITTER ("com.twitter.android"),
		S_WHATSAPP("com.whatsapp"),
		S_LINE("jp.naver.line.android"),
		S_WECHAT("com.tencent.mm"),
		S_TUMBLR("com.tumblr"), 
		S_GOOGLE_PLUS("com.google.android.apps.plus"),
		S_TELEGRAM("org.telegram.messenger"),
		S_SMS("vnd.android-dir/mms-sms");
	    

	    private final String name;       

	    private SM_PACKAGES(String s) {
	        name = s;
	    }

	    public boolean equalsName(String otherName){
	        return (otherName == null)? false:name.equals(otherName);
	    }

	    public String toString(){
	       return name;
	    }

	};
	public SocialManager( ){}
	public static SocialManager getInstance()
	{
	if (sInstance == null) 
    {
        synchronized (sSyncObj) 
        {
            if (sInstance == null) 
            {
            	Log.v(LOG_TAG, "Creating NitoRecorder");
                sInstance = new SocialManager();
            }
        }
    }
    return sInstance;
	}
	public boolean isSocialObjectInit()
	{
		if(_androidActivity!=null && _packName!=null)
		{
			return true;
		}
		else return false;
	}
    public void init(Activity androidActivity, String packName)
	{
    	
		_androidActivity = androidActivity;
		_packName = packName;
	}
	//public void getInstance
	public void openWeb(String direction)
	{
		if( isSocialObjectInit())
		{
		Log.v("Opening web: %s", direction);
		Intent browserIntent = new Intent(Intent.ACTION_VIEW);
		browserIntent.setData(Uri.parse("http://" + direction));
		_androidActivity.startActivity(browserIntent);
		}
		else
		{
			Log.v(LOG_TAG," please execute init method before to execute this function");
		}
	}
	public void openMarketToRate() 
	{
		if( isSocialObjectInit())
		{
		Uri uri = Uri.parse("market://details?id=" + _packName);
		Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
			try 
			{
				_androidActivity.startActivity(goToMarket);
			} 
			catch (ActivityNotFoundException e) 
			{
				Toast.makeText(_androidActivity, "No in market!", Toast.LENGTH_LONG).show();
			}
		}
		else
		{
			Log.v(LOG_TAG," please execute init method before to execute this function");
		}
	}
	public void sendEMail(String to, String subject, String message)
	{
		if( isSocialObjectInit())
		{
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("text/html");
		String[] recipients = new String[]{to};
		intent.putExtra(Intent.EXTRA_EMAIL, recipients);
		intent.putExtra(Intent.EXTRA_SUBJECT, subject);
		intent.putExtra(Intent.EXTRA_TEXT, message);
		_androidActivity.startActivity(Intent.createChooser(intent, "Share with"));
		}
		else
		{
			Log.v(LOG_TAG," please execute init method before to execute this function");
		}
	}
	public void sendSms(String Text)
	{
		try 
		{	
		Intent sendIntent = new Intent(Intent.ACTION_VIEW);
		sendIntent.putExtra("sms_body", Text); 
		sendIntent.setType("vnd.android-dir/mms-sms");
		_androidActivity.startActivity(sendIntent);
		}
		catch(Exception e) {
			Toast.makeText(_androidActivity.getApplicationContext(),
				"SMS faild, please try again later!",
				Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}
	public void shareLine(String message)
	{
		try
		{
		Intent shareIntent = ShareCompat.IntentBuilder.from(_androidActivity)
				   .setText(message)
				   .setType("text/plain")
				   .getIntent()
				   .setPackage(SM_PACKAGES.S_LINE.toString());

				_androidActivity.startActivity(shareIntent);
		}
		catch(Exception e)
		{
			Toast.makeText(_androidActivity.getApplicationContext(),"LINE app doesn't exist", Toast.LENGTH_SHORT).show();
		}
	}
	public void shareTumblr(String message)
	{
		try
		{
		Intent shareIntent = ShareCompat.IntentBuilder.from(_androidActivity)
				   .setText(message)
				   .setType("text/plain")
				   .getIntent()
				   .setPackage(SM_PACKAGES.S_TUMBLR.toString());

				_androidActivity.startActivity(shareIntent);
		}
		catch(Exception e)
		{
			Toast.makeText(_androidActivity.getApplicationContext(),"Tumblr app doesn't exist", Toast.LENGTH_SHORT).show();
		}
	}
	public void shareWeChat(String message)
	{
		try
		{
		Intent shareIntent = ShareCompat.IntentBuilder.from(_androidActivity)
				   .setText(message)
				   .setType("text/plain")
				   .getIntent()
				   .setPackage(SM_PACKAGES.S_WECHAT.toString());

				_androidActivity.startActivity(shareIntent);
		}
		catch(Exception e)
		{
			Toast.makeText(_androidActivity.getApplicationContext(),"WeChat app doesn't exist", Toast.LENGTH_SHORT).show();
		}
	}
	public void shareWhatsApp(String message)
	{
		try
		{
		Intent shareIntent = ShareCompat.IntentBuilder.from(_androidActivity)
				   .setText(message)
				   .setType("text/plain")
				   .getIntent()
				   .setPackage(SM_PACKAGES.S_WHATSAPP.toString());

				_androidActivity.startActivity(shareIntent);
		}
		catch(Exception e)
		{
			Toast.makeText(_androidActivity.getApplicationContext(),"WhatsApp app doesn't exist", Toast.LENGTH_SHORT).show();
		}
	}
	public void shareTelegram(String message)
	{


		try
		{
		Intent shareIntent = ShareCompat.IntentBuilder.from(_androidActivity)
				   .setText(message)
				   .setType("text/plain")
				   .getIntent()
				   .setPackage(SM_PACKAGES.S_TELEGRAM.toString());

				_androidActivity.startActivity(shareIntent);
		}
		catch(Exception e)
		{
			Toast.makeText(_androidActivity.getApplicationContext(),"Telegram app doesn't exist", Toast.LENGTH_SHORT).show();
		}

	}
	public void shareGooglePlus(String message)
	{
		try
		{
		Intent shareIntent = ShareCompat.IntentBuilder.from(_androidActivity)
				   .setText(message)
				   .setType("text/plain")
				   .getIntent()
				   .setPackage(SM_PACKAGES.S_GOOGLE_PLUS.toString());

				_androidActivity.startActivity(shareIntent);
		}
		catch(Exception e)
		{
			Toast.makeText(_androidActivity.getApplicationContext(),"GOOGLE APP Doesn't exist", Toast.LENGTH_SHORT).show();
		}
	}
	public void sendATweetIntent(String message)
	{
		if( isSocialObjectInit())
		{
			Intent tweetIntent = new Intent(Intent.ACTION_SEND);
			tweetIntent.putExtra(Intent.EXTRA_TEXT, message);
			tweetIntent.setType("text/plain");
	
			PackageManager packManager = _androidActivity.getPackageManager();
			List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(tweetIntent,  packManager.MATCH_DEFAULT_ONLY);
	
			boolean resolved = false;
			for(ResolveInfo resolveInfo: resolvedInfoList)
			{
			    if(resolveInfo.activityInfo.packageName.startsWith("com.twitter.android"))
			    {
			        tweetIntent.setClassName(
			            resolveInfo.activityInfo.packageName, 
			            resolveInfo.activityInfo.name );
			        resolved = true;
			        break;
			    }
			}
			if(resolved)
			{
				_androidActivity.startActivity(tweetIntent);
			}
			else
			{
			    Toast.makeText(_androidActivity, "Twitter app isn't found", Toast.LENGTH_LONG).show();
			}
		}
		else
		{
			Log.v(LOG_TAG," please execute init method before to execute this function");
		}
	}
	public void sendAFacebookIntent(String heading, String caption, String message, String badge, String deepLink)
	{
		if( isSocialObjectInit())
		{
		Log.v("sendAFacebookIntent: %s", heading);
		Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
		shareIntent.setType("text/plain");
		shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, heading);
		String urlToShare = "http://cna.3monkeybits.com/"/* + caption + message*/;
		shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, urlToShare);
		
		PackageManager packManager = _androidActivity.getPackageManager();
		List<ResolveInfo> resolvedInfoList = packManager.queryIntentActivities(shareIntent,  packManager.MATCH_DEFAULT_ONLY);
		
			boolean resolved = false;
			for (ResolveInfo resolveInfo : resolvedInfoList) 
			{
				// com.facebook.katana = Facebook app
				// 
				if (resolveInfo.activityInfo.packageName.startsWith("com.facebook.katana")) 
				{
					final ActivityInfo activity = resolveInfo.activityInfo;
					final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
					shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
					shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
					shareIntent.setComponent(name);
					_androidActivity.startActivity(shareIntent);
					break;
				}
			}
			if (!resolved) 
			{
			    String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
			    Intent intentWithoutApp = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
			    _androidActivity.startActivity(intentWithoutApp);
			}
		}
		else
		{
			Log.v(LOG_TAG," please execute init method before to execute this function");
		}

	}
}