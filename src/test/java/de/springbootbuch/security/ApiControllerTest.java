package de.springbootbuch.security;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest
@MockBean({GreetingService.class, UserRepository.class})
public class ApiControllerTest {

	@Autowired
	private MockMvc mvc;
		
	@Test
	public void getGreetingShouldWork() 
		throws Exception 
	{
		mvc.perform(
				get("/api/greeting")
					.with(user("Tester")))
			.andExpect(
				content().string("Hello, Tester.")
			);
	}
}