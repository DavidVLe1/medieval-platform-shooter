package learn.platformShooter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.platformShooter.data.NpcRepository;
import learn.platformShooter.models.Npc;
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
class NpcControllerTest {
    @MockBean
    NpcRepository npcRepository;
    @Autowired
    MockMvc mvc;
    @Test
    void addShouldReturn201() throws Exception{
        Npc npc = new Npc (0,"Merchant","Health",5.0);
        Npc expected =  new Npc (3,"Merchant","Health",5.0);
        when(npcRepository.add(any())).thenReturn(expected);
        ObjectMapper jsonMapper = new ObjectMapper();
        String userJson = jsonMapper.writeValueAsString (npc);
        String expectedJson =jsonMapper.writeValueAsString (expected);

        var request = post("/api/npc")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));
    }
}