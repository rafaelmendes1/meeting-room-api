package com.github.rafaelmendes1.meetingapi;

import com.github.rafaelmendes1.meetingapi.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
}
