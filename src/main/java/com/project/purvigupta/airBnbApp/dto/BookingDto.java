package com.project.purvigupta.airBnbApp.dto;

import com.project.purvigupta.airBnbApp.entity.Enum.BookingStatus;
import com.project.purvigupta.airBnbApp.entity.Guest;
import com.project.purvigupta.airBnbApp.entity.Hotel;
import com.project.purvigupta.airBnbApp.entity.Room;
import com.project.purvigupta.airBnbApp.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {


    private Long id;





    private Integer roomsCount;

    private LocalDate checkIndate;
    private LocalDate checkOutDate;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    private BookingStatus bookingStatus;



    private Set<GuestDto> guests;
}
