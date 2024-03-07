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
@Builder
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

  public PostDto toPostDto() {

    final var postComments = comments.stream().map(PostComment::toPostCommentDto).toList();
    return new PostDto(id, title, postComments);
  }

  public static Post fromModel(PostDto postDto) {

    return Post.builder()
        .id(postDto.id())
        .title(postDto.title())
        .comments(postDto.comments().stream().map(PostComment::fromModel).toList())
        .build();
  }
}
