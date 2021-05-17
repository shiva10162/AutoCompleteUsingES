package com.ps.autocomplete.model;

import com.ps.autocomplete.dto.UserData;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Document(indexName = "users")
@Setting(settingPath = "es-config/elastic-analyzer.json")
@Getter
@Setter
public class User {

  @Id
  private String id;
  private String firstName;
  private String lastName;
  private String fullName;

  @Field(type = FieldType.Text, analyzer = "autocomplete_index", searchAnalyzer = "autocomplete_search")
  private String country;

  public String getFullName() {
    return firstName + " " + lastName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public static User from(UserData userJson) {
    User user = new User();
    user.setId(UUID.randomUUID().toString());
    user.setFirstName(userJson.getName());
    user.setLastName(userJson.getSurname());
    user.setCountry(userJson.getRegion());
    user.setFullName(user.getFirstName() + " " + user.getLastName());
    return user;
  }
}
