****************************************************************************
			INTERACTION_BASIC_EXAMPLE
***************************************************************************
Author: Unai Elordi
License: freeBSD
***************************************************************************

INTRODUCTION:

this samples shows how we can catch simple gesture events, in this case, 
using onDown and onSpin(slide) events. 

when you make spin with your finger, the text has changed   

FEATURES: 
	sping actions changes the text which is located in array. 

	The listener only gather onDown and onSpin events.

IMPORTANT NOTES: 

the listener class is implemented in the same activity class

mDetector = new GestureDetectorCompat(this, new MyGestureListener());

MyGestureListener is a class where the event functions are implemented. 


INSTALLATION: 

create android application project in eclipse.

copy .java files in src. 

copy res/layout/{files}.xml
see android_manifest file to define extra activity. 
copy res/values/strings.xml because you need to get the labels of buttons.
