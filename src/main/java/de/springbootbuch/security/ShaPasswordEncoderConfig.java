package de.springbootbuch.security;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Profile("use-sha256-password-hash")
@Configuration
public class ShaPasswordEncoderConfig 
	extends WebSecurityConfigurerAdapter 
{

	/**
	 * Needed to store a salt in a UserDetails impl
	 */
	static class UserWithSalt implements UserDetails {

		private static final long serialVersionUID = 3909944840262397676L;

		private final String username;
		
		private final String password;
		
		private final String salt;

		public UserWithSalt(String username, String password, String salt) {
			this.username = username;
			this.password = password;
			this.salt = salt;
		}

		@Override
		public String getUsername() {
			return username;
		}

		@Override
		public String getPassword() {
			return password;
		}

		public String getSalt() {
			return salt;
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			return new ArrayList<>();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
	}
	
	private final UserRepository userRepository;

	public ShaPasswordEncoderConfig(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Bean
	public UserDetailsService userDetailsService(
		final UserRepository userRepository
	) {
		return username -> userRepository
			.findOneByLogin(username)
			.map(entity -> new UserWithSalt(
				entity.getLogin(),
				entity.getHashedPassword(), 
				entity.getSalt()))
			.orElseThrow(() -> 
				new UsernameNotFoundException(username)
			);
	}

	@Override
	public void configure(
		AuthenticationManagerBuilder auth
	) {
		DaoAuthenticationProvider authProvider
			= new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(
			userDetailsService(userRepository));
		authProvider.setPasswordEncoder(
			new ShaPasswordEncoder(256));
		// Optional, falls Ihre Hashes ein 
		// individuelles Salt enthalten
		authProvider.setSaltSource(
			user -> ((UserWithSalt)user).getSalt());
		
		auth.authenticationProvider(authProvider);
	}
}