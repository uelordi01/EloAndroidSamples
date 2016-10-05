****************************************************************************
			ANDROID ASYNC PROGRESSBAR
***************************************************************************
Author: Unai Elordi
License: freeBSD
***************************************************************************

INTRODUCTION:
	This samples shows how we can make simple progressbar using android.widget.progressbar.
	we use Android os asynctask, because we need to know when its finished and when its started.

FEATURES: 
	It has a asynctask private class to manage all progressbar events.
	It has a progressbar view in main_activity.xml
	

	
IMPORTANT NOTES: 

Don't forget to add ProgressBar variable to global.

Its necessary to have a onAnimationFinished callback, because we need to change de slide pannel state.

INSTALLATION: 

import android projec in eclipse

or if you have problems try to create android aplication 
{
	copy .java files in src. 
	copy res/layout/{files}.xml
	copy res/strings/layout.xml (for all sizes)
	see android_manifest file to define extra activity. 
	copy res/values/strings.xml because you need to get the labels of buttons.
}
