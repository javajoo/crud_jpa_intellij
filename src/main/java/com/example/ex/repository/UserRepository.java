package com.example.ex.repository;

import com.example.ex.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> { // <객체, 기본키타입>
    // null이 올수 있는 값을 감싸는 Wrapper 클래스, 참조하더라도 NullPointerException(npe)가 발생하지 않도록 도와준다.
    Optional<User> findById(Long id);
}
