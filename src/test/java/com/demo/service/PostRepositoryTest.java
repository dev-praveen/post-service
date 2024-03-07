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

  @BeforeEach
  void setUp() {

    postRepository.deleteAll();
  }

  @BeforeAll
  static void beforeAll() {}

  @Test
  void shouldSavePostWithComments() {

    final var comment1 = PostComment.builder().review("very good").build();
    final var comment2 = PostComment.builder().review("nice").build();
    final var post =
        Post.builder().title("java jpa blog").comments(List.of(comment1, comment2)).build();

    final var dbPost = postRepository.save(post);

    assertThat(dbPost.getTitle()).isEqualTo("java jpa blog");
    assertThat(dbPost.getId()).isNotNull();
    assertThat(dbPost.getComments()).isNotNull();

    dbPost.getComments().forEach(postComment -> assertThat(postComment.getId()).isNotNull());
  }
}
