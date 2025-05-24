package com.project.purvigupta.airBnbApp.services;

import com.project.purvigupta.airBnbApp.dto.HotelDto;
import com.project.purvigupta.airBnbApp.dto.HotelSearchRequest;
import com.project.purvigupta.airBnbApp.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {
    void initializeRoomForAYear(Room room);
    void deleteAllInventory(Room room);
    Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest );

}
