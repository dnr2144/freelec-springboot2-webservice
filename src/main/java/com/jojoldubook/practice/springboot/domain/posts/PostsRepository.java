package com.jojoldubook.practice.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// Posts 클래스로 Database를 접근하게 해줄 JpaRepository 상속
// 보통 iatis나 MytBatis 등에서 DAO(Database Access Object)라고 불리는 DB Layer 접근자.
// Jpa에서는 Repository라고 부르며 인터페이스로 생성
// 단순히 인터페이스 생성 후, JpaRepository<Entity class, PK 타입>를 상속하면 기본적인 CRUD 메서도가 자동으로 생성됨.
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
