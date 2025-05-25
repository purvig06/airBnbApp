package com.project.purvigupta.airBnbApp.controller;

import com.project.purvigupta.airBnbApp.dto.HotelDto;
import com.project.purvigupta.airBnbApp.dto.HotelInfoDto;
import com.project.purvigupta.airBnbApp.dto.HotelSearchRequest;
import com.project.purvigupta.airBnbApp.services.HotelService;
import com.project.purvigupta.airBnbApp.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowseController {
    private final InventoryService inventoryService;
    private final HotelService hotelService;

@GetMapping("/search")
    public ResponseEntity<Page<HotelDto>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest){
    Page<HotelDto> page = inventoryService.searchHotels(hotelSearchRequest);
    return ResponseEntity.ok(page);

}
@GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId){
    return  ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));
}



}
