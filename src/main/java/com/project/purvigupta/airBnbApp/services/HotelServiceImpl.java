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
}
