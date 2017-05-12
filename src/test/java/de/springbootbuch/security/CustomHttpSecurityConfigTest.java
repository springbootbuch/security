package de.springbootbuch.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@RunWith(SpringRunner.class)
@WebMvcTest
@MockBean({GreetingService.class, UserRepository.class})
@ActiveProfiles({"custom-http-security"})
@Import(CustomHttpSecurityConfig.class)
public class CustomHttpSecurityConfigTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void publicApiShouldBeAccessible() throws Exception {
		mvc.perform(
				get("/api/greeting"))
			.andExpect(
				content().string("Hello, Anonymous.")
			);
	}

	@Test
	public void privateApiShouldBeProtected() throws Exception {
		mvc.perform(
				get("/api/admin/user"))
			.andExpect(
				MockMvcResultMatchers.status().isUnauthorized()
			);
	}
}
