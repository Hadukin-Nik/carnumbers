package ru.hniapplications.testapplication.carnumbersapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.hniapplications.testapplication.carnumbersapi.models.CarNumber;
import ru.hniapplications.testapplication.carnumbersapi.models.entities.CarNumberEntity;
import ru.hniapplications.testapplication.carnumbersapi.repositories.NumberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarNumberService implements ICarNumbersService{
    private final NumberRepository numberRepository;
    private final ICarNumberStructuredService carNumberStructuredService;

    private CarNumber lastCarNumber;

    public List<CarNumber> getAllNumbers() {
        return numberRepository.findAll().stream().map(CarNumber::new).toList();
    }

    public CarNumber next() {
        if (lastCarNumber == null) {
            return random();
        }

        lastCarNumber = carNumberStructuredService.addFirstEntry(lastCarNumber.next());

        numberRepository.save(new CarNumberEntity(lastCarNumber.toString()));

        return lastCarNumber;
    }

    public CarNumber random() {
        lastCarNumber = carNumberStructuredService.addFirstEntry(new CarNumber());

        numberRepository.save(new CarNumberEntity(lastCarNumber.toString()));

        return lastCarNumber;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        carNumberStructuredService.init(getAllNumbers());
    }
}
