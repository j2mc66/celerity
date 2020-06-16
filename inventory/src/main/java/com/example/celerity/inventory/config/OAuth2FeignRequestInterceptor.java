package com.example.celerity.inventory.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.util.Assert;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class OAuth2FeignRequestInterceptor implements RequestInterceptor {

	/**
	 * The name of the token.
	 */
	public static final String BEARER = "Bearer";

	/**
	 * The name of the header.
	 */
	public static final String AUTHORIZATION = "Authorization";
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OAuth2FeignRequestInterceptor.class);
	 
    private final OAuth2ClientContext oauth2ClientContext;
	
    /**
     * Creates new instance of {@link OAuth2FeignRequestInterceptor} with client context.
     *
     * @param oauth2ClientContext the OAuth2 client context
     */
    public OAuth2FeignRequestInterceptor(OAuth2ClientContext oauth2ClientContext) {
        Assert.notNull(oauth2ClientContext, "Context can not be null");
        this.oauth2ClientContext = oauth2ClientContext;
    }
    
	@Override
	public void apply(RequestTemplate template) {

		if (template.headers().containsKey(AUTHORIZATION)) {
            LOGGER.warn("The Authorization token has been already set");
        } else if (oauth2ClientContext.getAccessTokenRequest().getExistingToken() == null) {
            LOGGER.warn("Can not obtain existing token for request, if it is a non secured request, ignore.");
        } else {
            LOGGER.debug("Constructing Header {} for Token {}", AUTHORIZATION, BEARER);
            template.header(AUTHORIZATION, String.format("%s %s", BEARER,
                    oauth2ClientContext.getAccessTokenRequest().getExistingToken().toString()));
        }
	}

}
