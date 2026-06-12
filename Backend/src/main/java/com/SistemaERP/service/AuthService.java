package com.SistemaERP.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.SistemaERP.dto.LoginRequest;
import com.SistemaERP.dto.LoginResponse;
import com.SistemaERP.entity.Empleado;
import com.SistemaERP.entity.Usuario;
import com.SistemaERP.repository.UsuarioRepository;

@Service
public class AuthService {

	private final UsuarioRepository usuarioRepository;

	public AuthService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@Transactional(readOnly = true)
	public LoginResponse login(LoginRequest request) {
		Usuario usuario = usuarioRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.UNAUTHORIZED, "Usuario o contrasena incorrectos"));

		// Comparacion temporal. Luego password_hash debe contener un hash BCrypt.
		if (!usuario.isEstado() || !usuario.getPassword().equals(request.getPassword())) {
			throw new ResponseStatusException(
					HttpStatus.UNAUTHORIZED, "Usuario o contrasena incorrectos");
		}

		Empleado empleado = usuario.getEmpleado();
		String nombreCompleto = empleado.getNombre() + " " + empleado.getApellido();

		return new LoginResponse(
				usuario.getIdUsuario(),
				usuario.getUsername(),
				nombreCompleto,
				usuario.getRol().getNombre());
	}
}
