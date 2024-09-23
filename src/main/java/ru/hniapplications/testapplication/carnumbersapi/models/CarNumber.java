package ru.hniapplications.testapplication.carnumbersapi.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.hniapplications.testapplication.carnumbersapi.models.dtos.CarNumberDTO;

import java.util.*;

@ToString
@Getter
@EqualsAndHashCode
public class CarNumber {
    private final List<Character> letters;

    private int[] stringCodePart;
    private int integerCodePart;

    public CarNumber() {
        letters = new ArrayList<>();

        letters.addAll(Arrays.asList('А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'));

        Collections.sort(letters);

        Random random = new Random();

        integerCodePart = random.nextInt(1000);

        stringCodePart = new int[3];
        stringCodePart[0] = random.nextInt(letters.size());
        stringCodePart[1] = random.nextInt(letters.size());
        stringCodePart[2] = random.nextInt(letters.size());
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


    public CarNumber next() {
        if (integerCodePart == 999) {
            int[] newStringCodePart = Arrays.copyOf(stringCodePart, stringCodePart.length);

            int i = stringCodePart.length - 1;
            newStringCodePart[i]++;
            for (; i > 0 && newStringCodePart[i] >= letters.size(); i--) {
                newStringCodePart[i] = 0;
                newStringCodePart[i - 1]++;
            }

            if (newStringCodePart[0] >= letters.size()) {
                newStringCodePart[0] = 0;
            }
            return new CarNumber(newStringCodePart, 0);
        } else {
            return new CarNumber(stringCodePart, integerCodePart + 1);
        }
    }


    public boolean isNear(CarNumber carNumber) {
        CarNumber nextThis = next();
        CarNumber nextThat = carNumber.next();

        if (nextThis.equals(carNumber) || nextThat.equals(this)) {
            return true;
        } else {
            return false;
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

        return "" + ansString.charAt(0) + ansInteger + ansString.charAt(1) + ansString.charAt(2);
    }
}
