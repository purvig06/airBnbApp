package com.project.purvigupta.airBnbApp.repository;

import com.project.purvigupta.airBnbApp.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository  extends JpaRepository<Hotel, Long> {


}
