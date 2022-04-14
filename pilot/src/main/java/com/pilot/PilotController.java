package com.pilot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pilot.config.Log;

@RestController
public class PilotController {
	
	@Autowired
	private PilotService service;
	
	@GetMapping(value = "/connectTest")
	public ResponseEntity<?> getConnectTest() {
		
		PilotDTO res = null;
		
		try {
			res = service.getConnectTest();
			
		} catch (Exception e) {
			Log.getLog().error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<PilotDTO>(res, HttpStatus.OK);
	}
	
}
