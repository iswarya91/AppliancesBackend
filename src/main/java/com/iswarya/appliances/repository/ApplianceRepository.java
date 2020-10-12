package com.iswarya.appliances.repository;

import com.iswarya.appliances.model.Appliance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplianceRepository extends JpaRepository<Appliance, Long> {

    @Query("FROM Appliance WHERE serialNumber=?1 AND brand=?2 AND model=?3")
    List<Appliance> findBySerialNumberBrandAndModel(String serialNumber, String brand, String model);
}
