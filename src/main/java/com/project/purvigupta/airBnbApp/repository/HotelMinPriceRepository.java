package com.project.purvigupta.airBnbApp.repository;

import com.project.purvigupta.airBnbApp.dto.HotelPriceDto;
import com.project.purvigupta.airBnbApp.entity.Hotel;
import com.project.purvigupta.airBnbApp.entity.HotelMinPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.Optional;

public interface HotelMinPriceRepository  extends JpaRepository<HotelMinPrice , Long> {

    @Query("""
    SELECT com.project.purvigupta.airBnbApp.dto.HotelPriceDto(i.hotel , AVG(i.price));
    FROM HotelMinPrice i
    WHERE i.hotel.city = :city
    AND i.date BETWEEN :startDate AND :endDate
    AND i.hotel.active = true
    AND(i.totalCount - i.bookedCount-i.reservedCount) >= :roomsCount
    GROUP BY i.hotel 
    
    """

    )
    Page<HotelPriceDto> findHotelWithAvailableInventory(
            @Param("city")String city,
            @Param("startDate") LocalDate startDate,
            @Param("endDate")LocalDate endDate,
            @Param("roomsCount") Integer roomsCount,
            @Param("dateCount")Long dateCount,
            Pageable pageable
    );

    Optional<HotelMinPrice> findByHotelAndDate(Hotel hotel, LocalDate date);
}
