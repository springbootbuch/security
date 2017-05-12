package de.springbootbuch.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Profile("custom-http-security")
@Configuration
public class CustomHttpSecurityConfig
	extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) 
		throws Exception 
	{
		http
			.httpBasic()
			.and()
			.csrf().disable()
			.headers()
				.frameOptions().disable()
				.cacheControl().disable()
			.and()
			.antMatcher("/**")
			.authorizeRequests()
				.antMatchers("/api/admin/**")
				.authenticated()
				.antMatchers(HttpMethod.GET, "/api/**")
				.permitAll();
	}
}