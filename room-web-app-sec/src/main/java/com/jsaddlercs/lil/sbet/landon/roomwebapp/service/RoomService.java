package com.jsaddlercs.lil.sbet.landon.roomwebapp.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jsaddlercs.lil.sbet.landon.roomwebapp.data.RoomRepository;
import com.jsaddlercs.lil.sbet.landon.roomwebapp.models.Room;

@Service
public class RoomService {

	private final RoomRepository roomRepository;
	
	public RoomService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}
    public List<Room> getAllRooms(){
//        return rooms;
    	return roomRepository.findAll();
    }
}
