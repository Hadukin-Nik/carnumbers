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
    public void add_tod_between_L() {
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
    public void add_tod_between_B() {
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
    public void add_tod_between_R() {
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
    public void add_tod_after() {
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
    public void add_tod_before() {
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
}