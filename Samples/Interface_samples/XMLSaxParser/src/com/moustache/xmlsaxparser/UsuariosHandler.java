package com.moustache.xmlsaxparser;


import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class UsuariosHandler extends DefaultHandler {
	  boolean bidUsuario = false;
	  boolean bError = false;
	  boolean bNombre = false;
	  boolean bApellidos = false;
	  boolean bUsuario =false;
	  static int numberOfUserNodes=0;
	  private List<UsuariosModel> m_usuarios_list;
	  private UsuariosModel m_usuario_single;
	  
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		if(bUsuario)
		{
			if(bidUsuario)
			{
			m_usuario_single.setM_idUsuario(Integer.parseInt(new String(ch,start,length)));
			bidUsuario=false;
			}
		}
		
		super.characters(ch, start, length);
	}
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		 if(qName.equalsIgnoreCase("usuario"))
		 {
			 bUsuario=false;
		 }
		super.endElement(uri, localName, qName);
	}
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		
	    if(qName.equalsIgnoreCase("usuario"))
	    {
	    	bUsuario=true;
	    	m_usuario_single=new UsuariosModel();
	    }
	    else if(qName.equalsIgnoreCase("usrId"))
	    {
	    	if(bUsuario)
	    	{
	    	bidUsuario=true;
	    	}
	    }
	    else if(qName.equalsIgnoreCase("Error"))
	    {
	    	bError=true;
	    }
		super.startElement(uri, localName, qName, attributes);
	}
	  
	  
	  
	  
}
