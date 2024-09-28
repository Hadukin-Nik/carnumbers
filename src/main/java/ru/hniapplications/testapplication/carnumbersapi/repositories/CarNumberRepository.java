package ru.hniapplications.testapplication.carnumbersapi.repositories;

import jakarta.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hniapplications.testapplication.carnumbersapi.models.entities.CarNumberEntity;

@Repository
@Table(name = "messages")
public interface CarNumberRepository extends JpaRepository<CarNumberEntity, Long> {
    @Query(value = "Select * from carcodes order by date limit 1", nativeQuery = true)
    public CarNumberEntity getLastMessage();

    @Query(value = "Select * from carcodes where letters = ?1 limit 1", nativeQuery = true)
    public CarNumberEntity findByCarNumber(String carNumber);
}
