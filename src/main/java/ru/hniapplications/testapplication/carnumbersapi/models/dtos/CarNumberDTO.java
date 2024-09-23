package ru.hniapplications.testapplication.carnumbersapi.models.dtos;

import jakarta.persistence.*;
import lombok.*;
import ru.hniapplications.testapplication.carnumbersapi.models.CarNumber;


@Builder
@Entity(name = "carcodes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarNumberDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "letters")
    private String stringCode;

    public CarNumberDTO(String carNumber) {
        this.stringCode = carNumber;
    }
}
