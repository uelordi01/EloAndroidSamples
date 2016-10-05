package com.example.helppopup;

import com.example.helppopup.SocialManager.SM_PACKAGES;



public class PopUp_List_Pannel {
	private int idImagen; 
	private SM_PACKAGES m_app_name;

	public PopUp_List_Pannel  (int idImagen,SM_PACKAGES appName) { 
	    this.idImagen = idImagen; 
	    this.m_app_name=appName;
	}

	public int get_idImagen() {
	    return idImagen; 
	} 
	public SM_PACKAGES getApplicationName()
	{
		return this.m_app_name;
	}

}
