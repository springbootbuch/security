package de.springbootbuch.security;


import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.rules.ExpectedException.none;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE)
@WithMockUser(
	username = "test", 
	roles = {"USER", "ADMIN"}
)
public class GreetingServiceTest {

	@Autowired
	private GreetingService greetingService;
	
	@Rule
	public ExpectedException expectedException 
		= none();

	@Test
	public void secretGreetingShouldBeProtected() {
		expectedException
			.expect(AccessDeniedException.class);
		greetingService.superAdminGreeting();
	}
	
	@Test
	@WithMockUser(username = "Reader")
	public void filteredGreetingsShouldWork() {
		assertThat(
			greetingService.greetings(), 
			contains("Hello, Reader."));
	}
	
	@Test
	public void adminGreetingShouldBeAccessible() {
		assertThat(greetingService.adminGreeting(), is(equalTo("Hello, Admin!")));
	}
}