package de.springbootbuch.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Part of springbootbuch.de.
 *
 * OAuth-Resourceserver needs an {@link AuthenticationManager}. The default one
 * goes away if you add a custom {@link UserDetailsService}, so that has to be taken 
 * care of.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Profile("oauth")
@Configuration
public class OAuthUserDetailsConfig {
	@Bean
	public UserDetailsService userDetailsService() {
		InMemoryUserDetailsManager manager = 
				new InMemoryUserDetailsManager();
		manager.createUser(
				User.withDefaultPasswordEncoder()
					.username("user")
					.password("pwd")
					.roles("USER")
					.build());
		return manager;
	}
	
	@Bean 
	public AuthenticationManager authenticationManager(
			final UserDetailsService userDetailsService
	) {
		final DaoAuthenticationProvider authenticationProvider 
			= new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		return new ProviderManager(Arrays.asList(authenticationProvider));
	}
}
