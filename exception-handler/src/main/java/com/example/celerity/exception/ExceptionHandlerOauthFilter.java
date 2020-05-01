package com.example.celerity.exception;

import java.io.IOException;
import java.net.ConnectException;
import java.time.LocalDateTime;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.filter.RequestContextFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ExceptionHandlerOauthFilter extends RequestContextFilter {

	private static final Log LOGGER = LogFactory.getLog(ExceptionHandlerOauthFilter.class);
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			filterChain.doFilter(request, response);
		} catch (HttpClientErrorException | HttpServerErrorException  e) {			
			LOGGER.error(e);
			if(HttpStatus.NOT_FOUND.value() ==  e.getStatusCode().value()) {
				ErrorResponse errorResponse = ErrorResponse.builder()
						.timestamp(LocalDateTime.now().toString())
						.status(HttpStatus.NOT_FOUND.value())
						.error(HttpStatus.NOT_FOUND.name())
						.path(request.getRequestURI())
						.message("El Servicio de autenticacion no se encuentra disponible")
						.build();
				String errorResponseString = mapper.writeValueAsString(errorResponse);
				response.getWriter().write(errorResponseString);
				response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			} else {
				response.getWriter().write(e.getResponseBodyAsString());
				response.setStatus(response.getStatus());
			}
			
							
			response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
			
		} catch (ConnectException | ResourceAccessException ex) {
			
			ErrorResponse errorResponse = ErrorResponse.builder()
					.timestamp(LocalDateTime.now().toString())
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.error(HttpStatus.INTERNAL_SERVER_ERROR.name())
					.path(request.getRequestURI())
					.message(ex.getMessage())
					.build();
			
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			String errorResponseString = mapper.writeValueAsString(errorResponse);
			response.getWriter().write(errorResponseString);
			response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		} catch (Exception ex1) {
			LOGGER.error(ex1);
			ErrorResponse errorResponse = ErrorResponse.builder()
					.timestamp(LocalDateTime.now().toString())
					.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.error(HttpStatus.INTERNAL_SERVER_ERROR.name())
					.path(request.getRequestURI())
					.message(ex1.getMessage())
					.build();
			
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			String errorResponseString = mapper.writeValueAsString(errorResponse);
			response.getWriter().write(errorResponseString);
			response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		}
	}
}
