package com.demo.service.model;

import java.util.List;

public record PostDto(Long id, String title, List<PostCommentDto> comments) {}
