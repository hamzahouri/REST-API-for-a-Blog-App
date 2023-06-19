package com.hamza.blog.controller;

import com.hamza.blog.payload.PostDto;
import com.hamza.blog.payload.PostResponse;
import com.hamza.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost (@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    @GetMapping
    public PostResponse getAllPosts(@RequestParam(value = "page", defaultValue = "0", required = false) int page,
                                    @RequestParam(value = "size",defaultValue = "10",required = false) int size){
        return postService.getAllPost(page,size);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getById (@PathVariable long id) {
  return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost (@PathVariable(name = "id") Long id,
                                               @Valid @RequestBody PostDto postDto) {
        PostDto updatedPost = postService.updatePost(postDto,id);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
    }


}
