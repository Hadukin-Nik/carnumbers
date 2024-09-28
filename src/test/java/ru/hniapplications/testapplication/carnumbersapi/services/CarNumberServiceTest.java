package ru.hniapplications.testapplication.carnumbersapi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.hniapplications.testapplication.carnumbersapi.models.CarNumber;
import ru.hniapplications.testapplication.carnumbersapi.models.entities.CarNumberEntity;
import ru.hniapplications.testapplication.carnumbersapi.repositories.CarNumberRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CarNumberServiceTest {
    @MockBean
    private CarNumberRepository carNumberRepository;
    @MockBean
    private CarNumberStructuredService carNumberStructuredService;

    @Test
    void get_all_numbers() {
        //Arrange
        List<CarNumber> expectedList = new ArrayList<>();
        expectedList.add(new CarNumber("СВА", 399));

        CarNumberEntity testObject = new CarNumberEntity();
        testObject.setId(0L);
        testObject.setStringCode("С399ВА");

        when(carNumberRepository.findAll()).thenReturn(Arrays.asList(testObject));
        //Act
        CarNumberService carNumberService = new CarNumberService(carNumberRepository, carNumberStructuredService);
        List<CarNumber> allNumbers = carNumberService.getAllNumbers();
        //Assert
        Assertions.assertEquals(expectedList, allNumbers);
    }

    @Test
    void random_check() {
        //Arrange
        String expectedAns = "С399ВА";
        when(carNumberStructuredService.addFirstEntry(any())).thenReturn(new CarNumber("СВА", 399));
        //Act
        CarNumberService carNumberService = new CarNumberService(carNumberRepository, carNumberStructuredService);
        //Assert
        Assertions.assertNotNull(carNumberService.random());
    }

    @Test
    void next_check() {
        //Arrange
        CarNumber randomMock = new CarNumber("СВА",399);
        CarNumber nextMock = new CarNumber("СВА",400);
        when(carNumberStructuredService.addFirstEntry(any())).thenReturn(randomMock);
        when(carNumberStructuredService.addFirstEntry(nextMock)).thenReturn(nextMock);
        //Act
        CarNumberService carNumberService = new CarNumberService(carNumberRepository, carNumberStructuredService);
        carNumberService.next();
        //Assert
        Assertions.assertEquals(nextMock, carNumberService.next());
    }
}