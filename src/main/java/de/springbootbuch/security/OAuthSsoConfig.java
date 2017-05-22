package de.springbootbuch.security;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Profile("oauth-sso")
@Configuration
@EnableOAuth2Sso
public class OAuthSsoConfig 
	extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(
		HttpSecurity http) throws Exception {
		http
			.antMatcher("/**")
				.authorizeRequests()
			.antMatchers("/", "/login**")
				.permitAll()
			.anyRequest()
				.authenticated()
			.and()
				.logout()
				.logoutSuccessUrl("/")
				.permitAll();
	}
}
