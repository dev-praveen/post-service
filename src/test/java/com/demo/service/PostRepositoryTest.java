package com.demo.service;

import com.demo.service.dao.PostRepository;
import com.demo.service.domain.Post;
import com.demo.service.domain.PostComment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PostRepositoryTest {

  @Autowired private PostRepository postRepository;

  @BeforeAll
  static void beforeAll() {}

  @BeforeEach
  void setUp() {

    postRepository.deleteAll();
  }

  @Test
  void shouldSavePostWithComments() {

    final var comment1 = new PostComment();
    comment1.setReview("very good");

    final var comment2 = new PostComment();
    comment1.setReview("nice");

    final var post = new Post();
    post.setTitle("java jpa blog");
    post.setComments(List.of(comment1, comment2));

    final var dbPost = postRepository.save(post);

    assertThat(dbPost.getTitle()).isEqualTo("java jpa blog");
    assertThat(dbPost.getId()).isNotNull();
    assertThat(dbPost.getComments()).isNotNull();

    dbPost.getComments().forEach(postComment -> assertThat(postComment.getId()).isNotNull());
  }
}
