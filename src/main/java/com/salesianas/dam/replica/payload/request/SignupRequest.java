package com.salesianas.dam.replica.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import javax.validation.constraints.*;

/**
 * 
 * Clase del Registro de Usuario
 * 
 * @author rblancar
 *
 */
@Getter
@Setter
public class SignupRequest {
	private String name;

	private String lastName;

	@NotBlank
	@Size(min = 3, max = 20)
	private String username;
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	private Set<String> role;
	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	private String center;

}