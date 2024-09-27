package ru.hniapplications.testapplication.carnumbersapi.services;

import ru.hniapplications.testapplication.carnumbersapi.models.CarNumber;

public interface ICarNumbersService {
    CarNumber next();
    CarNumber random();
}
