package com.project.purvigupta.airBnbApp.repository;


import com.project.purvigupta.airBnbApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room , Long> {

}

