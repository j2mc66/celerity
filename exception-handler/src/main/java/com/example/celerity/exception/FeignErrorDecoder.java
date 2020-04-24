package com.example.celerity.exception;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {
	
	private static final Log LOGGER = LogFactory.getLog(FeignErrorDecoder.class);
	
	@Override
	public Exception decode(String methodKey, Response response) {
		
		String message = null;
		String description = null;

        try(Reader reader = response.body().asReader(StandardCharsets.UTF_8)) {
            
            String result = CharStreams.toString(reader);
            ObjectMapper mapper = new ObjectMapper();   
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            ErrorResponse exceptionMessage = mapper.readValue(result, ErrorResponse.class);

            message = exceptionMessage.getMessage();
            description = exceptionMessage.getDescription();

        } catch (IOException e) {
        	LOGGER.error(e);
        }
        
        return new ResponseStatusException(HttpStatus.valueOf(response.status()), message, description);
	}

}
