package com.example.ex.entity;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


@Entity // JPA를 사용해서 DB테이블과 매핑할 클래스는 @Entity를 붙여야만 매핑이 된다.
@Data // getter, setter, toString이 포함되어 있는 lombok 어노테이션
@Table(name="user") // 테이블 매핑
@EntityListeners(AuditingEntityListener.class) // 이벤트가 발생할 때마다 특정 로직을 실행시킨다.
public class User {
    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 시퀀스처럼 자동증가
    private Long id;
    @Column(name = "login_id") // 필드와 컬럼 매핑
    private String loginId;
    private String password;
    private Long birth;
    private String job;

    // 생성시간
    @CreatedDate
    @Column(name = "created_at" , updatable = false, nullable = false)
    private LocalDateTime createdAt;

    // 수정시간
    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // 수정 메서드
    public User changeUserInfo(User user){
        this.loginId = user.getLoginId();
        this.password = user.getPassword();
        this.birth = user.getBirth();
        this.job = user.getJob();
        return this;
    }

}
