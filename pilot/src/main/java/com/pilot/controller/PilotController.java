package com.pilot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pilot.config.Log;
import com.pilot.dto.PilotDTO;
import com.pilot.dto.ResponseDTO;
import com.pilot.service.PilotService;

@RestController
public class PilotController {
	
	@Autowired
	private PilotService service;
	
	@GetMapping(value = "/connectPrimaryTest")
	public ResponseEntity<?> getConnectTest() {
		
		PilotDTO res = null;
		
		try {
			res = service.getConnectPrimaryTest();
			
		} catch (Exception e) {
			Log.getLog().error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<PilotDTO>(res, HttpStatus.OK);
	}
	
	@GetMapping(value = "/connectSubTest")
	public ResponseEntity<?> getConnectSubTest() {
		
		PilotDTO res = null;
		
		try {
			res = service.getConnectSubTest();
			
		} catch (Exception e) {
			Log.getLog().error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<PilotDTO>(res, HttpStatus.OK);
	}
	
	@PostMapping(value = "createTable")
	public ResponseEntity<?> postCreateTable(@RequestBody PilotDTO param) {
		
		ResponseDTO dto = null;
		
		try {
			
		} catch (Exception e) {
			Log.getLog().error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ResponseDTO>(dto, HttpStatus.OK);
	}
	
	@PostMapping(value = "insertTable")
	public ResponseEntity<?> postInsertTable(@RequestBody PilotDTO param) {
		
		ResponseDTO dto = null;
		
		try {
			
		} catch (Exception e) {
			Log.getLog().error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<ResponseDTO>(dto, HttpStatus.OK);
	}
	
	
}
