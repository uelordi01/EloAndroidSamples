****************************************************************************
			ANDROID ANIMATIONS MOVE
***************************************************************************
Author: Unai Elordi
License: freeBSD
***************************************************************************

INTRODUCTION:
	This samples shows how we can slide a pannel using android animations.
	using onTouch element we can show and hide the frameLayout pannel

FEATURES: 
	It has Object animator class to override xtranslation animation.
	In android animations, its usual to animate views, but unafortunately sometimes this view is only a illusion, In fact, 
	it doesn't move the view, thats why, if we do this example, we need objectAnimator sample.

	Fragment sample also control the size of device whether is a 		
	tablet, two fragments are displayed, but it is a mobile phone 2 	
	frames wil

	
IMPORTANT NOTES: 

Its very important to define your minimum sdk api to 11.

Its necessary to have a onAnimationFinished callback, because we need to change de slide pannel state.

INSTALLATION: 

create android application project in eclipse.

copy .java files in src. 

copy res/layout/{files}.xml
copy res/strings/layout.xml (for all sizes)
see android_manifest file to define extra activity. 
copy res/values/strings.xml because you need to get the labels of buttons.



