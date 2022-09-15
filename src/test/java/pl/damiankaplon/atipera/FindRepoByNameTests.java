package pl.damiankaplon.atipera;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc()
class FindRepoByNameTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void createsMockMvc() {
        Assertions.assertNotNull(mockMvc);
    }

    @Test
    void returnsProperJsonAskedForUsersReposWhoExists() throws Exception {
        this.mockMvc.perform(get("/profile/octocat"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("[*]..name").exists())
                .andExpect(jsonPath("[*]..ownerName").exists())
                .andExpect(jsonPath("[*]..['branches']").exists())
                .andExpect(jsonPath("[*]..['branches']..name").exists())
                .andExpect(jsonPath("[*]..['branches']..lastCommitSh").exists());
    }

    @Test
    void returns404WithProperJsonAskedForNotExistingUser() throws Exception {
        this.mockMvc.perform(get("/profile/NotAR31Us34"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.status").value(404));
    }

}
