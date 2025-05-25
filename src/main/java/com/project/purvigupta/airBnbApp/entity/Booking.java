package com.project.purvigupta.airBnbApp.entity;


import com.project.purvigupta.airBnbApp.entity.Enum.BookingStatus;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id" , nullable = false)
    private Hotel  hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "room_id" , nullable = false)
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = " user-id" , nullable = false)
    private User user;

    @Column(nullable = false)
    private Integer roomsCount;

    private LocalDate checkIndate;
    private LocalDate checkOutDate;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;




    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookingStatus bookingStatus;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "booking_guest" ,
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "guest_id")
      )
    private Set<Guest> guests;
    //guest to booking
    @Column(nullable = false , precision = 10 , scale = 2)
    private BigDecimal amount;







}
