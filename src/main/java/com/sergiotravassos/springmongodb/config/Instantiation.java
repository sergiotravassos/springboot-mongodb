package com.sergiotravassos.springmongodb.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.sergiotravassos.springmongodb.domain.Post;
import com.sergiotravassos.springmongodb.domain.User;
import com.sergiotravassos.springmongodb.dto.AuthorDTO;
import com.sergiotravassos.springmongodb.dto.CommentDTO;
import com.sergiotravassos.springmongodb.repository.PostRepository;
import com.sergiotravassos.springmongodb.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("20/03/2022"), "Teste Title 1", "Teste mensagem body 1", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2022"), "Teste Title 2", "Teste mensagem body 2", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Teste comentario 1 no post 1", sdf.parse("21/03/2022"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Teste comentario 2 no post 1", sdf.parse("22/03/2022"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Teste comentario 3 em post 2", sdf.parse("23/03/2022"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
		
	}

}
