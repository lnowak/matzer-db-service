package com.matzer.db.api.service.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

import com.matzer.db.api.dto.response.Status;
import com.matzer.db.api.dto.response.StatusResponse;
import com.matzer.db.api.error.ErrorCode;
import com.matzer.db.api.security.AuthenticationException;

/**
 * 
 * Exception mapper.
 * 
 * @author lkawon@gmail.com
 *
 */
@Provider
public class CustomExceptionMapper implements ExceptionMapper<RuntimeException> { 

	/**
	 * Log4j logger.
	 */
	private static final Logger LOG = Logger.getLogger(CustomExceptionMapper.class);
	
	
    /* (non-Javadoc)
     * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
     */
    @Override 
    public final Response toResponse(RuntimeException exception) {
    	LOG.error("Exception occured while processing request.", exception);    	
    	Response response = prepareResponse(ErrorCode.UNKNOWN, null);
    	
    	if (exception instanceof WebApplicationException) {
    		WebApplicationException webApplicationException = (WebApplicationException) exception;
    		if (webApplicationException .getResponse().getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
    			response = prepareResponse(ErrorCode.NOT_FOUND, null);
    		}
    	} else if (exception instanceof ApiException) {
        	ApiException apiException = (ApiException) exception;        	
        	response = prepareResponse(apiException.getErrorCode(), null); 
        } else if (exception instanceof AuthenticationException) {
        	AuthenticationException authenticationException = (AuthenticationException) exception;
        	response = prepareResponse(authenticationException.getErrorCode(), null);
        }
        
        return response;
    }
    
    /**
     * Creates response for exception.
     * 
     * @param errorCode		error code
     * @param message		optional message
     * @return				response object
     */
    private Response prepareResponse(ErrorCode errorCode, String message) {
    	Status status = new Status(errorCode);
    	if (message != null) {
    		status.setMessage(message);
    	}
    	
    	Response response = Response.status(Response.Status.OK).type(MediaType.APPLICATION_JSON_TYPE). 		   
    			entity(new StatusResponse(status)).build(); 
    	
    	return response;
    }
} 
