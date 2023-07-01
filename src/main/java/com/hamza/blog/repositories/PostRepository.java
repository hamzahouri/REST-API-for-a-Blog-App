package com.hamza.blog.repositories;

import com.hamza.blog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    // Retrieve Post by Category ID
    List<Post> findByCategoryId (Long categoryId);
}

