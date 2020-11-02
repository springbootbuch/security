package de.springbootbuch.security;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = NONE)
@WithMockUser(
	username = "test", 
	roles = {"USER", "ADMIN"}
)
public class GreetingServiceTest {

	@Autowired
	private GreetingService greetingService;

	@Test
	public void secretGreetingShouldBeProtected() {
		assertThatExceptionOfType(AccessDeniedException.class)
			.isThrownBy(() -> greetingService.superAdminGreeting());
	}
	
	@Test
	@WithMockUser(username = "Reader")
	public void filteredGreetingsShouldWork() {
		Assertions.assertThat(
			greetingService.greetings())
			.contains("Hello, Reader.");
	}
	
	@Test
	public void adminGreetingShouldBeAccessible() {
		assertThat(greetingService.adminGreeting()).isEqualTo("Hello, Admin!");
	}
}
