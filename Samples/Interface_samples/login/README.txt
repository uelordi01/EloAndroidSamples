****************************************************************************
			LOGIN_BASIC_EXAMPLE
***************************************************************************
Author: Unai Elordi
License: freeBSD
***************************************************************************

INTRODUCTION:
	this has a main activity which invokes another login activity. 

FEATURES: 
	login button with static logic (Without db)

	added inline listener with login(View view) function, 

IMPORTANT NOTES: 

you must never define a listener with empty parameters. 
if you want to pass extra parameter to activity: 

the activity which called another activity. 

	Intent in=new Intent();
	in.putExtra("variableName", "value"); 
	startActivity(intent)

Activity which is called by predecesor: 

	in=getIntent();
	String message=e.getStringExtra("error");
	create a text view and push string value: 
	setContentView(textView);

INSTALLATION: 

create android application project in eclipse.

copy .java files in src. 

copy res/layout/{files}.xml
see android_manifest file to define extra activity. 
copy res/values/strings.xml because you need to get the labels of buttons.


