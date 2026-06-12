package com.SistemaERP.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EmpleadoRequest(
		@NotBlank @Size(max = 20) String nombre,
		@NotBlank @Size(max = 20) String apellido,
		@NotBlank @Size(max = 15) String dni,
		@NotBlank @Size(max = 15) String telefono,
		@NotBlank @Size(max = 15) String cargo) {
}
