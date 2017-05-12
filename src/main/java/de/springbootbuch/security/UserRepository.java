package de.springbootbuch.security;

import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

/**
 * Part of springbootbuch.de.
 *
 * @author Michael J. Simons
 * @author @rotnroll666
 */
public interface UserRepository extends Repository<UserEntity, Integer> {
	Optional<UserEntity> findOneByLogin(String login);
	
	List<UserEntity> findAll();
}