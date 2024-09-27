package ru.hniapplications.testapplication.carnumbersapi.repositories;

import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.hniapplications.testapplication.carnumbersapi.models.entities.CarNumberEntity;

@Repository
@Table(name = "messages")
public interface NumberRepository extends JpaRepository<CarNumberEntity, Long> {
}
