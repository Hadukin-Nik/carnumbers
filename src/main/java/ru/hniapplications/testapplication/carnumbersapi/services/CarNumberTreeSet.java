package ru.hniapplications.testapplication.carnumbersapi.services;

import org.springframework.stereotype.Service;
import ru.hniapplications.testapplication.carnumbersapi.models.CarNumber;
import ru.hniapplications.testapplication.carnumbersapi.models.exceptions.ContainsElementException;
import ru.hniapplications.testapplication.carnumbersapi.models.structures.CarNumberTreeSetElement;

import java.util.TreeSet;

@Service
public class CarNumberTreeSet {
    private TreeSet<CarNumberTreeSetElement> treeSet;
    private boolean isWarmed;

    public CarNumberTreeSet() {
        treeSet = new TreeSet<>();
        isWarmed = false;
    }

    public void addCarNumber(CarNumber carNumber) {
        if (containsCarNumber(carNumber)) {
            throw new ContainsElementException();
        }

        CarNumberTreeSetElement mock = new CarNumberTreeSetElement(carNumber, CarNumberTreeSetElement.Type.LR);
        CarNumberTreeSetElement lower = treeSet.lower(mock);
        CarNumberTreeSetElement higher = treeSet.higher(mock);


        //if we don't see something near our mock, then we adding new tod
        if (lower == null || higher == null
                || !lower.getValue().isNear(carNumber)
                || !higher.getValue().isNear(carNumber)) {
            treeSet.add(mock);
        }

        //if there something to connect from left side
        if (lower != null && lower.getType() == CarNumberTreeSetElement.Type.LR && lower.getValue().isNear(carNumber)) {
            lower.setType(CarNumberTreeSetElement.Type.L);
            mock.setType(CarNumberTreeSetElement.Type.R);
        } else if (lower != null && lower.getValue().isNear(carNumber)) {
            treeSet.remove(lower);
            mock.setType(CarNumberTreeSetElement.Type.R);
        }

        //if there something to connect from right side
        if (higher != null && higher.getType() == CarNumberTreeSetElement.Type.LR && higher.getValue().isNear(carNumber)) {
            higher.setType(CarNumberTreeSetElement.Type.R);
            mock.setType(CarNumberTreeSetElement.Type.L);
        } else if (higher != null && higher.getValue().isNear(carNumber)) {
            treeSet.remove(higher);
            mock.setType(CarNumberTreeSetElement.Type.L);
        }
    }

    public CarNumber addFirstEntry(CarNumber carNumber) {
        if (containsCarNumber(carNumber)) {
            CarNumberTreeSetElement mock = new CarNumberTreeSetElement(carNumber, CarNumberTreeSetElement.Type.LR);
            CarNumberTreeSetElement ceilingDot = treeSet.ceiling(mock);

            if (ceilingDot.getType() == CarNumberTreeSetElement.Type.LR
                    || ceilingDot.getType() == CarNumberTreeSetElement.Type.R) {
                CarNumber next = ceilingDot.getValue().next();
                addCarNumber(next);

                return next;
            } else {
                CarNumberTreeSetElement higherDot = treeSet.higher(mock);

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
        CarNumberTreeSetElement mock = new CarNumberTreeSetElement(carNumber, CarNumberTreeSetElement.Type.LR);
        CarNumberTreeSetElement floor = treeSet.floor(mock);

        if ((floor != null) && (floor.equals(mock) || floor.getType() == CarNumberTreeSetElement.Type.L)) {
            return true;
        } else {
            return false;
        }
    }

    public void setWarmedTrue() {
        isWarmed = true;
    }

    public boolean isWarmed() {
        return isWarmed;
    }
}
