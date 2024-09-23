package ru.hniapplications.testapplication.carnumbersapi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.hniapplications.testapplication.carnumbersapi.models.CarNumber;
import ru.hniapplications.testapplication.carnumbersapi.models.dtos.CarNumberDTO;
import ru.hniapplications.testapplication.carnumbersapi.repository.NumberRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class NumbersServiceTest {
    @MockBean
    private NumberRepository numberRepository;

    @BeforeEach
    void setUp() {

        CarNumberDTO testObject = new CarNumberDTO();
        testObject.setId(0L);
        testObject.setStringCode("С399ВА");
        when(numberRepository.findAll()).thenReturn(Arrays.asList(testObject));
    }

    @Test
    void getAllNumbers() {
        //Arrange
        List<CarNumber> expectedList = new ArrayList<>();
        expectedList.add(new CarNumber("СВА", 399));

        //Act
        NumbersService numbersService = new NumbersService(numberRepository);
        List<CarNumber> allNumbers = numbersService.getAllNumbers();
        //Assert
        Assertions.assertEquals(expectedList, allNumbers);

    }
}