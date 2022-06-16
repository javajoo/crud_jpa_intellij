package com.example.ex.controller;


import com.example.ex.entity.User;
import com.example.ex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping("/ex/insert")
    public Map<String, Object> insertUser(User user) {
        HashMap<String, Object> map = new HashMap<>();
        Long row = userService.insertUser(user);
        if (row >= 1) {
            map.put("result", "success");
        } else {
            map.put("result", "errorMessage");
            map.put("errorMessage", "추가실패!!!!");
        }
        return map;
    }

    @DeleteMapping("/ex/delete")
    public Map<String, Object> deleteUser(User user) {
        HashMap<String, Object> map = new  HashMap<>();
        userService.deleteUser(user);
        map.put("result","success");
        return map;
    }

    @PutMapping("/ex/update")
    public Map<String, Object> updateUser(User afterData) {
        HashMap<String, Object> map = new HashMap<>();
        Long row = userService.updateUser(afterData);
        if (row >= 1) {
            map.put("result", "success");
        } else {
            map.put("result", "error");
            map.put("errorMessage", "수정실패!!!!");
        }
        return map;
    }
}

