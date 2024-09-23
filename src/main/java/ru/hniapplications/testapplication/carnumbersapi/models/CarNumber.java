package ru.hniapplications.testapplication.carnumbersapi.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import ru.hniapplications.testapplication.carnumbersapi.models.dtos.CarNumberDTO;

@ToString
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class CarNumber implements Comparable<CarNumber>{
    private String stringCodePart;
    private int integerCodePart;

    public CarNumber(CarNumberDTO carNumberDTO)
    {
        String sign = carNumberDTO.getStringCode();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(sign.charAt(0));
        stringBuilder.append(sign.charAt(4));
        stringBuilder.append(sign.charAt(5));



        stringCodePart = stringBuilder.toString();

        stringBuilder.delete(0, stringBuilder.length());

        stringBuilder.append(sign.charAt(1));
        stringBuilder.append(sign.charAt(2));
        stringBuilder.append(sign.charAt(3));
        integerCodePart = Integer.parseInt(stringBuilder.toString());
    }

    @Override
    public int compareTo(CarNumber o) {
        if(stringCodePart.equals(o.stringCodePart)) {
            return Integer.compare(integerCodePart, o.integerCodePart);
        } else {
            return stringCodePart.compareTo(o.stringCodePart);
        }
    }


}
