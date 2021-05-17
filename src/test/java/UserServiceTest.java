import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import com.ps.autocomplete.AutoCompleteApplication;
import com.ps.autocomplete.model.User;
import com.ps.autocomplete.service.UserService;
import java.io.IOException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AutoCompleteApplication.class)
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Autowired
  private ElasticsearchTemplate esTemplate;

  @Before
  public void before() {
    esTemplate.deleteIndex(User.class);
    esTemplate.createIndex(User.class);
    esTemplate.putMapping(User.class);
    esTemplate.refresh(User.class);
  }


  @Test
  public void testFindByCountry() throws IOException {
    userService.saveAll();
    List<User> byCountry = userService.search("Ba");
    assertThat(byCountry.size(), is(0));
  }

}
