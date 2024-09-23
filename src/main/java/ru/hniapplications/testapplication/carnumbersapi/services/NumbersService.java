package ru.hniapplications.testapplication.carnumbersapi.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.hniapplications.testapplication.carnumbersapi.models.CarNumber;
import ru.hniapplications.testapplication.carnumbersapi.repository.NumberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NumbersService {
    private final NumberRepository numberRepository;

    public List<CarNumber> getAllNumbers() {
        return numberRepository.findAll().stream().map(CarNumber::new).toList();
    }


}
