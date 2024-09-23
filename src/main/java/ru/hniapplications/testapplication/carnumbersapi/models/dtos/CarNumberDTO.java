package ru.hniapplications.testapplication.carnumbersapi.models.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;


@Builder
@Entity(name = "carcodes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarNumberDTO {
    @Id
    private Long id;

    @Column(name = "letters")
    private String stringCode;
}
