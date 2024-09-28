package ru.hniapplications.testapplication.carnumbersapi.models.structures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.hniapplications.testapplication.carnumbersapi.models.CarNumber;

import java.util.Arrays;

@AllArgsConstructor
@Getter
@Setter
public class CarNumberServiceElement implements Comparable {
    public enum Type {
        L, R, LR
    }

    private CarNumber value;
    private Type type;


    public int compareTo(Object o) {
        if ((o instanceof CarNumberServiceElement obj)) {
            if(value.getAlphabeticCodePart() == obj.value.getAlphabeticCodePart()) {
                return Integer.compare(value.getIntegerCodePart(), obj.value.getIntegerCodePart());
            } else {
                return Integer.compare(value.getAlphabeticCodePart(), obj.value.getAlphabeticCodePart());
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CarNumberServiceElement o) {
            return this.getValue().equals(o.getValue());
        } else {
            return false;
        }
    }

}
