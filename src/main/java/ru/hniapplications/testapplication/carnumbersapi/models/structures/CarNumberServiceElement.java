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
            int[] stringCodePart = value.getStringCodePart();
            int integerCodePart = value.getIntegerCodePart();

            int[] oStringCodePart = obj.getValue().getStringCodePart();
            int oIntegerCodePart = obj.getValue().getIntegerCodePart();

            if (Arrays.compare(stringCodePart, oStringCodePart) == 0) {
                return Integer.compare(integerCodePart, oIntegerCodePart);
            } else {
                for (int i = 0; i < stringCodePart.length; i++) {
                    if (stringCodePart[i] > oStringCodePart[i]) {
                        return 1;
                    } else if (stringCodePart[i] < oStringCodePart[i]) {
                        return -1;
                    }
                }
                return 0;
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
