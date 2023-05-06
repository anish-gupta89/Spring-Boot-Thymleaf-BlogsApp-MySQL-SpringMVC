package com.ag.db;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentEnqReposiroty extends JpaRepository<StudentEnquiaryEntity, Serializable>{
	
	List<StudentEnquiaryEntity> findByUserId(UserDetailsEntity userId, Pageable pageable);
	
	List<StudentEnquiaryEntity> findByUserId(UserDetailsEntity userId);
}
