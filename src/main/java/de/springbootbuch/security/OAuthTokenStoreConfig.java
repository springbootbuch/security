package de.springbootbuch.security;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Profile("oauth-tokenstore")
@Configuration
public class OAuthTokenStoreConfig {
	@Bean
	public TokenStore tokenStore(
		final DataSource dataSource
	) {
		return new JdbcTokenStore(dataSource);
	}
}
