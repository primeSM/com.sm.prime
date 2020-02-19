package com.sm.prime.springboot.domain.posts;


import com.sm.prime.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity // 테이블과 링크될 클래스
public class Posts extends BaseTimeEntity {

    @Id // 해당 테이블의 Primary key 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Primary key 생성 규칙 GenerationType.IDENTITY 설정해야 auto increment 설정
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 Column 설정( Varchar(255) dmf 500으로 변경하거나 할때 설정)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}

