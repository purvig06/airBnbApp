package com.project.purvigupta.airBnbApp.services;

import com.project.purvigupta.airBnbApp.dto.BookingDto;
import com.project.purvigupta.airBnbApp.dto.BookingRequestDto;
import com.project.purvigupta.airBnbApp.entity.*;
import com.project.purvigupta.airBnbApp.entity.Enum.BookingStatus;
import com.project.purvigupta.airBnbApp.exception.ResourceNotFoundException;
import com.project.purvigupta.airBnbApp.repository.BookingRepository;
import com.project.purvigupta.airBnbApp.repository.HotelRepository;
import com.project.purvigupta.airBnbApp.repository.InventoryRepository;
import com.project.purvigupta.airBnbApp.repository.RoomRepository;
import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl  implements BookingService{
    private final ModelMapper modelMapper;
    private final InventoryRepository inventoryRepository;
    private final BookingRepository bookingRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;


    @Override
    @Transactional
    public BookingDto initialiseBooking(BookingRequestDto bookingRequestDto) {
        log.info("Initialisig booking for a hotel : {} , room : {} , date {}-{}" +bookingRequestDto.getHotelId() , bookingRequestDto.getRoomId(),
                bookingRequestDto.getCheckInDate() , bookingRequestDto.getCheckOutDate()
                );
        Hotel hotel = hotelRepository.findById(bookingRequestDto.getHotelId()).orElseThrow(()->
        new ResourceNotFoundException("hotel not found with this id" + bookingRequestDto.getHotelId()));

        Room room  = roomRepository.findById(bookingRequestDto.getHotelId()).orElseThrow(()->
                new ResourceNotFoundException("room not found with this id" + bookingRequestDto.getRoomId()));
        List<Inventory>inventoryList = inventoryRepository.findAndLockAvailableInventory(room.getId(), bookingRequestDto.getCheckInDate(),
        bookingRequestDto.getCheckOutDate() , bookingRequestDto.getRoomsCount());

        long daysCount = ChronoUnit.DAYS.between(bookingRequestDto.getCheckInDate() , bookingRequestDto.getCheckOutDate())+1;

        if(inventoryList.size()!=daysCount){
            throw new IllegalStateException("Rooms is not available anymore");
        }

        //reserve the room  / update the booked count of inventories

        for(Inventory inventory : inventoryList){
            inventory.setBookedCount(inventory.getBookedCount() + bookingRequestDto.getRoomsCount());
        }

        User user = new User();
        user.setId(1L);

        inventoryRepository.saveAll(inventoryList);
        //create the booking
            Booking booking = Booking.builder()
                    .bookingStatus(BookingStatus.RESERVED)
                    .hotel(hotel)
                    .room(room)
                    .checkIndate(bookingRequestDto.getCheckInDate())
                    .checkOutDate(bookingRequestDto.getCheckOutDate())
                    .user(user)
                    .roomsCount(bookingRequestDto.getRoomsCount())
                    .amount(BigDecimal.TEN)
                    .build();
            booking = bookingRepository.save(booking);
            return modelMapper.map(booking, BookingDto.class);

    }
}
