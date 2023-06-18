package com.hamza.blog.service.serviceImpl;

import com.hamza.blog.entities.Post;
import com.hamza.blog.exceptions.ResourceNotFoundException;
import com.hamza.blog.payload.PostDto;
import com.hamza.blog.payload.PostResponse;
import com.hamza.blog.repositories.PostRepository;
import com.hamza.blog.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository,ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = mapToEntity(postDto);
        Post savedPost = postRepository.save(post);

        PostDto postResponse = mapToDto(savedPost);
        return postResponse;
    }

    @Override
    public PostResponse getAllPost(int pageNo, int size) {
        //create Pageable instance
        Pageable page = PageRequest.of(pageNo,size);

        Page<Post> posts = postRepository.findAll(page);
        // Get content from page object
        List<Post> ListOfPost = posts.getContent();
        List<PostDto> content= ListOfPost.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPage(posts.getTotalPages());
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setLast(posts.isLast());

        return postResponse;

    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post","id",id));

        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post","id",id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post updatedPost = postRepository.save(post);

        return mapToDto(updatedPost);
    }

    @Override
    public void deletePostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("post","id",id));
        postRepository.delete(post);
    }

    public PostDto mapToDto (Post post) {
        // mapping using modelMapper

        PostDto postDto = modelMapper.map(post, PostDto.class);

        /*  -- this is mapping using setter and getter method
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());*/
        return postDto;
    }

    public Post mapToEntity (PostDto postDto) {
        Post post = modelMapper.map(postDto, Post.class);

        /* -- this is mapping using setter and getter method
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setDescription(post.getDescription());*/

        return post;
    }
}
