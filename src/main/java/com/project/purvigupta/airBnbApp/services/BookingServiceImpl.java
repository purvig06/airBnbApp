package com.project.purvigupta.airBnbApp.services;

import com.project.purvigupta.airBnbApp.dto.BookingDto;
import com.project.purvigupta.airBnbApp.dto.BookingRequestDto;
import com.project.purvigupta.airBnbApp.dto.GuestDto;
import com.project.purvigupta.airBnbApp.entity.*;
import com.project.purvigupta.airBnbApp.entity.Enum.BookingStatus;
import com.project.purvigupta.airBnbApp.exception.ResourceNotFoundException;
import com.project.purvigupta.airBnbApp.repository.*;
import jakarta.annotation.Nullable;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookingServiceImpl  implements BookingService{
    private final GuestRepository guestRepository;
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
            inventory.setReservedCount(inventory.getReservedCount() + bookingRequestDto.getRoomsCount());
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

    @Override
    public BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList) {
        log.info("Adding guest with id {}" , bookingId);
        Booking booking  = bookingRepository.findById(bookingId).orElseThrow(()->
                new ResourceNotFoundException("Booking not found with id" + bookingId));
        if(hasBookingExpired(booking)){
            throw new IllegalStateException("Booking has already expired");
        }
        if(booking.getBookingStatus()!=BookingStatus.RESERVED){
            throw new IllegalStateException("Booking is not under reserved status,cannot add guests");
        }
        for(GuestDto guestDto :guestDtoList){
            Guest guest = modelMapper.map(guestDto , Guest.class);
            guest.setUser(getCurrentUser());
            guest = guestRepository.save(guest);
            booking.getGuests().add(guest);
        }
        booking.setBookingStatus(BookingStatus.GUESTS_ADDED);
      booking =   bookingRepository.save(booking);
        return modelMapper.map(booking, BookingDto.class);

    }
    public boolean hasBookingExpired(Booking booking){
        return booking.getCreatedAt().plusMinutes(10).isBefore(LocalDateTime.now());
    }
    public User getCurrentUser(){
        User user = new User();
        user.setId(1L);
        return user;

    }
}
