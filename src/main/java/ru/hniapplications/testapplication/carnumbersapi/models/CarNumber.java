package ru.hniapplications.testapplication.carnumbersapi.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.hniapplications.testapplication.carnumbersapi.models.entities.CarNumberEntity;

import java.util.*;

@Getter
@EqualsAndHashCode
public class CarNumber {
    private final String constStringCodePart = "116 RUS";
    private final List<Character> letters;

    private int alphabeticCodePart;
    private int integerCodePart;

    public CarNumber() {
        letters = new ArrayList<>();

        letters.addAll(Arrays.asList('А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'));

        Collections.sort(letters);

        int lettersSize = letters.size();

        Random random = new Random();

        integerCodePart = random.nextInt(1000);

        alphabeticCodePart = random.nextInt(lettersSize * lettersSize * lettersSize);
    }

    private CarNumber(int alphabeticCodePart, int integerCodePart) {
        this();
        this.alphabeticCodePart = alphabeticCodePart;
        this.integerCodePart = integerCodePart;
    }

    public CarNumber(String sign, int integerCodePart) {
        this();
        parseAlphabeticCodePartToInteger(sign);
        this.integerCodePart = integerCodePart;
    }

    public CarNumber(CarNumberEntity carNumberEntity) {
        this();
        String sign = carNumberEntity.getStringCode();

        parseAlphabeticCodePartToInteger("" + sign.charAt(0) + sign.charAt(4) + sign.charAt(5));

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.delete(0, stringBuilder.length());

        stringBuilder.append(sign.charAt(1));
        stringBuilder.append(sign.charAt(2));
        stringBuilder.append(sign.charAt(3));
        integerCodePart = Integer.parseInt(stringBuilder.toString());
    }

    private void parseAlphabeticCodePartToInteger(String stringCodePart) {
        int parsed = 0;
        for (int i = 0; i < stringCodePart.length(); i++) {
            int foundIndex = letters.indexOf(stringCodePart.charAt(i));
            parsed = parsed * letters.size() + (foundIndex);
        }
        alphabeticCodePart = parsed;
    }

    private String parseAlphabeticCodePartToString() {
        StringBuilder stringBuilder = new StringBuilder();

        int lettersSize = letters.size();

        int buf = alphabeticCodePart;

        stringBuilder.append(letters.get(buf / (lettersSize * lettersSize)));
        stringBuilder.append(letters.get(buf % (lettersSize * lettersSize) / lettersSize));
        stringBuilder.append(letters.get(buf % lettersSize));

        return stringBuilder.toString();
    }

    public CarNumber next() {
        int lettersSize = letters.size();
        if (integerCodePart == 999) {
            if (alphabeticCodePart == lettersSize * lettersSize * lettersSize - 1) {
                throw new IllegalArgumentException("You cannot use cars numbers after X999XX");
            }

            return new CarNumber(alphabeticCodePart + 1, 0);
        } else {
            return new CarNumber(alphabeticCodePart, integerCodePart + 1);
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
        return toSave() + " " + constStringCodePart;
    }

    public String toSave() {
        String ansString = parseAlphabeticCodePartToString();
        String ansInteger = String.format("%03d", integerCodePart);

        return ansString.charAt(0) + ansInteger + ansString.charAt(1) + ansString.charAt(2);
    }
}
