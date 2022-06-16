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

@Entity
@Data
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "login_id")
    private String loginId;
    private String password;
    private Long birth;
    private String job;

    @CreatedDate
    @Column(name = "created_at" , updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public User changeUserInfo(User user){
        this.loginId = user.loginId;
        this.password = user.getPassword();
        this.birth = user.getBirth();
        this.job = user.getJob();
        return this;
    }

}
