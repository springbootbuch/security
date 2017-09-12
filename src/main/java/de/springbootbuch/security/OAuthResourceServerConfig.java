package de.springbootbuch.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Profile("oauth")
@Configuration
public class OAuthResourceServerConfig {

	@ConditionalOnMissingBean(ResourceServerConfigAlt.class)
	@Configuration
	@EnableResourceServer
	class ResourceServerConfig {
	}

	@Profile("custom-resourceserver")
	@Configuration
	@EnableResourceServer
	static class ResourceServerConfigAlt extends
		ResourceServerConfigurerAdapter {

		@Override
		public void configure(
			final HttpSecurity http) {
			// Ihre Konfiguration hier
		}
	}
}