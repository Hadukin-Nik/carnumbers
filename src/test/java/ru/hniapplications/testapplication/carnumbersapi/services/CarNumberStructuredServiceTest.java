package ru.hniapplications.testapplication.carnumbersapi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import ru.hniapplications.testapplication.carnumbersapi.models.CarNumber;

import java.util.List;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CarNumberStructuredServiceTest {
    @Test
    public void happy_path() {
        CarNumberStructuredService set = new CarNumberStructuredService();

        Assertions.assertNotNull(set);
    }
    @Test
    public void add_dot_between_L() {
        //Arrange
        CarNumber carNumberL = new CarNumber("СВВ", 300);
        CarNumber carNumberB = new CarNumber("СВВ", 301);
        CarNumber carNumberR = new CarNumber("СВВ", 302);

        CarNumber expectedCarNumber = new CarNumber("СВВ", 303);
        //Act
        CarNumberStructuredService set = new CarNumberStructuredService();
        set.init(List.of(carNumberL, carNumberB, carNumberR));

        //Assert
        Assertions.assertEquals(expectedCarNumber, set.addFirstEntry(carNumberL));
    }
    @Test
    public void add_dot_between_B() {
        //Arrange
        CarNumber carNumberL = new CarNumber("СВВ", 300);
        CarNumber carNumberB = new CarNumber("СВВ", 301);
        CarNumber carNumberR = new CarNumber("СВВ", 302);

        CarNumber expectedCarNumber = new CarNumber("СВВ", 303);
        //Act
        CarNumberStructuredService set = new CarNumberStructuredService();
        set.init(List.of(carNumberL, carNumberB, carNumberR));

        //Assert
        Assertions.assertEquals(expectedCarNumber, set.addFirstEntry(carNumberB));
    }
    @Test
    public void add_dot_between_R() {
        //Arrange
        CarNumber carNumberL = new CarNumber("СВВ", 300);
        CarNumber carNumberB = new CarNumber("СВВ", 301);
        CarNumber carNumberR = new CarNumber("СВВ", 302);

        CarNumber expectedCarNumber = new CarNumber("СВВ", 303);
        //Act
        CarNumberStructuredService set = new CarNumberStructuredService();
        set.init(List.of(carNumberL, carNumberB, carNumberR));
        //Assert
        Assertions.assertEquals(expectedCarNumber, set.addFirstEntry(carNumberR));
    }

    @Test
    public void add_dot_after() {
        //Arrange
        CarNumber carNumberL = new CarNumber("СВВ", 300);
        CarNumber carNumberB = new CarNumber("СВВ", 301);
        CarNumber carNumberR = new CarNumber("СВВ", 302);

        CarNumber mock = new CarNumber("СВВ", 305);
        //Act
        CarNumberStructuredService set = new CarNumberStructuredService();
        set.init(List.of(carNumberL, carNumberB, carNumberR));

        //Assert
        Assertions.assertEquals(mock, set.addFirstEntry(mock));
    }

    @Test
    public void add_dot_before() {
        //Arrange
        CarNumber carNumberL = new CarNumber("СВВ", 300);
        CarNumber carNumberB = new CarNumber("СВВ", 301);
        CarNumber carNumberR = new CarNumber("СВВ", 302);

        CarNumber mock = new CarNumber("СВВ", 200);
        //Act
        CarNumberStructuredService set = new CarNumberStructuredService();
        set.init(List.of(carNumberL, carNumberB, carNumberR));

        //Assert
        Assertions.assertEquals(mock, set.addFirstEntry(mock));
    }

    @Test
    public void mixed_dot_tests() {
        //Arrange
        CarNumber[] carNumbers = new CarNumber[3];
        carNumbers[0] = new CarNumber("СВВ", 300);
        carNumbers[1] = new CarNumber("СВВ", 301);
        carNumbers[2] = new CarNumber("СВВ", 302);

        CarNumber expectedCarNumber = new CarNumber("СВВ", 303);

        boolean isThereAnError = false;
        for(int i = 0; i < carNumbers.length; i++) {
            for(int j = 0; j < carNumbers.length; j++) {
                for(int k = 0; k < carNumbers.length; k++) {
                    if(k != j && j != i && k != i) {
                        //Act
                        CarNumberStructuredService set = new CarNumberStructuredService();
                        set.init(List.of(carNumbers[0], carNumbers[1], carNumbers[2]));

                        //Assert
                        if(!expectedCarNumber.equals(set.addFirstEntry(carNumbers[i]))) {
                            isThereAnError = true;
                        }
                    }
                }
            }
        }

        Assertions.assertFalse(isThereAnError);
    }
}