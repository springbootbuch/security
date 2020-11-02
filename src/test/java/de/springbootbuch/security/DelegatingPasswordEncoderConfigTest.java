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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles({
	"custom-userdetailsservice",
	"use-different-hashes"
})
public class DelegatingPasswordEncoderConfigTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	public void userDetailsShouldWork() {
		final String[][] credentials = new String[][] {
			{ "Michael", "bcrypted" },
			{ "Insecure", "Yes,really" }, 
			{ "Klaus ohne Salz", "Also insecure" },
			{ "Klaus mit Salz", "Also insecure" },
			{ "Rainer", "Very secure" }
		};
		for(String[] tuple : credentials) {
			final HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Basic " + Base64.getEncoder().encodeToString((tuple[0] + ":" + tuple[1]).getBytes()));

			final ResponseEntity<String> r = restTemplate
				.exchange("/api/greeting", HttpMethod.GET, new HttpEntity<>(headers), String.class);
			assertThat(r.getStatusCode()).isEqualTo(HttpStatus.OK);
			assertThat(r.getBody()).isEqualTo("Hello, " + tuple[0] + ".");
		}
	}
}
