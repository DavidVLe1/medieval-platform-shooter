package learn.platformShooter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.platformShooter.data.GameEventsRepository;
import learn.platformShooter.models.GameEvents;
import learn.platformShooter.models.PlayerCharacter;
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
class GameEventsControllerTest {
    @MockBean
    GameEventsRepository gameEventsRepository;
    @Autowired
    MockMvc mvc;
    @Test
    void addShouldReturn201() throws Exception{
        GameEvents gameEvents = new GameEvents (0,3,0,false,false);
        GameEvents expected = new GameEvents (3,3,0,false,false);
        when(gameEventsRepository.add(any())).thenReturn(expected);
        ObjectMapper jsonMapper = new ObjectMapper();
        String userJson = jsonMapper.writeValueAsString (gameEvents);
        String expectedJson =jsonMapper.writeValueAsString (expected);

        var request = post("/api/gameEvents")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));
    }
}