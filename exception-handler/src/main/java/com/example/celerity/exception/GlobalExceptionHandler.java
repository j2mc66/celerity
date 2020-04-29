package com.example.celerity.exception;

import java.net.ConnectException;
import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.ResourceAccessException;

import com.netflix.hystrix.exception.HystrixRuntimeException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final String BASE_DATOS_JDBC_CONNECTION_EXCEPTION_MENSAJE = "BaseDatos.jDBCConnectionExceptionMensaje";
	private static final String BASE_DATOS_SQL_EXCEPTION_MENSAJE = "BaseDatos.sqlExceptionMensaje";
	private static final String GENERAL_DATA_DUPLICADA = "BaseDatos.dataDuplicada";
	private static final String GENERAL_INTERNAL_SERVER_ERROR_MENSAJE = "General.internalServerErrorMensaje";
	private static final String GENERAL_HTTP_STATUS_0_MENSAJE = "General.httpStatus0Mensaje";
	private static final String SECURITY_LOCKED_EXCEPTION_MENSAJE = "Security.lockedExceptionMensaje";
	private static final String SECURITY_UNAUTHORIZED_EXCEPTION_MENSAJE = "Security.unauthorizedExceptionMensaje";
	private static final String SECURITY_ACCESS_DENIED_EXCEPTION_MENSAJE = "Security.foribiddenExceptionMensaje";
	
	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Metodo que permite tomar excepcion de argumentos no validos
	 * 
	 * @param e MethodArgumentNotValidException
	 * @return @ResponseBody ErrorResponse
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public @ResponseBody ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
			HttpServletRequest request) {
		
		String mensaje = e.getBindingResult().getFieldErrors().stream()
				.map( error -> error.getField() + StringUtils.SPACE + error.getDefaultMessage())
				.collect(Collectors.joining("|"));
		
		return ErrorResponse.builder()
				.timestamp(String.valueOf(System.currentTimeMillis()))
				.status(HttpStatus.BAD_REQUEST.value())
				.error(HttpStatus.BAD_REQUEST.name())
				.path(request.getRequestURI())
				.message(mensaje)
				.build();
	}

	/**
	 * Metodo que captura excepciones de conexion a base de datos.
	 * 
	 * @param e JDBCConnectionException
	 * @return @ResponseBody ErrorResponse
	 */
	@ExceptionHandler(JDBCConnectionException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse habdleJdbcConnectionException(JDBCConnectionException e,
			HttpServletRequest request) {
		
		String mensaje = messageSource.getMessage(BASE_DATOS_JDBC_CONNECTION_EXCEPTION_MENSAJE, null, Locale.getDefault());
		
		return ErrorResponse.builder()
				.timestamp(String.valueOf(System.currentTimeMillis()))
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.path(request.getRequestURI())
				.message(mensaje)
				.build();
	}

	/**
	 * Metodo que captura excepciones de conexiones HTTP.
	 * 
	 * @param e ConnectException
	 * @return @ResponseBody ErrorResponse
	 */
	@ExceptionHandler(ConnectException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse handleConnectException(ConnectException e, HttpServletRequest request) {
		
		String mensaje = messageSource.getMessage(GENERAL_INTERNAL_SERVER_ERROR_MENSAJE, null, Locale.getDefault());

		return ErrorResponse.builder()
				.timestamp(String.valueOf(System.currentTimeMillis()))
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.path(request.getRequestURI())
				.message(mensaje)
				.build();
	}

	/**
	 * Metodo que captura excepciones de más de un registro.
	 * DataIntegrityViolationException.class
	 * 
	 * @param e Exception
	 * @return @ResponseBody ErrorResponse
	 */
	@ExceptionHandler({ DataIntegrityViolationException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse habdleNonUniqueResultException(DataIntegrityViolationException e,
			HttpServletRequest request) {

		String mensaje = messageSource.getMessage(GENERAL_DATA_DUPLICADA, null, Locale.getDefault());

		String description = e.getMostSpecificCause().getMessage() != null ? 
				e.getMostSpecificCause().getMessage() : e.getMessage();

		return ErrorResponse.builder()
				.timestamp(String.valueOf(System.currentTimeMillis()))
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.path(request.getRequestURI())
				.message(mensaje)
				.description(description)
				.build();
	}

	/**
	 * Metodo que captura excepciones de la base de datos. JpaSystemException
	 * 
	 * @param e Exception
	 * @return @ResponseBody ErrorResponse
	 */
	@ExceptionHandler({ JpaSystemException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse handleSqlException(JpaSystemException e, HttpServletRequest request) {
		
		String mensaje = messageSource.getMessage(BASE_DATOS_SQL_EXCEPTION_MENSAJE, null, Locale.getDefault());

		String description = e.getMostSpecificCause().getMessage() != null ? 
				e.getMostSpecificCause().getMessage() : e.getMessage();

		return ErrorResponse.builder()
				.timestamp(String.valueOf(System.currentTimeMillis()))
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.path(request.getRequestURI())
				.message(mensaje)
				.description(description)
				.build();
	}
	
	/**
	 * Metodo que captura excepciones de la base de datos.
	 * JpaObjectRetrievalFailureException
	 * 
	 * @param e Exception
	 * @return @ResponseBody ApiRespuesta
	 */
	@ExceptionHandler({ JpaObjectRetrievalFailureException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse handleJpaObjectRetrievalFailureException(JpaObjectRetrievalFailureException e, HttpServletRequest request) {
		
		String mensaje = messageSource.getMessage(BASE_DATOS_SQL_EXCEPTION_MENSAJE, null, Locale.getDefault());
		
		String description = e.getMostSpecificCause().getMessage() != null ? 
				e.getMostSpecificCause().getMessage() : e.getMessage();
				
		return ErrorResponse.builder()
				.timestamp(String.valueOf(System.currentTimeMillis()))
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.path(request.getRequestURI())
				.message(mensaje)
				.description(description)
				.build();
	}
	
	/**
	 * Metodo que captura excepciones de la base de datos.
	 * JpaObjectRetrievalFailureException
	 * 
	 * @param e Exception
	 * @return @ResponseBody ApiRespuesta
	 */
	 
	@ExceptionHandler({EntityNotFoundException.class})
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse handleEntityNotFoundException(EntityNotFoundException e, HttpServletRequest request) {

		String mensaje = messageSource.getMessage(BASE_DATOS_SQL_EXCEPTION_MENSAJE, null, Locale.getDefault());
		
		return ErrorResponse.builder()
				.timestamp(String.valueOf(System.currentTimeMillis()))
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.path(request.getRequestURI())
				.message(mensaje)
				.description(e.getMessage())
				.build();
	}
	
	/**
	 * Metodo que captura excepciones de la base de datos.
	 * SQLGrammarException
	 * 
	 * @param e Exception
	 * @return @ResponseBody ApiRespuesta
	 */
	@ExceptionHandler({ SQLGrammarException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse handleSqlException(SQLGrammarException e, HttpServletRequest request) {

		String mensaje = messageSource.getMessage(BASE_DATOS_SQL_EXCEPTION_MENSAJE, null, Locale.getDefault());
		
		String description = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
				
		return ErrorResponse.builder()
				.timestamp(String.valueOf(System.currentTimeMillis()))
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.path(request.getRequestURI())
				.message(mensaje)
				.description(description)
				.build();
	}
		
	/**
	 * Metodo que captura excepciones cuando no hay respuesta de parte de algun
	 * microservicio. HystrixRuntimeException.class
	 * 
	 * @param e Exception
	 * @return @ResponseBody ErrorResponse
	 */
	@ExceptionHandler({ HystrixRuntimeException.class })
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse habdleHystrixRuntimeException(HystrixRuntimeException e,
			HttpServletRequest request) {

		String mensaje = messageSource.getMessage(GENERAL_HTTP_STATUS_0_MENSAJE, null, Locale.getDefault());

		String description = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();

		return ErrorResponse.builder()
				.timestamp(String.valueOf(System.currentTimeMillis()))
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.path(request.getRequestURI())
				.message(mensaje)
				.description(description)
				.build();
	}

	/**
	 * Metodo que captura excepciones cuando ocurre un LockedException.
	 * 
	 * @param e Exception
	 * @return @ResponseBody ErrorResponse
	 */
	@ExceptionHandler(LockedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public @ResponseBody ErrorResponse handleLockedException(Exception e, HttpServletRequest request) {
		
		String mensaje = messageSource.getMessage(SECURITY_LOCKED_EXCEPTION_MENSAJE, null, Locale.getDefault());

		return ErrorResponse.builder()
				.timestamp(String.valueOf(System.currentTimeMillis()))
				.status(HttpStatus.FORBIDDEN.value())
				.error(HttpStatus.FORBIDDEN.name())
				.path(request.getRequestURI())
				.message(mensaje)
				.build();
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	@ResponseStatus(HttpStatus.FORBIDDEN)
	public @ResponseBody ErrorResponse handleAccessDeniedException(Exception e, HttpServletRequest request) {
		
		String mensaje = messageSource.getMessage(SECURITY_ACCESS_DENIED_EXCEPTION_MENSAJE, null, Locale.getDefault());

		return ErrorResponse.builder()
				.timestamp(String.valueOf(System.currentTimeMillis()))
				.status(HttpStatus.FORBIDDEN.value())
				.error(HttpStatus.FORBIDDEN.name())
				.path(request.getRequestURI())
				.message(mensaje)
				.build();
	}

	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public @ResponseBody ErrorResponse handleBadCredentialsException(Exception e, HttpServletRequest request) {
		
		String mensaje = messageSource.getMessage(SECURITY_UNAUTHORIZED_EXCEPTION_MENSAJE, null, Locale.getDefault());
		
		return ErrorResponse.builder()
				.timestamp(String.valueOf(System.currentTimeMillis()))
				.status(HttpStatus.UNAUTHORIZED.value())
				.error(HttpStatus.UNAUTHORIZED.name())
				.path(request.getRequestURI())
				.message(mensaje)
				.build();
	}
		
	@ExceptionHandler(value = ResponseStatusException.class)
	public ResponseEntity<ErrorResponse> handleException(ResponseStatusException exception, HttpServletRequest request) {
		ErrorResponse errorResponse =  ErrorResponse.builder()
				.timestamp(LocalDateTime.now().toString())
				.status(exception.getStatus().value())
				.error(exception.getStatus().name())
				.path(request.getRequestURI())
				.message(exception.getMessage())
				.description(exception.getDescription())
				.build();
		
		return new ResponseEntity<ErrorResponse>(errorResponse, exception.getStatus());
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse handleException(Exception exception, HttpServletRequest request) {
		return ErrorResponse.builder()
				.timestamp(LocalDateTime.now().toString())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.path(request.getRequestURI())
				.message(exception.getMessage())
				.build();
		
	}
		
	@ExceptionHandler(value = ResourceAccessException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponse handleCustomException(ResourceAccessException exception, HttpServletRequest request) {
		return ErrorResponse.builder()
				.timestamp(LocalDateTime.now().toString())
				.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.error(HttpStatus.INTERNAL_SERVER_ERROR.name())
				.path(request.getRequestURI())
				.message(exception.getMessage())
				.build();
	}
	
}
