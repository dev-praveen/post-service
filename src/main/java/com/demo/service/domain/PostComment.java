package com.demo.service.domain;

import com.demo.service.model.PostCommentDto;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "post_comment")
public class PostComment implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_COMMENT_SEQUENCE")
  @SequenceGenerator(
      name = "POST_COMMENT_SEQUENCE",
      sequenceName = "POST_COMMENT_SEQUENCE",
      allocationSize = 1)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  private Post post;

  private String review;

  public PostCommentDto toPostCommentDto() {

    return new PostCommentDto(id, review);
  }

  public static PostComment fromModel(PostCommentDto postCommentDto) {

    return PostComment.builder().id(postCommentDto.id()).review(postCommentDto.review()).build();
  }
}
