package com.matzer.db.api.security.impl;

import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.security.SecurityContext;
import org.apache.log4j.Logger;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.matzer.db.api.dto.response.Status;
import com.matzer.db.api.dto.response.StatusResponse;
import com.matzer.db.api.error.ErrorCode;
import com.matzer.db.api.security.AuthenticationException;
import com.matzer.db.api.security.IAuthenticationHandler;
import com.matzer.db.api.security.ITokenSecurityHandler;
import com.matzer.db.api.security.RandomCodeGenerator;
import com.matzer.db.api.security.Unsecured;

/**
 * 
 * Security handler for token authorizaton.
 * 
 * @author lukasz.nowak@homersoft.com
 *
 */
public class TokenSecurityHandler extends AbstractPhaseInterceptor<Message> implements ITokenSecurityHandler {

	/**
	 * Log4j logger.
	 */
	private static final Logger LOG = Logger.getLogger(TokenSecurityHandler.class);

	/**
	 * Used to find security token in query param list.
	 */
	private static final String SECURITY_TOKEN_PARAM_NAME = "token";
	
	/**
	 * Default expiration time in minutes.
	 */
	private static final int DEFAULT_EXPIRATION_TIME = 5 * 60;
	
	/**
	 * Token cache.
	 */
	private Cache<String, String> tokenCache;
	
	/**
	 * Handles authentication.
	 */
	private IAuthenticationHandler authenticationHandler;
	
	/**
	 * Indicates if the authorization verification is turned on (default is off).
	 */
	private boolean authorization = false;
	
	
	/**
	 * Default constructor.
	 */
	public TokenSecurityHandler() {
		this(DEFAULT_EXPIRATION_TIME);
	}
	
	/**
	 * Constructor.
	 * 
	 * @param expirationTime	expiration time
	 */
	public TokenSecurityHandler(int expirationTime) {
	    super(Phase.POST_LOGICAL);	       	   
	    	    	   	    
	    initCache(expirationTime);
	    
	    LOG.info("TokenSecurityHandler created.");
	}

	/**
	 * @return the authenticationHandler
	 */
	public final IAuthenticationHandler getAuthenticationHandler() {
		return authenticationHandler;
	}

	/**
	 * @param authenticationHandler the authenticationHandler to set
	 */
	public final void setAuthenticationHandler(IAuthenticationHandler authenticationHandler) {
		this.authenticationHandler = authenticationHandler;
	}
	
	/**
	 * @return the authorization
	 */
	public final boolean isAuthorization() {
		return authorization;
	}

	/**
	 * @param authorization the authorization to set
	 */
	public final void setAuthorization(boolean authorization) {
		this.authorization = authorization;
	}

