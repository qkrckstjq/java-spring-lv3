package com.example.backoffice.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass //entity에서 이 추상클래스 상속시 아래 컬럼을 컬럼으로 상속받아서 사용할수있게 해줌
@EntityListeners(AuditingEntityListener.class) //자동으로 시간 넣어주는 애임
public abstract class TimeStamped {

    @CreatedDate
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
}