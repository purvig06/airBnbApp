package com.project.purvigupta.airBnbApp.repository;

import com.project.purvigupta.airBnbApp.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<Guest, Long> {
}