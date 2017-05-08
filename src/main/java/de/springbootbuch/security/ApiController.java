package de.springbootbuch.security;

import java.security.Principal;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
@RestController
@RequestMapping("/api")
public class ApiController {
	
	private final GreetingService greetingService;

	public ApiController(GreetingService greetingService) {
		this.greetingService = greetingService;
	}
	
	@GetMapping("/greeting")
	public String getGreeting(
		final Principal principal) 
	{		
		return String.format(
			"Hello, %s.", principal.getName());
	}
	
	@GetMapping("/adminGreeting")
	public String getAdminGreeting() {
		return greetingService
			.adminGreeting();
	}
	
	@GetMapping("/superAdminGreeting")
	public String getSuperAdminGreeting() {
		return greetingService
			.superAdminGreeting();
	}
	
	@GetMapping("/greetings")
	public List<String> getGreetings() {
		return greetingService
			.greetings();
	}
}
