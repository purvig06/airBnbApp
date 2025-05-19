package com.project.purvigupta.airBnbApp.dto;


import lombok.Data;

import java.math.BigDecimal;


@Data
public class RoomDto {

    private Long id;

    private String type;

    private BigDecimal basePrice ;


    private String[] photos;


    private String[]amenities;//wi-fi swimming pool


    private Integer totalCount;


    private Integer capacity;



}
