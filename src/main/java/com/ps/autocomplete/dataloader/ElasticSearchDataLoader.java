package com.ps.autocomplete.dataloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.ps.autocomplete.dto.UserData;
import com.ps.autocomplete.model.User;
import com.ps.autocomplete.service.UserService;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ElasticSearchDataLoader implements CommandLineRunner {

  @Value("classpath:data/users.json")
  private Resource usersJsonFile;

  @Autowired
  private UserService userService;

  @Override
  public void run(String... args) throws Exception {
    if (this.isInitialized()) {
      return;
    }

    List<User> users = this.loadUsersFromFile();
    users.forEach(userService::save);
  }

  private List<User> loadUsersFromFile() throws IOException {
    ObjectMapper objectMapper = new ObjectMapper();
    CollectionType collectionType = TypeFactory.defaultInstance()
        .constructCollectionType(List.class, UserData.class);
    List<UserData> allFakeUsers = objectMapper
        .readValue(this.usersJsonFile.getFile(), collectionType);
    return allFakeUsers.stream().map(User::from).map(this::generateId).collect(Collectors.toList());
  }

  private User generateId(User user) {

    return user;
  }


  private boolean isInitialized() {
    return this.userService.count() > 0;
  }
}
