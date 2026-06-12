package com.SistemaERP.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SistemaERP.entity.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

	boolean existsByDni(String dni);
}
