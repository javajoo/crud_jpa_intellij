package com.example.ex.controller;

import com.example.ex.entity.User;
import com.example.ex.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  // 추가 화면
  @RequestMapping("/ex/insert_view")
    public String insertView() {
      return "/insert";
  }

  // 목록 화면
  @RequestMapping("/ex/list_view")
  public String listView(Model model) {
    List<User> userList = userService.selectUser();
    model.addAttribute("userList", userList);
    return "/list";
  }

  // 상세 화면
  @RequestMapping("/ex/detail_view")
  public String detailView(Model model, @RequestParam("id") Long id) {
    User user = userService.selectUserById(id);
    model.addAttribute("user",user);
    return "/detail";
  }

}
