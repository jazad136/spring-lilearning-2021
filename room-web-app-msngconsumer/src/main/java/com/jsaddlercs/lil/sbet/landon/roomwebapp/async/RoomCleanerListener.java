package com.jsaddlercs.lil.sbet.landon.roomwebapp.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsaddlercs.lil.sbet.landon.roomwebapp.models.Room;
import com.jsaddlercs.lil.sbet.landon.roomwebapp.service.RoomService;

@Component
public class RoomCleanerListener {
	private static final Logger LOG = LoggerFactory.getLogger(RoomCleanerListener.class);
	private final ObjectMapper mapper;
	private final RoomService roomService;
	public RoomCleanerListener(ObjectMapper mapper, RoomService roomServie) {
		this.mapper = mapper;
		this.roomService = roomServie;
	}
	
	public void receiveMessage(String message) { 
		try { 
			AsyncPayload payload = mapper.readValue(message, AsyncPayload.class);
			if("ROOM".equals(payload.getId())) { 
				Room room = roomService.getById(payload.getId());
				LOG.info("ROOM {}:{} needs to be cleaned", room.getNumber(), room.getName());
			}
			else { 
				LOG.warn("Unknown model type");
			}
		} catch(JsonProcessingException e) { 
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
