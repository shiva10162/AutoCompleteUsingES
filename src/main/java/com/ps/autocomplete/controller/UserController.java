package com.ps.autocomplete.controller;

import com.ps.autocomplete.model.User;
import com.ps.autocomplete.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping(path = "/list")
  public List<User> getAllUsers() {
    return userService.listAll();
  }

  @GetMapping(path = "/search")
  public List<User> searchUsers(@RequestParam String keywords) {
    return userService.search(keywords);
  }

  @GetMapping(path = "/delete")
  public String deleteUsers() {
    userService.deleteAllUsers();
    return "All Users Deleted!";
  }

  @GetMapping(path = "/find")
  public List<User> searchEmployeebyCountry(
      @RequestParam(value="term", required = false, defaultValue="") String query) {

    if (StringUtils.isEmpty(query)) {
      return getAllUsers().stream()
          .limit(15)
          .collect(Collectors.toList());
    } else {
      return searchUsers(query);
    }
  }

}
