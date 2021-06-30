package com.jojoldubook.practice.springboot.web.dto;

import com.jojoldubook.practice.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto { // 새로운 book 정보 저장 request
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // Entity class인 Posts에는 클래스의 인스턴스 값들이 언제 어디서 변해야 하는지 코드상으로 구분하기 위해
    // Entity class에는 절대 setter를 만들지 않는다.
    // setter가 없는 상황에서 builder를 이용해 DB 값 변경. p93 참조
    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
