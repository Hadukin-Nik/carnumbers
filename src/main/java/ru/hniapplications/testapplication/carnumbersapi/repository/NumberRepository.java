package ru.hniapplications.testapplication.carnumbersapi.repository;

import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hniapplications.testapplication.carnumbersapi.models.dtos.CarNumberDTO;

@Repository
@Table(name = "messages")
public interface NumberRepository extends JpaRepository<CarNumberDTO, Long> {
}
