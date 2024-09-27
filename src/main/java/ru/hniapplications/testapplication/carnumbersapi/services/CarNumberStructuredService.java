package ru.hniapplications.testapplication.carnumbersapi.services;

import org.springframework.stereotype.Service;
import ru.hniapplications.testapplication.carnumbersapi.models.CarNumber;
import ru.hniapplications.testapplication.carnumbersapi.models.exceptions.ContainsElementException;
import ru.hniapplications.testapplication.carnumbersapi.models.structures.CarNumberServiceElement;

import java.util.List;
import java.util.TreeSet;

@Service
public class CarNumberStructuredService implements ICarNumberStructuredService {
    private TreeSet<CarNumberServiceElement> treeSet;

    private void addCarNumber(CarNumber carNumber) {
        if (containsCarNumber(carNumber)) {
            throw new ContainsElementException();
        }

        CarNumberServiceElement mock = new CarNumberServiceElement(carNumber, CarNumberServiceElement.Type.LR);
        CarNumberServiceElement lower = treeSet.lower(mock);
        CarNumberServiceElement higher = treeSet.higher(mock);


        //if we don't see something near our mock, then we adding new dot
        if (lower == null || higher == null
                || !lower.getValue().isNear(carNumber)
                || !higher.getValue().isNear(carNumber)) {
            treeSet.add(mock);
        }

        //if there something to connect from left side
        if (lower != null && lower.getType() == CarNumberServiceElement.Type.LR && lower.getValue().isNear(carNumber)) {
            lower.setType(CarNumberServiceElement.Type.L);
            mock.setType(CarNumberServiceElement.Type.R);
        } else if (lower != null && lower.getValue().isNear(carNumber)) {
            treeSet.remove(lower);
            mock.setType(CarNumberServiceElement.Type.R);
        }

        //if there something to connect from right side
        if (higher != null && higher.getType() == CarNumberServiceElement.Type.LR && higher.getValue().isNear(carNumber)) {
            higher.setType(CarNumberServiceElement.Type.R);
            mock.setType(CarNumberServiceElement.Type.L);
        } else if (higher != null && higher.getValue().isNear(carNumber)) {
            treeSet.remove(higher);
            mock.setType(CarNumberServiceElement.Type.L);
        }
    }

    public void init(List<CarNumber> carNumbers) {
        treeSet = new TreeSet<>();
        carNumbers.forEach(this::addCarNumber);
    }

    public CarNumber addFirstEntry(CarNumber carNumber) {
        if (containsCarNumber(carNumber)) {
            CarNumberServiceElement mock = new CarNumberServiceElement(carNumber, CarNumberServiceElement.Type.LR);
            CarNumberServiceElement ceilingDot = treeSet.ceiling(mock);

            //If there is something
            if (ceilingDot.getType() == CarNumberServiceElement.Type.LR
                    || ceilingDot.getType() == CarNumberServiceElement.Type.R) {
                CarNumber next = ceilingDot.getValue().next();
                addCarNumber(next);

                return next;
            } else {
                CarNumberServiceElement higherDot = treeSet.higher(mock);

                CarNumber next = higherDot.getValue().next();
                addCarNumber(next);

                return next;
            }
        } else {
            addCarNumber(carNumber);

            return carNumber;
        }
    }

    public boolean containsCarNumber(CarNumber carNumber) {
        CarNumberServiceElement mock = new CarNumberServiceElement(carNumber, CarNumberServiceElement.Type.LR);
        CarNumberServiceElement floor = treeSet.floor(mock);

        if ((floor != null) && (floor.equals(mock) || floor.getType() == CarNumberServiceElement.Type.L)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isWarmed() {
        return treeSet != null;
    }
}
