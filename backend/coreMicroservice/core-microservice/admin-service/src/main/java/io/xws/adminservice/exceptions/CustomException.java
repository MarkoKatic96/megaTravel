package io.xws.adminservice.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
	private final String message = "";
	private final HttpStatus status = null;
}
