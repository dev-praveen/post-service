package com.demo.service.domain;

import com.demo.service.model.PostDto;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post")
public class Post implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_SEQUENCE")
  @SequenceGenerator(name = "POST_SEQUENCE", sequenceName = "POST_SEQUENCE", allocationSize = 1)
  private Long id;

  private String title;

  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<PostComment> comments = new ArrayList<>();

  public static Post fromModel(PostDto postDto) {

    final Post post = new Post();
    post.setId(postDto.id());
    post.setTitle(postDto.title());
    post.setComments(postDto.comments().stream().map(PostComment::fromModel).toList());
    return post;
  }

  public void setComments(List<PostComment> comments) {
    comments.forEach(postComment -> postComment.setPost(this));
    this.comments = comments;
  }

  public PostDto toPostDto() {

    final var postComments = comments.stream().map(PostComment::toPostCommentDto).toList();
    return new PostDto(id, title, postComments);
  }
}
