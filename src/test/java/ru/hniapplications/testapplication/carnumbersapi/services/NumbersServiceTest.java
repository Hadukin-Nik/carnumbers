package ru.hniapplications.testapplication.carnumbersapi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.hniapplications.testapplication.carnumbersapi.models.CarNumber;
import ru.hniapplications.testapplication.carnumbersapi.models.dtos.CarNumberDTO;
import ru.hniapplications.testapplication.carnumbersapi.repository.NumberRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NumbersServiceTest {
    @MockBean
    private NumberRepository numberRepository;
    @MockBean
    private CarNumberTreeSet carNumberTreeSet;

    @Test
    void get_all_numbers() {
        //Arrange
        List<CarNumber> expectedList = new ArrayList<>();
        expectedList.add(new CarNumber("СВА", 399));

        CarNumberDTO testObject = new CarNumberDTO();
        testObject.setId(0L);
        testObject.setStringCode("С399ВА");

        when(numberRepository.findAll()).thenReturn(Arrays.asList(testObject));
        //Act
        NumbersService numbersService = new NumbersService(numberRepository, carNumberTreeSet);
        List<CarNumber> allNumbers = numbersService.getAllNumbers();
        //Assert
        Assertions.assertEquals(expectedList, allNumbers);
    }

    @Test
    void random_check() {
        //Arrange
        String expectedAns = "С399ВА";
        when(carNumberTreeSet.addFirstEntry(any())).thenReturn(new CarNumber("СВА", 399));
        //Act
        NumbersService numbersService = new NumbersService(numberRepository, carNumberTreeSet);
        //Assert
        Assertions.assertNotNull(numbersService.random());
    }

    @Test
    void next_check() {
        //Arrange
        CarNumber randomMock = new CarNumber("СВА",399);
        CarNumber nextMock = new CarNumber("СВА",400);
        when(carNumberTreeSet.addFirstEntry(any())).thenReturn(randomMock);
        when(carNumberTreeSet.addFirstEntry(nextMock)).thenReturn(nextMock);
        //Act
        NumbersService numbersService = new NumbersService(numberRepository, carNumberTreeSet);
        numbersService.next();
        //Assert
        Assertions.assertEquals(nextMock, numbersService.next());
    }
}