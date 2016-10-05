****************************************************************************
			FRAGMENT_BASIC_EXAMPLE
***************************************************************************
Author: Unai Elordi
License: freeBSD
***************************************************************************

INTRODUCTION:
	This samples shows how we can handle dinamyc and static frames. 
	There is an animal list which you can touch each row and therefore 
	other activity is displayes. this is only for mobiles. 

FEATURES: 
	Has a static frame using Framelist (Without db)

	It has implemented the callback methods.

	Fragment sample also control the size of device whether is a 		
	tablet, two fragments are displayed, but it is a mobile phone 2 	
	frames will display in two diferent windows. It depends on the 		
	screen resolution.

	
IMPORTANT NOTES: 

Its very important to define attach function in detallesFrame. 

fragment_Lista extends ListFragment you have to define callback onOptionSected here. 

It is necessary to define callback function body In the main activity, and this activity had to been implemented before, by interface defined in fragment_Lista.

INSTALLATION: 

create android application project in eclipse.

copy .java files in src. 

copy res/layout/{files}.xml
copy res/strings/layout.xml (for all sizes)
see android_manifest file to define extra activity. 
copy res/values/strings.xml because you need to get the labels of buttons.

OTHERS: 
documentation-link: http://jarroba.com/programar-fragments-fragmentos-en-android/


