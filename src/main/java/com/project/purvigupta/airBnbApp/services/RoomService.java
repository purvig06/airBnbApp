package com.project.purvigupta.airBnbApp.services;

import com.project.purvigupta.airBnbApp.dto.RoomDto;

import java.util.List;

public interface RoomService {
    RoomDto createNewRoom(RoomDto roomDto);
    List<RoomDto>getAllRoomsInHotel( Long hotelId);
    RoomDto getRoomById(Long RoomId);
    void deleteRoomById(Long RoomId);



}
