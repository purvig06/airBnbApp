package com.project.purvigupta.airBnbApp.services;

import com.project.purvigupta.airBnbApp.dto.HotelDto;
import com.project.purvigupta.airBnbApp.entity.Hotel;
import com.project.purvigupta.airBnbApp.exception.ResourceNotFoundException;
import com.project.purvigupta.airBnbApp.repository.HotelRepository;
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
        boolean exists = hotelRepository.existsById(id);
        if(!exists) throw new ResourceNotFoundException("Hotel not found with ID : " + id);
        hotelRepository.deleteById(id);
       //todo:delete the future inventories for this hotel



    }

    @Override
    public void activateHotel(Long hotelId) {
        log.info("Activating the hotel with id" + hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)

                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with this id " + hotelId));
              hotel.setActive(true);

              //todo:create inventory for all the rooms for this hotel

    }
}
