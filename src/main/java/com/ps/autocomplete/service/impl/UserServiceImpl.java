package com.ps.autocomplete.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.ps.autocomplete.dto.UserData;
import com.ps.autocomplete.model.User;
import com.ps.autocomplete.repository.UserRepository;
import com.ps.autocomplete.service.UserService;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Value("classpath:data/users.json")
  private Resource usersJsonFile;

  public List<User> listAll() {
    return userRepository.findAll();
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public List<User> saveAll() throws IOException {
    if (count() > 0) {
      return listAll();
    }
    ObjectMapper objectMapper = new ObjectMapper();
    CollectionType collectionType = TypeFactory
        .defaultInstance().constructCollectionType(List.class, UserData.class);
    List<UserData> allFakeUsers = objectMapper
        .readValue(this.usersJsonFile.getFile(), collectionType);
    List<User> users = allFakeUsers.stream().map(User::from).collect(Collectors.toList());
    users.forEach(this::save);
    return users;
  }

  @Override
  public void deleteAllUsers() {
    userRepository.deleteAll();
  }

  public long count() {
    return this.userRepository.count();
  }

  public List<User> search(String keywords) {
    MatchQueryBuilder searchByCountries = QueryBuilders.matchQuery("country", keywords);
    return this.userRepository.search(searchByCountries);
  }
}
