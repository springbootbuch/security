package de.springbootbuch.security;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

import java.util.Base64;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles({
	"oauth"
})
public class OAuthConfigTest {
	@Autowired
	TestRestTemplate restTemplate;

	@Test
	public void tokenShouldBeGenerated() {
		final HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "Basic " + Base64.getEncoder().encodeToString("springboot:buch".getBytes()));		
		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();

		map.add("grant_type", "password");
		map.add("username", "user");
		map.add("password", "pwd");
		map.add("scope", "all");
				
		final ResponseEntity<OAuth2AccessToken> r = restTemplate
			.exchange("/oauth/token", HttpMethod.POST, new HttpEntity<>(map, headers), OAuth2AccessToken.class);
		assertThat(r.getStatusCode()).isEqualTo(HttpStatus.OK);
		
		final OAuth2AccessToken accessToken = r.getBody();
		assertThat(accessToken.getTokenType()).isEqualTo("bearer");
		assertThat(accessToken.getScope()).contains("all");
	}
}
