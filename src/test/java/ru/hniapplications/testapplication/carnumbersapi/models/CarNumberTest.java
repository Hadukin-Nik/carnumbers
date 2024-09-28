package ru.hniapplications.testapplication.carnumbersapi.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CarNumberTest {
    @Test
    void transformation_number() {
        //Arrange
        String expectedAns = "А000АА 116 RUS";
        //Act
        CarNumber carNumber = new CarNumber("ААА", 0);
        String ans = carNumber.toString();
        //Assert
        assertEquals(expectedAns, ans);
    }

    @Test
    void next_alphabetic_case() {
        //Arrange
        String expectedAns = "С000ВВ 116 RUS";
        //Act
        CarNumber carNumber = new CarNumber("СВА", 999);
        String ans = carNumber.next().toString();
        //Assert
        assertEquals(expectedAns, ans);
    }

    @Test
    void next_alphabetic_case_2() {
        //Arrange
        String expectedAns = "Т000АА 116 RUS";
        //Act
        CarNumber carNumber = new CarNumber("СХХ", 999);
        String ans = carNumber.next().toString();
        //Assert
        assertEquals(expectedAns, ans);
    }

    @Test
    void next_alphabetic_case_3() {
        //Arrange
        String expectedAns = "С000ХХ 116 RUS";
        //Act
        CarNumber carNumber = new CarNumber("СХУ", 999);
        String ans = carNumber.next().toString();
        //Assert
        assertEquals(expectedAns, ans);
    }

    @Test
    void next_numeric_case() {
        //Arrange
        String expectedAns = "С400ВА 116 RUS";
        //Act
        CarNumber carNumber = new CarNumber("СВА", 399);
        String ans = carNumber.next().toString();
        //Assert
        assertEquals(expectedAns, ans);
    }

    @Test
    void is_near_case_1() {
        //Arrange
        CarNumber carNumberExpected = new CarNumber("СВА", 400);
        //Act
        CarNumber carNumber = new CarNumber("СВА", 399);
        //Assert
        Assertions.assertTrue(carNumberExpected.isNear(carNumber));
    }

    @Test
    void is_near_case_2() {
        //Arrange
        CarNumber carNumberExpected = new CarNumber("СВВ", 0);
        //Act
        CarNumber carNumber = new CarNumber("СВА", 999);
        //Assert
        Assertions.assertTrue(carNumberExpected.isNear(carNumber));
    }

    @Test
    void is_near_false_case() {
        //Arrange
        CarNumber carNumberExpected = new CarNumber("СВВ", 1);
        //Act
        CarNumber carNumber = new CarNumber("СВА", 999);
        //Assert
        Assertions.assertFalse(carNumberExpected.isNear(carNumber));
    }
}