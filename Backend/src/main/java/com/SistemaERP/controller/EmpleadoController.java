package com.SistemaERP.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.SistemaERP.dto.EmpleadoRequest;
import com.SistemaERP.dto.EmpleadoResponse;
import com.SistemaERP.service.EmpleadoService;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

	private final EmpleadoService service;

	public EmpleadoController(EmpleadoService service) {
		this.service = service;
	}

	// GET /api/empleados: entrega los datos que personas.js muestra en la tabla.
	@GetMapping
	public List<EmpleadoResponse> listar() {
		return service.listar();
	}

	// POST /api/empleados: valida el JSON y lo envia al servicio.
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EmpleadoResponse crear(@Valid @RequestBody EmpleadoRequest request) {
		return service.crear(request);
	}
}
