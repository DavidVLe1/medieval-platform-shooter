package learn.platformShooter.domain;

import learn.platformShooter.data.NpcRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class NpcServiceTest {
    @Autowired
    NpcService service;
    @MockBean
    NpcRepository repository;

    @Test
    void shouldFindAll() {
    }

    @Test
    void shouldFindById() {
    }
    @Test
    void shouldNotFindById() {
    }

    @Test
    void shouldAdd() {
    }
    @Test
    void shouldNotAdd() {
    }

    @Test
    void shouldUpdate() {
    }
    @Test
    void shouldNotUpdate() {
    }

    @Test
    void shouldDeleteById() {
    }
    @Test
    void shouldNotDeleteById() {
    }
}