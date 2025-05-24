package com.project.purvigupta.airBnbApp.services;

import com.project.purvigupta.airBnbApp.dto.HotelDto;
import com.project.purvigupta.airBnbApp.entity.Hotel;
import com.project.purvigupta.airBnbApp.entity.Room;
import com.project.purvigupta.airBnbApp.exception.ResourceNotFoundException;
import com.project.purvigupta.airBnbApp.repository.HotelRepository;
import com.project.purvigupta.airBnbApp.repository.RoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final InventoryService inventoryService;
    private final RoomRepository roomRepository;
    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("creating a new hotel with name :{}" , hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto , Hotel.class );
        hotel.setActive(false);
        hotel = hotelRepository.save(hotel);
        log.info("creating a new hotel with id :{}" , hotelDto.getId());
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {

        log.info("getting a new hotel with id :{}" , id);
        Hotel hotel =  hotelRepository
                .findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Hotel not found with this id " + id));
        return modelMapper.map(hotel , HotelDto.class );
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("updating a new hotel with id :{}" , id);
        Hotel hotel =  hotelRepository
                .findById(id)

                .orElseThrow(()->new ResourceNotFoundException("Hotel not found with this id " + id));
        modelMapper.map(hotelDto,hotel);
       hotel.setId(id);
        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel , HotelDto.class);
    }

    @Override

    public void deleteHotelById(Long id) {
        Hotel hotel = hotelRepository
                .findById(id)

                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with this id " + id));
        hotelRepository.deleteById(id);
        for(Room room : hotel.getRooms()){

            inventoryService.deleteAllInventory(room);
            roomRepository.deleteById(room.getId());
        }
        hotelRepository.deleteById(id);




    }

    @Override
    public void activateHotel(Long hotelId) {
        log.info("Activating the hotel with id" + hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)

                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with this id " + hotelId));
              hotel.setActive(true);

             //assuming only do it once
             for(Room room : hotel.getRooms()){
                 inventoryService.initializeRoomForAYear(room);
             }

    }
}
