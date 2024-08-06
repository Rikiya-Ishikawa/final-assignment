package integrationtest;

import com.final_assignment.FinalAssignmentApplication;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import java.nio.charset.StandardCharsets;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = FinalAssignmentApplication.class)
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRestApiIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void ユーザーが全件取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/users"))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        String expectedResponse = """
                    [
                        {
                            "id": 1,
                            "name": "yamada",
                            "email": "yamada@example.com"
                        },
                        {
                            "id": 2,
                            "name": "kobashi",
                            "email": "kobashi@example.com"
                        },
                        {
                            "id": 3,
                            "name": "akiyama",
                            "email": "akiyama@example.com"
                        },
                        {
                            "id": 4,
                            "name": "misawa",
                            "email": "misawa@example.com"
                        },
                        {
                            "id": 5,
                            "name": "takayama",
                            "email": "takayama@example.com"
                        }
                    ]
                """;

        JSONAssert.assertEquals(expectedResponse, response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 指定したidが取得されること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/users/1"))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        String expectedResponse = """
                    {
                        "id": 1,
                        "name": "yamada",
                        "email": "yamada@example.com"
                    }
                """;

        JSONAssert.assertEquals(expectedResponse, response, JSONCompareMode.STRICT);
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 指定したidが存在しない場合は404を返すこと() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/6"))
            .andExpect(status().isNotFound());
    }

    @Test
    @DataSet(value = "datasets/users.yml")
    @Transactional
    void 新しいレコードが追加されること() throws Exception {
        String wrestler = """
                    {
                        "name": "Luis Mante",
                        "email": "Luis@example.com"
                    }
                """;

        String response = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                .contentType("application/json")
                .content(wrestler))
            .andExpect(MockMvcResultMatchers.status().isCreated())
            .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        String expectedResponse = """
                    {
                        "name": "Luis Mante",
                        "email": "Luis@example.com"
                    }
                """;

        JSONAssert.assertEquals(expectedResponse, response, JSONCompareMode.LENIENT);
    }
}
