package com.github.rafaelmendes1.meetingapi.controllers;

import com.github.rafaelmendes1.meetingapi.entities.Room;
import com.github.rafaelmendes1.meetingapi.exceptions.ResourceNotFoundException;
import com.github.rafaelmendes1.meetingapi.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/rooms")
public class RoomController {

    @Autowired
    private RoomRepository repository;

    @GetMapping
    public List<Room> getAllRooms() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable Long id) throws ResourceNotFoundException {
        Room room = repository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Room not found:: " + id));
        return ResponseEntity.ok().body(room);
    }

    @PostMapping
    public Room createRoom(@Valid @RequestBody Room room) {
        return repository.save(room);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @Valid @RequestBody Room roomDetails) throws ResourceNotFoundException {
        Room room = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Room not found:: " + id));

        room.setName(roomDetails.getName());
        room.setDate(roomDetails.getDate());
        room.setStartHour(roomDetails.getStartHour());
        room.setEndHour(roomDetails.getEndHour());

        final Room updatedRoom = repository.save(room);
        return ResponseEntity.ok(updatedRoom);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteRoom(@PathVariable Long id) throws ResourceNotFoundException {
        Room room = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Room not found:: " + id));

        repository.delete(room);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
