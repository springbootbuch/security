package de.springbootbuch.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Profile("simple-customization")
@Configuration
public class SimpleCustomizationConfig {
	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = 
				new InMemoryUserDetailsManager();
		manager.createUser(
				User.withUsername("leser")
					.password("aendermich")
					.roles("USER", "ADMIN")
					.build());
		return manager;
	}
}
