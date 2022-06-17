package com.example.ex.service;

import com.example.ex.entity.User;
import com.example.ex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.List;

@Service
@Transactional // DB에 관련되면 @Transactional 해줘야 한다.
@RequiredArgsConstructor // final 필드에 대해 생성자를 만들어주는 lombok 어노테이션
public class UserService {

    private final UserRepository userRepository;

    // 추가 쿼리
    public Long insertUser(User user){
        return userRepository.save(user).getId();
    }

    // 여러건 조회 쿼리
    @Transactional(readOnly = true) // 조회만 할경우에는 readOnly = true 해줘야 한다.
    public List<User> selectUser() {
        return userRepository.findAll();
    }

    // 삭제 쿼리
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    // 단건 조회 쿼리(id)
    @Transactional(readOnly = true) // 조회만 할경우에는 readOnly = true 해줘야 한다.
    public User selectUserById(Long id) {
        // null이 올수 있는 값을 감싸는 Wrapper 클래스, 참조하더라도 NullPointerException(npe)가 발생하지 않도록 도와준다.
        return userRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    // 수정 쿼리
    public Long updateUser(User afterData) {
        // (1) 넘어온 user 에 대한 유효성 검증이 필요하다.
        User beforeUser = userRepository.findById(afterData.getId()).orElseThrow(EntityExistsException::new);

        // (2) 넘어온 데이터를 수정한다.
        User changeUser = beforeUser.changeUserInfo(afterData);
        return changeUser.getId();
    }

    // 여러건 조회 쿼리(loginId)
    @Transactional(readOnly = true) // 조회만 할경우에는 readOnly = true 해줘야 한다.
    public User selectUserByLoginId(String loginId) {
        return userRepository.findByLoginId(loginId);
    }

}
