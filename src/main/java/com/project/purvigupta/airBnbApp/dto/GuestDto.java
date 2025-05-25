package com.project.purvigupta.airBnbApp.dto;

import com.project.purvigupta.airBnbApp.entity.Booking;
import com.project.purvigupta.airBnbApp.entity.Enum.Gender;
import com.project.purvigupta.airBnbApp.entity.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
public class GuestDto {

    private Long id;


    private User user;


    private String name;


    private Gender gender;

    private Integer age;


    private Set<Booking> bookings;
}
