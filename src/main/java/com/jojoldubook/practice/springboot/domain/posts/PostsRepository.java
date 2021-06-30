package com.jojoldubook.practice.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// Posts 클래스로 Database를 접근하게 해줄 JpaRepository 상속
// 보통 iatis나 MytBatis 등에서 DAO(Database Access Object)라고 불리는 DB Layer 접근자.
// Jpa에서는 Repository라고 부르며 인터페이스로 생성
// 단순히 인터페이스 생성 후, JpaRepository<Entity class, PK 타입>를 상속하면 기본적인 CRUD 메서도가 자동으로 생성됨.
public interface PostsRepository extends JpaRepository<Posts, Long> {
    // StringDataJpa에서 제공하지 않는 메서드는 이렇게 쿼리로 작성해도 된다.
    // 규모가 있는 프로젝트에서는 데이터 조회는 FK의 조인, 복잡한 조건 등으로 인해 Entity 클래스만으로
    // 처리하기 어려워 조회용 프레임워크를 추가로 사용한다. 대표적으로 querydsl, jooq, MyBatis가 있다.
    // 조회는 위 3가지 프레임워크 중 하나를 통해 조회하고, 등록/수정/삭제 등은 SpringDataJpa를 통해 진행한다.
    // 개인적으로 querydsl을 추천한다.
    @Query("SELECT p FROM Posts as p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
