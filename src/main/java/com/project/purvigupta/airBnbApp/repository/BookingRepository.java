package com.project.purvigupta.airBnbApp.repository;

import com.project.purvigupta.airBnbApp.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

}
