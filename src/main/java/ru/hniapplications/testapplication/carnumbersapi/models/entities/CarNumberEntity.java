package ru.hniapplications.testapplication.carnumbersapi.models.entities;

import jakarta.persistence.*;
import lombok.*;


@Builder
@Entity(name = "carcodes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarNumberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "letters")
    private String stringCode;

    public CarNumberEntity(String carNumber) {
        this.stringCode = carNumber;
    }
}
