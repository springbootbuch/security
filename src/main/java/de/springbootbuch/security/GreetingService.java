package de.springbootbuch.security;

import java.util.List;
import static java.util.stream.Collectors.toList;
import java.util.stream.Stream;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@Service
public class GreetingService {
	@PreAuthorize("hasRole('ADMIN')")
	public String adminGreeting() {
		return "Hello, Admin!";
	}
	
	@PreAuthorize("hasRole('SUPERADMIN')")
	public String superAdminGreeting() {
		return "Secret greeting";
	}
	
	@PostFilter(
		"filterObject.contains(authentication.name)"
	)
	public List<String> greetings() {
		return Stream.of(
			"Hello, Reader.",
			"Guten Tag, leser."
		).collect(toList());
	}
}