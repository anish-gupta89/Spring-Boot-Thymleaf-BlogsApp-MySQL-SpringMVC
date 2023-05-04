package com.ag.db;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<CommentEntity, Serializable>{

}
