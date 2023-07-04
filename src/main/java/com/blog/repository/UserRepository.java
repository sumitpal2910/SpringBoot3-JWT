package com.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.blog.entities.User;

public interface UserRepository  extends JpaRepository<User, Long>{

	public Optional<User> findByEmail(String email);
	
	@Query(value = "select u.* from Users u where email = ? AND id != ? limit 1", nativeQuery = true)
	public Optional<User> findByEmail(String email, Long id);
}
