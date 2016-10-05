package com.moustache.xmlsaxparser;

import java.io.File;
import java.io.StringReader;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
String xmlString="<usuarios><usuario><usrId>-1</usrId><Error>Usuario no encontrado</Error></usuario></usuarios>";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
	    try {
	        SAXParser saxParser = saxParserFactory.newSAXParser();
	        UsuariosHandler handler = new  UsuariosHandler();
	        InputSource is=new InputSource(new StringReader(xmlString));
	        saxParser.parse(is, handler);
	    }
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

}
