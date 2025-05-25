package com.project.purvigupta.airBnbApp.services;

import com.project.purvigupta.airBnbApp.dto.HotelDto;
import com.project.purvigupta.airBnbApp.dto.HotelInfoDto;
import com.project.purvigupta.airBnbApp.entity.Hotel;

public interface HotelService  {
    HotelDto createNewHotel(HotelDto hotelDto);
    HotelDto getHotelById(Long id);
    HotelDto updateHotelById(Long id , HotelDto hotelDto);
   void deleteHotelById(Long id);
   void activateHotel(Long hotelId);

    HotelInfoDto getHotelInfoById(Long hotelId);
}
