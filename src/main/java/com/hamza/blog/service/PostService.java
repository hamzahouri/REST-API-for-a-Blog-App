package com.hamza.blog.service;

import com.hamza.blog.payload.PostDto;
import com.hamza.blog.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost (PostDto postDto);
    PostResponse getAllPost (int pageNo, int size);

    PostDto getPostById (long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById (long id);
}
