package ru.hniapplications.testapplication.carnumbersapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.hniapplications.testapplication.carnumbersapi.models.CarNumber;
import ru.hniapplications.testapplication.carnumbersapi.models.dtos.CarNumberDTO;
import ru.hniapplications.testapplication.carnumbersapi.repository.NumberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NumbersService {
    private final NumberRepository numberRepository;
    private final CarNumberTreeSet carNumberTreeSet;

    private CarNumber lastCarNumber;

    public List<CarNumber> getAllNumbers() {
        return numberRepository.findAll().stream().map(CarNumber::new).toList();
    }

    public CarNumber next() {
        if (lastCarNumber == null) {
            return random();
        }

        lastCarNumber = carNumberTreeSet.addFirstEntry(lastCarNumber.next());

        numberRepository.save(new CarNumberDTO(lastCarNumber.toString()));

        return lastCarNumber;
    }

    public CarNumber random() {
        lastCarNumber = carNumberTreeSet.addFirstEntry(new CarNumber());

        numberRepository.save(new CarNumberDTO(lastCarNumber.toString()));

        return lastCarNumber;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        List<CarNumber> carNumbers = getAllNumbers();

        for (CarNumber carNumber : carNumbers) {
            carNumberTreeSet.addCarNumber(carNumber);
        }

        carNumberTreeSet.setWarmedTrue();
    }
}
