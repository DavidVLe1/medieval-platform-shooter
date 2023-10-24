package learn.platformShooter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.platformShooter.data.LeaderboardRepository;
import learn.platformShooter.data.PlayerCharacterRepository;
import learn.platformShooter.models.Leaderboard;
import learn.platformShooter.models.PlayerCharacter;
import learn.platformShooter.models.User;
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
class PlayerCharacterControllerTest {
    @MockBean
    PlayerCharacterRepository playerCharacterRepository;
    @Autowired
    MockMvc mvc;

    @Test
    void addShouldReturn201() throws Exception{
        PlayerCharacter playerCharacter = new PlayerCharacter (0,4,0,1.0,100.0,100,30,6,5);
        PlayerCharacter expected = new PlayerCharacter (4,4,0,1.0,100.0,100,30,6,5);
        when(playerCharacterRepository.add(any())).thenReturn(expected);
        ObjectMapper jsonMapper = new ObjectMapper();
        String userJson = jsonMapper.writeValueAsString (playerCharacter);
        String expectedJson =jsonMapper.writeValueAsString (expected);

        var request = post("/api/playerCharacter")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));
    }
}