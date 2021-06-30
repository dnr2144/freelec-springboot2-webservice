package com.jojoldubook.practice.springboot.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들(createdDate, modifiedDate)도 칼럼으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class) // BaseTimeEntity 클래스에 Auditing 기능을 포함시킨다
public abstract class BaseTimeEntity { // 모든 Entity의 super class가 되어 Entity들의 createdDate, modifiedDate를 자동으로 관리

    @CreatedDate
    private LocalDateTime createdDate; // Entity가 생성되어 저장될 때 시간이 자동 저장됨

    @LastModifiedDate
    private LocalDateTime modifiedDate; // 조회한 Entity의 값을 변경할 때 시간이 자동 저장됨.
}
