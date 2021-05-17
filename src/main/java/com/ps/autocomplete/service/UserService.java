package com.ps.autocomplete.service;

import com.ps.autocomplete.model.User;
import java.io.IOException;
import java.util.List;


public interface UserService {

  List<User> listAll();

  User save(User user);

  List<User> saveAll() throws IOException;

  void deleteAllUsers();

  long count();

  List<User> search(String keywords);
}
