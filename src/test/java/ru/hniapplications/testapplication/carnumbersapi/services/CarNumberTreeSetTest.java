package ru.hniapplications.testapplication.carnumbersapi.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import ru.hniapplications.testapplication.carnumbersapi.models.CarNumber;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CarNumberTreeSetTest {
    @Test
    public void happy_path() {
        CarNumberTreeSet set = new CarNumberTreeSet();

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
        CarNumberTreeSet set = new CarNumberTreeSet();
        set.addCarNumber(carNumberL);
        set.addCarNumber(carNumberB);
        set.addCarNumber(carNumberR);
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
        CarNumberTreeSet set = new CarNumberTreeSet();
        set.addCarNumber(carNumberL);
        set.addCarNumber(carNumberB);
        set.addCarNumber(carNumberR);
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
        CarNumberTreeSet set = new CarNumberTreeSet();
        set.addCarNumber(carNumberL);
        set.addCarNumber(carNumberB);
        set.addCarNumber(carNumberR);
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
        CarNumberTreeSet set = new CarNumberTreeSet();
        set.addCarNumber(carNumberL);
        set.addCarNumber(carNumberB);
        set.addCarNumber(carNumberR);
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
        CarNumberTreeSet set = new CarNumberTreeSet();
        set.addCarNumber(carNumberL);
        set.addCarNumber(carNumberB);
        set.addCarNumber(carNumberR);
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
        for(int i = 0; i < carNumbers.length; i++) {
            for(int j = 0; j < carNumbers.length; j++) {
                for(int k = 0; k < carNumbers.length; k++) {
                    if(k != j && j != i && k != i) {
                        //Act
                        CarNumberTreeSet set = new CarNumberTreeSet();
                        set.addCarNumber(carNumbers[0]);
                        set.addCarNumber(carNumbers[1]);
                        set.addCarNumber(carNumbers[2]);
                        //Assert
                        Assertions.assertEquals(expectedCarNumber, set.addFirstEntry(carNumbers[i]));
                    }
                }
            }
        }
    }
}