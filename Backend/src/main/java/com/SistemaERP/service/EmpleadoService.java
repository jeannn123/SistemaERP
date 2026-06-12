package com.SistemaERP.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.SistemaERP.dto.EmpleadoRequest;
import com.SistemaERP.dto.EmpleadoResponse;
import com.SistemaERP.entity.Empleado;
import com.SistemaERP.repository.EmpleadoRepository;

@Service
public class EmpleadoService {

	private final EmpleadoRepository repository;

	public EmpleadoService(EmpleadoRepository repository) {
		this.repository = repository;
	}

	// Consulta la tabla y transforma cada entidad en una respuesta para el frontend.
	@Transactional(readOnly = true)
	public List<EmpleadoResponse> listar() {
		return repository.findAll()
				.stream()
				.map(EmpleadoResponse::from)
				.toList();
	}

	// Aplica las reglas del modulo y guarda el empleado mediante el repositorio.
	@Transactional
	public EmpleadoResponse crear(EmpleadoRequest request) {
		String dni = request.dni().trim();
		if (repository.existsByDni(dni)) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un empleado con ese DNI");
		}

		Empleado empleado = new Empleado();
		empleado.setNombre(request.nombre().trim());
		empleado.setApellido(request.apellido().trim());
		empleado.setDni(dni);
		empleado.setTelefono(request.telefono().trim());
		empleado.setCargo(request.cargo().trim());

		return EmpleadoResponse.from(repository.save(empleado));
	}
}
