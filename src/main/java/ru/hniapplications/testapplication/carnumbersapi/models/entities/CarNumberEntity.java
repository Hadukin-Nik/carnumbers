package ru.hniapplications.testapplication.carnumbersapi.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


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
    @Column(name = "date")
    private Date date;
    public CarNumberEntity(String carNumber) {
        this.stringCode = carNumber;
        this.date = new Date(System.currentTimeMillis());
    }
}
