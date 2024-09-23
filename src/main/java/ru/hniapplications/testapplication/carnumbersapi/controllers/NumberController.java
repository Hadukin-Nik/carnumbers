package ru.hniapplications.testapplication.carnumbersapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hniapplications.testapplication.carnumbersapi.models.CarNumber;
import ru.hniapplications.testapplication.carnumbersapi.services.NumbersService;

import java.util.List;

@RestController()
@RequestMapping("/number")
@RequiredArgsConstructor
public class NumberController {
    private final NumbersService numbersService;

    @GetMapping("/next")
    public String next() {
        List<CarNumber> allNumbers = numbersService.getAllNumbers();
        String numbers = allNumbers.toString();
        return numbers;
    }
}

