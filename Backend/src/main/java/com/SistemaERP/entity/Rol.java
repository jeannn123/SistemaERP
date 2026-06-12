package com.SistemaERP.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "rol")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rol")
	private Integer idRol;

	@Column(nullable = false, unique = true, length = 15)
	private String nombre;

	public Integer getIdRol() {
		return idRol;
	}

	public String getNombre() {
		return nombre;
	}
}
