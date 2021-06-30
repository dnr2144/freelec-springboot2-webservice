package com.jojoldubook.practice.springboot.web.dto;

import com.jojoldubook.practice.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto { // book info search request

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
