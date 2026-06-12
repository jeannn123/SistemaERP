package com.SistemaERP.dto;

import com.SistemaERP.entity.Empleado;

public record EmpleadoResponse(
		Integer idEmpleado,
		String nombre,
		String apellido,
		String dni,
		String telefono,
		String cargo) {

	public static EmpleadoResponse from(Empleado empleado) {
		return new EmpleadoResponse(
				empleado.getIdEmpleado(),
				empleado.getNombre(),
				empleado.getApellido(),
				empleado.getDni(),
				empleado.getTelefono(),
				empleado.getCargo());
	}
}
