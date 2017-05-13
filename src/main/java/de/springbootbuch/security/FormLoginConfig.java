package de.springbootbuch.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import static org.springframework.security.config.http.SessionCreationPolicy.IF_REQUIRED;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Profile("form-login")
@Configuration
public class FormLoginConfig 
	extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) 
		throws Exception 
	{
		super.configure(http);
		http
			.formLogin()
			.and()
			.sessionManagement()
				.sessionCreationPolicy(IF_REQUIRED)
				.sessionFixation()
					.newSession();
	}
}