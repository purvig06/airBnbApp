package com.project.purvigupta.airBnbApp.services;

import com.project.purvigupta.airBnbApp.dto.RoomDto;
import com.project.purvigupta.airBnbApp.entity.Hotel;
import com.project.purvigupta.airBnbApp.entity.Room;
import com.project.purvigupta.airBnbApp.exception.ResourceNotFoundException;
import com.project.purvigupta.airBnbApp.repository.HotelRepository;
import com.project.purvigupta.airBnbApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomServiceImpl  implements RoomService{

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;


    @Override
    public RoomDto createNewRoom( Long hotelId , RoomDto roomDto) {
        log.info("Creating a new room in hotel with id " + hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)

                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with this id " + hotelId));
        hotel.setActive(true);
        Room room = modelMapper.map(roomDto , Room.class);
        room.setHotel(hotel);
        room = roomRepository.save(room);
        return modelMapper.map(room , RoomDto.class);
        //todo: create inventory as soon as room is created

    }

    @Override
    public List<RoomDto> getAllRoomsInHotel(Long hotelId) {
        log.info("getting all rooms in hotel with id" + hotelId );
        Hotel hotel = hotelRepository
                .findById(hotelId)

                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with this id " + hotelId));
                 return hotel.getRooms()
                         .stream()
                         .map((element)-> modelMapper.map(element, RoomDto.class))
                         .collect(Collectors.toList());


    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Getting the rooms with id"+roomId);
      Room room = roomRepository
                .findById(roomId)

                .orElseThrow(() -> new ResourceNotFoundException("Room not found with this id " + roomId));
                return modelMapper.map(room , RoomDto.class);
    }

    @Override
    public void deleteRoomById(Long roomId) {
        log.info("Deleting the room with id: {}"+roomId);
        boolean exist = roomRepository.existsById(roomId);
        if(!exist){
            throw new ResourceNotFoundException("Room not  found with this id"+roomId);
        }
        roomRepository.deleteById(roomId);


    }
}
