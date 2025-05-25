package com.project.purvigupta.airBnbApp.services;

import com.project.purvigupta.airBnbApp.dto.BookingDto;
import com.project.purvigupta.airBnbApp.dto.BookingRequestDto;

public interface BookingService {
    BookingDto initialiseBooking(BookingRequestDto bookingRequestDto);
}
