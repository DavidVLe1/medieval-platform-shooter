package learn.platformShooter.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import learn.platformShooter.data.ItemRepository;
import learn.platformShooter.models.Item;
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
class ItemControllerTest {
    @MockBean
    ItemRepository itemRepository;
    @Autowired
    MockMvc mvc;
    @Test
    void addShouldReturn201() throws Exception{
        Item item = new Item (0,"Shield","A Shield","max_health",20.0);
        Item expected = new Item (5,"Shield","A Shield","max_health",20.0);
        when(itemRepository.add(any())).thenReturn(expected);
        ObjectMapper jsonMapper = new ObjectMapper();
        String userJson = jsonMapper.writeValueAsString (item);
        String expectedJson =jsonMapper.writeValueAsString (expected);

        var request = post("/api/item")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userJson);

        mvc.perform(request)
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedJson));
    }
}