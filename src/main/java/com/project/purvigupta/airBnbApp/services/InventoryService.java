package com.project.purvigupta.airBnbApp.services;

import com.project.purvigupta.airBnbApp.entity.Room;

public interface InventoryService {
    void initializeRoomForAYear(Room room);
    void deleteFutureInventory(Room room);

}
