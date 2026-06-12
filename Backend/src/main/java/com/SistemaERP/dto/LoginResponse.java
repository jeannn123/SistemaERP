package com.SistemaERP.dto;

public class LoginResponse {

	private Integer idUsuario;
	private String username;
	private String nombre;
	private String rol;

	public LoginResponse(Integer idUsuario, String username, String nombre, String rol) {
		this.idUsuario = idUsuario;
		this.username = username;
		this.nombre = nombre;
		this.rol = rol;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public String getNombre() {
		return nombre;
	}

	public String getRol() {
		return rol;
	}
}
