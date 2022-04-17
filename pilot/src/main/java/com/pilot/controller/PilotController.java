package com.pilot.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pilot.config.Log;
import com.pilot.dto.PilotDTO;
import com.pilot.service.PilotService;

@RestController
public class PilotController {
	
	@Autowired
	private PilotService service;
	
	@GetMapping(value = "/connectPrimaryTest")
	public ResponseEntity<?> getConnectTest() {
		
		String res = null;
		
		try {
			res = service.getConnectPrimaryTest();
			
		} catch (Exception e) {
			Log.getLog().error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	@GetMapping(value = "/connectSubTest")
	public ResponseEntity<?> getConnectSubTest() {
		
		String res = null;
		
		try {
			res = service.getConnectSubTest();
			
		} catch (Exception e) {
			Log.getLog().error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<String>(res, HttpStatus.OK);
	}
	
	@PostMapping(value = "createPrimaryTable")
	public ResponseEntity<?> postCreateTable(@RequestBody PilotDTO param) {
		
		PilotDTO dto = null;
		
		try {
			dto = service.postCreateTable(param);
			
		} catch (Exception e) {
			Log.getLog().error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<PilotDTO>(dto, HttpStatus.OK);
	}
	
	@PostMapping(value = "insertPrimaryTable")
	public ResponseEntity<?> postInsertTable(@RequestBody PilotDTO param) {
		
		List<PilotDTO> list = null;
		
		try {
			list = service.postInsertTable(param);
			
		} catch (Exception e) {
			Log.getLog().error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<List<PilotDTO>>(list, HttpStatus.OK);
	}
	
	@PostMapping(value = "transferTable")
	public ResponseEntity<?> postTransferTable(@RequestBody PilotDTO param) {
		
		Map<String, Object> map = null;
		
		try {
			map = service.transferTable(param);
			
		} catch (Exception e) {
			Log.getLog().error(e.getMessage());
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
}
