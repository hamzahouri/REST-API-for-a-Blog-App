package com.hamza.blog.service;

import com.hamza.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment (long postId, CommentDto commentDto);
    List<CommentDto> getCommentById (long postId);
    CommentDto getCommentById(Long postId,Long CommentId);
    CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest);
    void deleteComment(Long postId, Long commentId);
}
