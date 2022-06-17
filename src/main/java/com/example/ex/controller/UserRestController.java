package com.example.ex.controller;


import com.example.ex.entity.User;
import com.example.ex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor // final 필드에 대해 생성자를 만들어주는 lombok 어노테이션
public class UserRestController {

    private final UserService userService;

    /**
     * 추가 API
     * @param user
     * @return
     */
    @PostMapping("/ex/insert")
    public Map<String, Object> insertUser(User user) {
        HashMap<String, Object> map = new HashMap<>();
        Long row = userService.insertUser(user);
        map.put("result", "success");
        if (row < 1) {
            map.put("result", "errorMessage");
            map.put("errorMessage", "추가실패!!!!");
        }
        return map;
    }

    /**
     * 삭제 API
     * @param user
     * @return
     */
    @DeleteMapping("/ex/delete")
    public Map<String, Object> deleteUser(User user) {
        HashMap<String, Object> map = new  HashMap<>();
        userService.deleteUser(user);
        map.put("result","success");
        return map;
    }

    /**
     * 수정 API
     * @param afterData
     * @return
     */
    @PutMapping("/ex/update")
    public Map<String, Object> updateUser(User afterData) {
        HashMap<String, Object> map = new HashMap<>();
        Long row = userService.updateUser(afterData);
        map.put("result", "success");
        if (row < 1) {
            map.put("result", "error");
            map.put("errorMessage", "수정실패!!!!");
        }
        return map;
    }

    @GetMapping("/ex/search/{loginId}")
    // @PathVariable을 통해 설정한 변수를 요청메소드의 파라미터로 사용할 수 있다.(파라미터를 하나만 받을 수 있다.)
    public Map<String, Object> search(@PathVariable("loginId") String loginId) {
        Map<String, Object> map = new HashMap<>();
        List<User> userList = new ArrayList<>();
        userList.add(userService.selectUserByLoginId(loginId));

        map.put("result", "success");
        map.put("userList", userList);

        if (userList.size() < 1) {
            map.put("result", "error");
        }
        return map;
    }
}