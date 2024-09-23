package ru.hniapplications.testapplication.carnumbersapi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class EventNumbersServiceTest {
    @Autowired
    private CarNumberTreeSet carNumberTreeSet;

    @Test
    public void test_tree_set_load_from_repository() {
        Assertions.assertTrue(carNumberTreeSet.isWarmed());
    }
}
