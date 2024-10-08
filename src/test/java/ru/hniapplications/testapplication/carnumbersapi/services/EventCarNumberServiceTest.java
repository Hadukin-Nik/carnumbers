package ru.hniapplications.testapplication.carnumbersapi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.hniapplications.testapplication.carnumbersapi.repositories.CarNumberRepository;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class EventCarNumberServiceTest {
    @Autowired
    private CarNumberStructuredService carNumberStructuredService;
    @MockBean
    private CarNumberRepository carNumberRepository;
    @Test
    public void test_tree_set_load_from_repository() {
        Assertions.assertTrue(carNumberStructuredService.isWarmed());
    }
}
