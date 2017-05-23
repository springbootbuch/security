package de.springbootbuch.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Profile("oauth")
@Configuration
public class OAuthAuthorizationServerConfig {

	@ConditionalOnMissingBean(AuthorizationServerConfigAlt.class)
	@Configuration
	@EnableAuthorizationServer
	class AuthorizationServerConfig {
	}

	@Profile("custom-clients1")
	@Configuration
	@EnableAuthorizationServer
	class AuthorizationServerConfigAlt extends 
		AuthorizationServerConfigurerAdapter {

		private final AuthenticationManager authenticationManager;

		public AuthorizationServerConfigAlt(AuthenticationManager authenticationManager) {
			this.authenticationManager = authenticationManager;
		}
		
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints
				.authenticationManager(this.authenticationManager);
		}

		@Override
		public void configure(
			ClientDetailsServiceConfigurer clients
		) throws Exception {
			clients.inMemory()
				.withClient("springboot")
					.authorizedGrantTypes("password")
					.secret("buch")
					.scopes("all")
				.and()
				.withClient("leser")
					.authorizedGrantTypes("password")
					.secret("leser")
					.scopes("read");
		}
	}
}
