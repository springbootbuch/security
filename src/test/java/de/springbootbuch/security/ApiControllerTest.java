package de.springbootbuch.security;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
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
@RunWith(SpringRunner.class)
@WebMvcTest
@MockBean(GreetingService.class)
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