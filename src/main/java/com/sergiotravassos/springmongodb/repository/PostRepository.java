package com.sergiotravassos.springmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sergiotravassos.springmongodb.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	// Query Methods
	List<Post> findByTitleContaining(String text);
}
