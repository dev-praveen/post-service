package com.demo.service.domain;

import com.demo.service.model.PostCommentDto;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Setter
@Getter
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

  public static PostComment fromModel(PostCommentDto postCommentDto) {

    final PostComment comment = new PostComment();
    comment.setId(postCommentDto.id());
    comment.setReview(postCommentDto.review());
    return comment;
  }

  public PostCommentDto toPostCommentDto() {

    return new PostCommentDto(id, review);
  }
}
