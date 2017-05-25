package de.springbootbuch.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Profile("oauth-client")
@Configuration
@EnableOAuth2Client
public class OAuthClientConfig {

	@Bean
	public OAuth2RestTemplate oauth2RestTemplate(
		OAuth2ProtectedResourceDetails resourceDetails,
		OAuth2ClientContext clientContext
	) {
		return new OAuth2RestTemplate(
			resourceDetails, clientContext);
	}
}
