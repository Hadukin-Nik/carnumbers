package ru.hniapplications.testapplication.carnumbersapi.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hniapplications.testapplication.carnumbersapi.models.CarNumber;
import ru.hniapplications.testapplication.carnumbersapi.services.ICarNumbersService;

@RestController()
@RequestMapping("/number")
@RequiredArgsConstructor
public class NumberController {
    private final ICarNumbersService carNumberService;

    @GetMapping("/next")
    public String next() {
        CarNumber carNumber = carNumberService.next();
        return carNumber.toString();
    }
    @GetMapping("/random")
    public String random() {
        CarNumber carNumber = carNumberService.random();
        return carNumber.toString();
    }
}

