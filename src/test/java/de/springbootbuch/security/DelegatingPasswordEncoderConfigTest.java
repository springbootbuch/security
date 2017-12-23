package de.springbootbuch.security;

import java.util.Base64;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@RunWith(SpringRunner.class)
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
			assertThat(r.getStatusCode(), is(equalTo(HttpStatus.OK)));
			assertThat(r.getBody(), is(equalTo("Hello, " + tuple[0] + ".")));
		}
	}
}