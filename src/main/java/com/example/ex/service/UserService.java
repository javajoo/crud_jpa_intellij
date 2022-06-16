package com.example.ex.service;

import com.example.ex.entity.User;
import com.example.ex.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long insertUser(User user){
        return userRepository.save(user).getId();
    }

    @Transactional(readOnly = true)
    public List<User> selectUser() {
        return userRepository.findAll();
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Transactional(readOnly = true)
    public User selectUserById(Long id) {
        return userRepository.findById(id).orElseThrow(EntityExistsException::new);
    }

    public Long updateUser(User afterData) {
        // 넘어온 user 에 대한 유효성 검증이 필요합니다. id 제대로 온건가?
        // (1)
        User beforeUser = userRepository.findById(afterData.getId()).orElseThrow(EntityExistsException::new);
        User changeUser = beforeUser.changeUserInfo(afterData);
        return changeUser.getId();
    }

}
