package de.springbootbuch.security;

import static java.util.Arrays.asList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
			.findOneByName(username)
			.map(entity -> new User(
				entity.getName(),
				entity.getHashedPassword(), 
				asList(new 
					SimpleGrantedAuthority("ROLE_USER"))
				))
			.orElseThrow(() -> 
				new UsernameNotFoundException(username)
			);
	}
}