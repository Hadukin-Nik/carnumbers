package ru.hniapplications.testapplication.carnumbersapi.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.hniapplications.testapplication.carnumbersapi.models.dtos.CarNumberDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ToString
@Getter

@EqualsAndHashCode
public class CarNumber implements Comparable<CarNumber> {
    private final List<Character> letters;

    private int[] stringCodePart;
    private int integerCodePart;

    private CarNumber() {
        letters = new ArrayList<>();

        letters.addAll(Arrays.asList('А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'));

        Collections.sort(letters);
    }
    private CarNumber(int[] stringCodePart, int integerCodePart) {
        this();
        this.stringCodePart = stringCodePart;
        this.integerCodePart = integerCodePart;
    }
    public CarNumber(String sign, int integerCodePart) {
        this();
        this.stringCodePart = new int[3];
        stringCodePart[0] = letters.indexOf(sign.charAt(0));
        stringCodePart[1] = letters.indexOf(sign.charAt(1));
        stringCodePart[2] = letters.indexOf(sign.charAt(2));

        this.integerCodePart = integerCodePart;
    }

    public CarNumber(CarNumberDTO carNumberDTO) {
        this();
        String sign = carNumberDTO.getStringCode();

        stringCodePart = new int[3];
        stringCodePart[0] = letters.indexOf(sign.charAt(0));
        stringCodePart[1] = letters.indexOf(sign.charAt(4));
        stringCodePart[2] = letters.indexOf(sign.charAt(5));

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.delete(0, stringBuilder.length());

        stringBuilder.append(sign.charAt(1));
        stringBuilder.append(sign.charAt(2));
        stringBuilder.append(sign.charAt(3));
        integerCodePart = Integer.parseInt(stringBuilder.toString());
    }


    @Override
    public int compareTo(CarNumber o) {
        if (stringCodePart.equals(o.stringCodePart)) {
            return Integer.compare(integerCodePart, o.integerCodePart);
        } else {
            for (int i = 0; i < stringCodePart.length; i++) {
                if (stringCodePart[i] > o.stringCodePart[i]) {
                    return 1;
                } else if (stringCodePart[i] < o.stringCodePart[i]) {
                    return -1;
                }
            }
            return 0;
        }
    }

    public CarNumber next() {
        if (integerCodePart == 999) {
            int[] newStringCodePart = Arrays.copyOf(stringCodePart, stringCodePart.length);

            int i = stringCodePart.length - 1;
            newStringCodePart[i] ++;
            for (; i > 0 && newStringCodePart[i] >= letters.size(); i--) {
                newStringCodePart[i] = 0;
                newStringCodePart[i - 1] ++;
            }

            if(newStringCodePart[0] >= letters.size()) {
                newStringCodePart[0] = 0;
            }
            return new CarNumber(newStringCodePart, 0);
        } else {
            return new CarNumber(stringCodePart, integerCodePart + 1);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i : stringCodePart) {
            stringBuilder.append(letters.get(i));
        }

        String ansString = stringBuilder.toString();
        String ansInteger = String.format("%03d", integerCodePart);

        return "" + ansString.charAt(0) + ansInteger + ansString.charAt(1) + ansString.charAt(2) + " 116 RUS";
    }
}
