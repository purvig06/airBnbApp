package com.project.purvigupta.airBnbApp.services;

import com.project.purvigupta.airBnbApp.dto.BookingDto;
import com.project.purvigupta.airBnbApp.dto.BookingRequestDto;
import com.project.purvigupta.airBnbApp.dto.GuestDto;

import java.util.List;

public interface BookingService {
    BookingDto initialiseBooking(BookingRequestDto bookingRequestDto);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
