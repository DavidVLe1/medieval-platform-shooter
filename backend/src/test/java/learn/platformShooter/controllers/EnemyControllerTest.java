package learn.platformShooter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.platformShooter.data.EnemyRepository;
import learn.platformShooter.models.Enemy;
import learn.platformShooter.models.Leaderboard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EnemyControllerTest {
    @MockBean
    EnemyRepository enemyRepository;
    @Autowired
    MockMvc mvc;
    @Test
    void addShouldReturn201() throws  Exception {
        Enemy enemy = new Enemy (0,"Dark Knight","Boss",30.0,100.0,5);
        Enemy expected = new Enemy (4,"Dark Knight","Boss",30.0,100.0,5);
        when(enemyRepository.add(any())).thenReturn(expected);
        ObjectMapper jsonMapper = new ObjectMapper();
        String userJson = jsonMapper.writeValueAsString (enemy);
        String expectedJson =jsonMapper.writeValueAsString (expected);

        var request = post("/api/enemy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));
    }
}