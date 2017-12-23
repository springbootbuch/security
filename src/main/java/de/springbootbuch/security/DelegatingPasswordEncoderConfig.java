package de.springbootbuch.security;

import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Profile("use-different-hashes")
@Configuration
public class DelegatingPasswordEncoderConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories
			.createDelegatingPasswordEncoder();
	}

	public PasswordEncoder customDelegatingEncoder() {
		final String idForEncode = "pbkdf2";		
		final Pbkdf2PasswordEncoder defaultEncoder 
			= new Pbkdf2PasswordEncoder();
		
		final Map<String, PasswordEncoder> encoders 
			= new HashMap<>();
		encoders.put(
			idForEncode, defaultEncoder);
		encoders.put(
			"bcrypt", new BCryptPasswordEncoder());		
		encoders.put(
			"scrypt", new SCryptPasswordEncoder());
		
		final DelegatingPasswordEncoder rv =
			new DelegatingPasswordEncoder(idForEncode, encoders);
		rv.setDefaultPasswordEncoderForMatches(
			defaultEncoder);
		return rv;
	}
	
	public PasswordEncoder fixedPasswordEncoder() {		
		return new StandardPasswordEncoder();
	}
}
