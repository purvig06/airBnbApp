package com.project.purvigupta.airBnbApp.controller;

import com.project.purvigupta.airBnbApp.dto.BookingDto;
import com.project.purvigupta.airBnbApp.dto.BookingRequestDto;
import com.project.purvigupta.airBnbApp.dto.GuestDto;
import com.project.purvigupta.airBnbApp.services.BookingService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class HotelBookingController {
    private final BookingService bookingService;


    @PostMapping("/init")
    public ResponseEntity<BookingDto> initialiseBooking(@RequestBody BookingRequestDto bookingRequestDto){
        return ResponseEntity.ok(bookingService.initialiseBooking(bookingRequestDto));

    }
    @PostMapping("/{bookingId}/addGuests")
    public ResponseEntity<BookingDto>addGuests(@PathVariable Long bookingId , @RequestBody List<GuestDto>guestDtoList){
        return ResponseEntity.ok(bookingService.addGuests(bookingId , guestDtoList));
    }
}
