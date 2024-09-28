package ru.hniapplications.testapplication.carnumbersapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.hniapplications.testapplication.carnumbersapi.models.CarNumber;
import ru.hniapplications.testapplication.carnumbersapi.models.entities.CarNumberEntity;
import ru.hniapplications.testapplication.carnumbersapi.repositories.CarNumberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarNumberService implements ICarNumbersService {
    private final CarNumberRepository carNumberRepository;
    private final ICarNumberStructuredService carNumberStructuredService;

    public List<CarNumber> getAllNumbers() {
        return carNumberRepository.findAll().stream().map(CarNumber::new).toList();
    }

    public CarNumber getLastCarNumber() {
        return new CarNumber(carNumberRepository.getLastMessage());
    }

    public CarNumber next() {
        CarNumber added = carNumberStructuredService.addFirstEntry(getLastCarNumber().next());

        while(carNumberRepository.findByCarNumber(added.toSave()) != null) {
            added = carNumberStructuredService.addFirstEntry(added.next());
        }

        carNumberRepository.saveAndFlush(new CarNumberEntity(added.toSave()));

        return added;
    }

    public CarNumber random() {
        CarNumber toSave = new CarNumber();
        while(carNumberRepository.findByCarNumber(toSave.toSave()) != null) {
            toSave = new CarNumber();
        }

        CarNumber added = carNumberStructuredService.addFirstEntry(toSave);

        carNumberRepository.saveAndFlush(new CarNumberEntity(added.toSave()));

        return added;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        carNumberStructuredService.init(getAllNumbers());
    }
}
