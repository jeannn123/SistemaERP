package com.SistemaERP.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Integer idUsuario;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(name = "password_hash", nullable = false)
	private String password;

	@Column(nullable = false)
	private boolean estado;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_rol", nullable = false)
	private Rol rol;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_empleado", nullable = false)
	private Empleado empleado;

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public boolean isEstado() {
		return estado;
	}

	public Rol getRol() {
		return rol;
	}

	public Empleado getEmpleado() {
		return empleado;
	}
}
