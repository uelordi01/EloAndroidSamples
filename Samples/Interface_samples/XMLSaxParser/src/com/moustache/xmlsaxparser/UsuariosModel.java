package com.moustache.xmlsaxparser;

public class UsuariosModel {

	int m_idUsuario;
	String m_nombreUsuario;
	String m_apellidosUsuario;
	String m_email;
	String m_matricula;
	String m_direccion;
	int m_tipo_vehiculo;
	String m_password;
	int m_rol;
	int m_grupo;
	String m_error;

	public void setM_error(String m_error) {
		this.m_error = m_error;
	}
	public UsuariosModel()
	{
		
	}
	public UsuariosModel(int m_idUsuario, String m_nombreUsuario,
			String m_apellidosUsuario, String m_email, String m_matricula,
			String m_direccion, int m_tipo_vehiculo, String m_password,
			int m_rol, int m_grupo) {
		super();
		this.m_idUsuario = m_idUsuario;
		this.m_nombreUsuario = m_nombreUsuario;
		this.m_apellidosUsuario = m_apellidosUsuario;
		this.m_email = m_email;
		this.m_matricula = m_matricula;
		this.m_direccion = m_direccion;
		this.m_tipo_vehiculo = m_tipo_vehiculo;
		this.m_password = m_password;
		this.m_rol = m_rol;
		this.m_grupo = m_grupo;
	}
	@Override
	public String toString() {
	return "UsuariosModel [m_idUsuario=" + m_idUsuario
			+ ", m_nombreUsuario=" + m_nombreUsuario
			+ ", m_apellidosUsuario=" + m_apellidosUsuario + ", m_email="
			+ m_email + ", m_matricula=" + m_matricula + ", m_direccion="
			+ m_direccion + ", m_tipo_vehiculo=" + m_tipo_vehiculo
			+ ", m_password=" + m_password + ", m_rol=" + m_rol
			+ ", m_grupo=" + m_grupo + "]";
	}
	public int getM_idUsuario() {
		return m_idUsuario;
	}
	public void setM_idUsuario(int m_idUsuario) {
		this.m_idUsuario = m_idUsuario;
	}
	public String getM_nombreUsuario() {
		return m_nombreUsuario;
	}
	public void setM_nombreUsuario(String m_nombreUsuario) {
		this.m_nombreUsuario = m_nombreUsuario;
	}
	public String getM_apellidosUsuario() {
		return m_apellidosUsuario;
	}
	public void setM_apellidosUsuario(String m_apellidosUsuario) {
		this.m_apellidosUsuario = m_apellidosUsuario;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_matricula() {
		return m_matricula;
	}
	public void setM_matricula(String m_matricula) {
		this.m_matricula = m_matricula;
	}
	public String getM_direccion() {
		return m_direccion;
	}
	public void setM_direccion(String m_direccion) {
		this.m_direccion = m_direccion;
	}
	public int getM_tipo_vehiculo() {
		return m_tipo_vehiculo;
	}
	public void setM_tipo_vehiculo(int m_tipo_vehiculo) {
		this.m_tipo_vehiculo = m_tipo_vehiculo;
	}
	public String getM_password() {
		return m_password;
	}
	public void setM_password(String m_password) {
		this.m_password = m_password;
	}
	public int getM_rol() {
		return m_rol;
	}
	public void setM_rol(int m_rol) {
		this.m_rol = m_rol;
	}
	public int getM_grupo() {
		return m_grupo;
	}
	public void setM_grupo(int m_grupo) {
		this.m_grupo = m_grupo;
	}
	public String getM_error() {
		return m_error;
	}

}
