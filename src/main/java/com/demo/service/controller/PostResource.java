package com.demo.service.controller;

import com.demo.service.dao.PostRepository;
import com.demo.service.domain.Post;
import com.demo.service.model.PostDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ap/v1")
public class PostResource {

  private final PostRepository postRepository;

  @PostMapping("/post")
  public ResponseEntity<Void> createPost(@RequestBody PostDto postDto) {

    postRepository.save(Post.fromModel(postDto));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @GetMapping("/posts")
  public ResponseEntity<List<PostDto>> getPosts() {

    final var posts = postRepository.findAll().stream().map(Post::toPostDto).toList();
    return ResponseEntity.ok(posts);
  }
}
