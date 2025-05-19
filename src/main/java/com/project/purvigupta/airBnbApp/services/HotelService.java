package com.project.purvigupta.airBnbApp.services;

import com.project.purvigupta.airBnbApp.dto.HotelDto;
import com.project.purvigupta.airBnbApp.entity.Hotel;

public interface HotelService  {
    HotelDto createNewHotel(HotelDto hotelDto);
    HotelDto getHotelById(Long id);
}
