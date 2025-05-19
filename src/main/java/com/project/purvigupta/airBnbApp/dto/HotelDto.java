package com.project.purvigupta.airBnbApp.dto;

import com.project.purvigupta.airBnbApp.entity.HotelContactInfo;
import com.project.purvigupta.airBnbApp.entity.Room;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class HotelDto {

    private Long id;


    private String name;
    private String city;
    private String[] photos;
    private String[]amenities; //wi-fi swimming pool


    @Embedded
    private HotelContactInfo contactInfo;

    @Column(nullable = false)
    private Boolean active;



}
