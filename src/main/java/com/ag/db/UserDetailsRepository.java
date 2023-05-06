package com.ag.db;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Serializable>{

	public Optional<UserDetailsEntity> findByUserEmailAndPassword(String email, String password);
	
	
	public Optional<UserDetailsEntity> findByUserEmail(String email);
}
