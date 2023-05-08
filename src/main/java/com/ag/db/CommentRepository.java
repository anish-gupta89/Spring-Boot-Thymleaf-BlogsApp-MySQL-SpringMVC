package com.ag.db;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<CommentEntity, Serializable> {

	List<CommentEntity> findByPostAndDeletedIsFalse(PostEntity post);

	List<CommentEntity> findByPostInAndDeletedIsFalse(List<PostEntity> postId);

}
