package com.sm.prime.springboot.web.dto;


import com.sm.prime.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // 선언된 모든 필드의 GET 메소드를 생성
@NoArgsConstructor // 선언된 모든 Final 필드가 포함된 생성자를 생성
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
