package com.jojoldubook.practice.springboot.domain.user;

import com.jojoldubook.practice.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity { // 사용자 정보 담당 클래스

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    // JPA로 데이터베이스로 저장할 때, Enum 값을 어떤 형태로 저장할지를 결정
    // 기본적으로는 int형 숫자로 저장되지만, 숫자로 저장되면 DB로 확인할 때, 그 값이 무슨 코드를 의미하는지 알 수가 없다.
    // 따라서 EnumType.STRING으로 저장되도록 선언한다.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
