package learn.platformShooter.domain;

import learn.platformShooter.data.PlayerCharacterRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PlayerCharacterServiceTest {
    @Autowired
    PlayerCharacterService service;
    @MockBean
    PlayerCharacterRepository repository;

    @Test
    void ShouldFindAll() {
    }

    @Test
    void ShouldFindByUserId() {
    }
    @Test
    void ShouldNotFindByUserId() {
    }

    @Test
    void ShouldFindByPlayerId() {
    }
    @Test
    void ShouldNotFindByPlayerId() {
    }

    @Test
    void ShouldAdd() {
    }
    @Test
    void ShouldNotAdd() {
    }

    @Test
    void ShouldUpdate() {
    }
    @Test
    void ShouldNotUpdate() {
    }

    @Test
    void ShouldDeleteById() {
    }
    @Test
    void ShouldNotDeleteById() {
    }
}