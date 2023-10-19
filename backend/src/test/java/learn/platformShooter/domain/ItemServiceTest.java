package learn.platformShooter.domain;

import learn.platformShooter.data.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class ItemServiceTest {
    @Autowired
    ItemService service;
    @MockBean
    ItemRepository repository;

    @Test
    void findAll() {
    }

    @Test
    void shouldFindByType() {
    }
    @Test
    void shouldNotFindByType() {
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
    void shouldNotpdate() {
    }

    @Test
    void shouldDeleteById() {
    }
    @Test
    void shouldNotDeleteById() {
    }
}