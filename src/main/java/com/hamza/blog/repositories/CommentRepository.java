package com.hamza.blog.repositories;

import com.hamza.blog.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findById(long postId);
}
