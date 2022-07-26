package com.sergiotravassos.springmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.sergiotravassos.springmongodb.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	// Query Methods
	List<Post> findByTitleContaining(String text);
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	List<Post> searchTitle(String text);
	
}
