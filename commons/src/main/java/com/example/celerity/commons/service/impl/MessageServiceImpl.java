/**
 * 
 */
package com.example.celerity.commons.service.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.example.celerity.commons.service.MessageService;

/**
 * Implementación del servicio de mensajeria.
 * 
 * @version 1
 * @date 26/04/2019
 * @author Álvaro José Lobatón Restrepo
 *
 */
@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageSource messageSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.coomeva.mimutualasociados.service.MensajeService#obtenerMensaje(java.
	 * lang.String)
	 */
	@Override
	public String obtenerMensaje( String llave) {
		return obtenerMensaje(llave, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.com.coomeva.mimutualasociados.service.MensajeService#obtenerMensaje(java.
	 * lang.String, java.lang.Object[])
	 */
	@Override
	public String obtenerMensaje(String llave, @Nullable Object[] args) {
		return messageSource.getMessage(llave, args, Locale.getDefault());
	}

}
