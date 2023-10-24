package learn.platformShooter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.platformShooter.data.WorldStatsRepository;
import learn.platformShooter.models.Leaderboard;
import learn.platformShooter.models.WorldStats;
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
class WorldStatsControllerTest {
    @MockBean
    WorldStatsRepository worldStatsRepository;
    @Autowired
    MockMvc mvc;
    @Test
    void addShouldReturn201() throws  Exception {
        WorldStats leaderboard = new WorldStats (0,3,0,0,0);
        WorldStats expected =new WorldStats (3,3,0,0,0);
        when(worldStatsRepository.add(any())).thenReturn(expected);
        ObjectMapper jsonMapper = new ObjectMapper();
        String userJson = jsonMapper.writeValueAsString (leaderboard);
        String expectedJson =jsonMapper.writeValueAsString (expected);

        var request = post("/api/worldStats")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));
    }
}