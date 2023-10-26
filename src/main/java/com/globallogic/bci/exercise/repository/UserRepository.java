package com.globallogic.bci.exercise.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.globallogic.bci.exercise.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("SELECT u FROM User u LEFT JOIN FETCH u.phones WHERE u.email = ?1")
	Optional<User> findOneByEmail(String email);
}