	/* (non-Javadoc)
	 * @see org.apache.cxf.interceptor.Interceptor#handleMessage(org.apache.cxf.message.Message)
	 */
	@Override
	public final void handleMessage(Message message) throws Fault {
		// get invoked service info / token verification only on non authentication interfaces
		OperationResourceInfo ori = (OperationResourceInfo) message.getExchange().get(OperationResourceInfo.class);
		if (ori != null) {
			Unsecured unsecuredForClass = ori.getAnnotatedMethod().getDeclaringClass().getAnnotation(Unsecured.class);
			Unsecured unsecuredForMethod = ori.getAnnotatedMethod().getAnnotation(Unsecured.class);
			if (unsecuredForClass == null && unsecuredForMethod == null) {
				// find token query parameter
				String token = null;
				MultivaluedMap<String, String> queryParams = JAXRSUtils.getStructuredParams(
						(String) message.get(Message.QUERY_STRING), "&", true, true);
				for (Entry<String, List<String>> entry : queryParams.entrySet()) {
					if (entry.getKey().equals(SECURITY_TOKEN_PARAM_NAME) 
							&& entry.getValue() != null && entry.getValue().size() == 1) {
						token = entry.getValue().get(0);
						break;
					}
				}
						
				try {
					// verify token if found in query parameter list and create security context
					if (token != null) {
						String login = getLoginAndRefresh(token);
						SecurityContext securityContext = authenticationHandler.createSecurityContext(login);
						message.put(SecurityContext.class, securityContext);
						
						if (authorization && !isAuthorized(ori, securityContext)) {
							throw new AuthenticationException(ErrorCode.FORBIDDEN);
			        	}
					} else {						
						throw new AuthenticationException(ErrorCode.MISSING_AUTHENTICATION_TOKEN);
					}
				} catch (AuthenticationException e) {
					// not throwing the exception in interceptor context, but passing as response class
					Response response = Response.status(Response.Status.OK).entity(
							new StatusResponse(new Status(e.getErrorCode(), e.getMessage()))).build();
					message.getExchange().put(Response.class, response);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see com.homersoft.wh.db.api.security.ITokenSecurityHandler#getToken(java.lang.String, java.lang.String)
	 */
	@Override
	public final String getToken(String login, String password) {
		if (authenticationHandler != null) {
			authenticationHandler.authenticate(login, password);
		} else {
			throw new AuthenticationException(ErrorCode.MISSING_AUTHENTICATION_HANDLER);
		}
		
		String token = RandomCodeGenerator.getenerateToken();		
		tokenCache.put(token, login);
		
		LOG.debug("Token " + token + " generated for login " + login + " (" + tokenCache.size() + ").");
		
		// cleanup old not used tokens
		tokenCache.cleanUp();
		
		return token;
	}	
	
	/* (non-Javadoc)
	 * @see com.homersoft.wh.db.api.security.ITokenSecurityHandler#validateToken(java.lang.String)
	 */
	@Override
	public final void validateToken(String token) {			
		if (!tokenCache.asMap().containsKey(token)) {
			throw new AuthenticationException(ErrorCode.INVALID_SECURITY_TOKEN);
		}
	}

	/* (non-Javadoc)
	 * @see com.homersoft.wh.db.api.security.ITokenSecurityHandler#getLoginAndRefresh(java.lang.String)
	 */
	@Override
	public final String getLoginAndRefresh(String token) {
		String login = tokenCache.asMap().get(token);
		if (login == null) {
			throw new AuthenticationException(ErrorCode.INVALID_SECURITY_TOKEN);
		} else {
			tokenCache.put(login, token);
			LOG.debug("Token " + token + " refreshed for login " + login + " (" + tokenCache.size() + ").");
		}
		
		return login;
	}
	
	/**
	 * Initializes cache.
	 * 
	 * @param expirationTime	token expiration time 
	 */
	private final void initCache(long expirationTime) {			   
		tokenCache = CacheBuilder.newBuilder()
				.maximumSize(10000)
				.expireAfterWrite(expirationTime, TimeUnit.SECONDS)	   
				.build();
	}
	
	/**
     * Checks if the interface method is accessible by the user.
     * 
     * @param ori		operation resource info
     * @param context					security context
     * @return							true if authorized
     */
    private boolean isAuthorized(OperationResourceInfo ori, SecurityContext context) {    	
    	boolean authorized = false;    	
    	if (ori != null) {
	    	RolesAllowed classAnnotation = ori.getAnnotatedMethod().getDeclaringClass().getAnnotation(RolesAllowed.class);
	    	authorized = isAuthorized(classAnnotation, context);
	    	
	    	if (!authorized) {
	    		RolesAllowed methodAnnotation = ori.getAnnotatedMethod().getAnnotation(RolesAllowed.class);
	    		authorized = isAuthorized(methodAnnotation, context);
	    	}
	    		    	
		    if (!authorized) {
		    	StringBuilder builder = new StringBuilder();
		    	builder.append("Unauthorized access to ");
		    	builder.append(ori.getClassResourceInfo().getResourceClass().getName());
		    	builder.append(".");
		    	builder.append(ori.getMethodToInvoke().getName());
		    	builder.append(" by ");
		    	builder.append(context.getUserPrincipal());
		    	builder.append(".");
		    	LOG.warn(builder.toString());
		    }
    	} else {
    		StringBuilder builder = new StringBuilder();
	    	builder.append("Missing OperationResourceInfo.");	    
	    	LOG.warn(builder.toString());
    	}
	    
    	return authorized;
    }
    
    /**
     * Checks if user is authorized (based on annotations).
     * 
     * @param rolesAllowed		role annotation
     * @param context			security context
     * @return					true if authorized
     */
    private boolean isAuthorized(RolesAllowed rolesAllowed, SecurityContext context) {
    	boolean authorized = false;    	
    	if (rolesAllowed != null) {
	    	for (String value : rolesAllowed.value()) {
	    		if (context.isUserInRole(value)) {
	    			authorized = true;
	    			break;
	    		}
	    	}
    	}
    	
    	return authorized;
    }
}
