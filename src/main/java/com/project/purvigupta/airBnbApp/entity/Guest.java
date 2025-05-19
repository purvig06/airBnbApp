package com.project.purvigupta.airBnbApp.entity;

import com.project.purvigupta.airBnbApp.entity.Enum.Gender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter

public class Guest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = " user_id")
    private  User user;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    private Integer age;

     @ManyToMany(mappedBy = "guests")
    private Set<Booking>bookings;
}
