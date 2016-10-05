****************************************************************************
			ADDING THE ACTION BAR
***************************************************************************
Author: Unai Elordi
License: freeBSD
***************************************************************************

INTRODUCTION:
	This samples shows how we can handle simple action bar. 
	It seems ActionBarActivity is deprecated by Toolbar class, but I did the sample with the first class which his parent is appCompatActivity. (appcompat 7).
	
Dificulty: Easy.

FEATURES: 
	
	It has an action bar with icons and callbacks.
	
IMPORTANT NOTES: 

This project is created by android developer Studio, so is build using gradle, please install before to import the project. 

Action bar is almost a menu, but the representation is slightly diferente, so thats why it is necessary to change some few lines in your custom menu layout file. 

go to menu folder and create main_activity_actions.xml menu file, with right click, new->resource menu file

now add every menu items that is necessary for your project, for example : 
main_activity_actions.xml

<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:yourapp="http://schemas.android.com/apk/res-auto">
    <item android:id="@+id/action_search"
        android:icon="@drawable/ic_action_search"
        android:title="@string/action_search"
        yourapp:showAsAction="ifRoom"
        />
    <item android:id="@+id/action_msg"
        android:icon="@drawable/message_outline"
        android:title="@string/action_search"
        yourapp:showAsAction="ifRoom"
        />
    <!-- Settings, should always be in the overflow -->
    <item android:id="@+id/action_settings"
        android:title="@string/action_settings"
        />
</menu>
 
 The key is to define a custom namespace if you want to see the action bar outside from the menu, 
 xmlns:yourapp="http://schemas.android.com/apk/res-auto"
 
 and now specify that you want to see in the action bar mode, writing yourapp:showAsAction="ifRoom"
 

INSTALLATION: 

install android developer studio.

OTHERS: 
documentation-link: http://jarroba.com/programar-fragments-fragmentos-en-android/


