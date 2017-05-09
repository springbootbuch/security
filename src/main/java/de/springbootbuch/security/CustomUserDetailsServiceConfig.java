package de.springbootbuch.security;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Profile("custom-userdetailsservice")
@Configuration
public class CustomUserDetailsServiceConfig {

	@Bean
	public UserDetailsService userDetailsService(
		final UserRepository userRepository
	) {
		return username -> userRepository
			.findOneByLogin(username)
			.map(entity -> new User(
				entity.getLogin(),
				entity.getHashedPassword(), 
				// Ihre Rollen hier
				new ArrayList<>()))
			.orElseThrow(() -> 
				new UsernameNotFoundException(username)
			);
	}
}