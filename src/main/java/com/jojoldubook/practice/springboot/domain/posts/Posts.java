package com.jojoldubook.practice.springboot.domain.posts;

import com.jojoldubook.practice.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // Entity class에는 절대 Setter를 만들어서는 안 된다.
@NoArgsConstructor

// 테이블과 링크될 클래스임을 나타냄
// 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍르오 매칭(e.g. SalesManager.java -> sales_manager table)
@Entity
public class Posts extends BaseTimeEntity {
    @Id // 해당 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙. 스프링부트 2.0에서는 GenerationType.IDENTITY를 붙여야 AUTO_INCREMENT 적용
    private Long id;

    // 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다.
    // 변경이 필요한 옵션이 있을 경우(e.g. VARCHAR 사이즈를 변경하고 싶다거나)
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    // DB로 쿼리를 날리는 어노테이션이 없는데도 update 가능
    // 이게 가능한 이유는 JPA의 영속성 컨텍스트 때문
    // 영속성 컨텍스트란 엔티티를 영구 저장한는 환경. 일종의 논리적 개념이며, JPA의 핵심 내용은 엔티티가
    // 영속성 컨텍스트에 포함되어 있냐, 아니냐로 갈림
    // JPA의 엔티티 매니저가 활성화된 상태로(Spring Data Jpa를 쓴다면 기본 옵션)
    // 트랜젝션 안에서 데이터베이스에서 데이터를 가져오면 이 데이터는 영속성 컨텍스트가 유지된 상태이다.
    // 이 상태에서 해당 데이터의 값을 변경하면 트렌젝션이 끝나는 시점에 해당 테이블에 변경분을 반영
    // 즉, 엔티티 객체의 값만 변경하면 별도로 update 쿼리문을 날릴 필요 X -> dirty checking 개념
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
