package ru.hniapplications.testapplication.carnumbersapi.services;

import ru.hniapplications.testapplication.carnumbersapi.models.CarNumber;

import java.util.List;

public interface ICarNumberStructuredService {
    void init(List<CarNumber> carNumbers);
    CarNumber addFirstEntry(CarNumber carNumber);

    boolean isWarmed();
}
