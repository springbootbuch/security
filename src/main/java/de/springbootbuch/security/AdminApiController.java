package de.springbootbuch.security;

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
@RequestMapping("/api/admin")
public class AdminApiController {

	private final UserRepository userRepository;

	public AdminApiController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/user")
	public List<UserEntity> getUser() {
		return this.userRepository.findAll();
	}
}
