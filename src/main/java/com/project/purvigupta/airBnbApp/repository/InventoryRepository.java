package com.project.purvigupta.airBnbApp.repository;


import com.project.purvigupta.airBnbApp.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
