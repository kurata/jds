package br.com.aqueteron.jds.core;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Domain with key was not found.")
public class JdsDomainNotFoundException extends RuntimeException {
}
