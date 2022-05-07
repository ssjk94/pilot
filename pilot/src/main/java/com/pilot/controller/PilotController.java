package com.pilot.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pilot.dto.PilotDTO;
import com.pilot.service.PilotService;

@RestController
public class PilotController {
	
	@Autowired
	private PilotService service;
	
	@GetMapping(value = "/connectPrimaryTest")
	public ResponseEntity<?> getConnectTest() {
		
		Map<String, String> map = null;
		
		try {
			map = service.getConnectPrimaryTest();
			
		} catch (Exception e) {
			map = service.errorMessage(e.getMessage());
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}
	
	@GetMapping(value = "/connectSubTest")
	public ResponseEntity<?> getConnectSubTest() {
		
		Map<String, String> map = null;
		
		try {
			map = service.getConnectSubTest();
			
		} catch (Exception e) {
			map = service.errorMessage(e.getMessage());
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}
	
	@PostMapping(value = "/getPrimaryTableDataList")
	public ResponseEntity<?> getPrimaryTableDataList(@RequestBody PilotDTO param) {
		
		Map<String, Object> map = null;
		
		try {
			map = service.getPrimaryTableDataList(param);
			
		} catch (Exception e) {
			return new ResponseEntity<Map<String, String>>(service.errorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@PostMapping(value = "/getSubTableDataList")
	public ResponseEntity<?> getSubTableDataList(@RequestBody PilotDTO param) {
		
		Map<String, Object> map = null;
		
		try {
			map = service.getSubTableDataList(param);
			
		} catch (Exception e) {
			return new ResponseEntity<Map<String, String>>(service.errorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@PostMapping(value = "createPrimaryTable")
	public ResponseEntity<?> postCreateTable(@RequestBody PilotDTO param) {
		
		Map<String, String> map = null;
		
		try {
			map = service.postCreateTable(param);
			
		} catch (Exception e) {
			map = service.errorMessage(e.getMessage());
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
	}
	
	@PostMapping(value = "insertPrimaryTable")
	public ResponseEntity<?> postInsertTable(@RequestBody PilotDTO param) {
		
		Map<String, Object> map = null;
		
		try {
			map = service.postInsertTable(param);
			
		} catch (Exception e) {
			return new ResponseEntity<Map<String, String>>(service.errorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
	@PostMapping(value = "transferTable")
	public ResponseEntity<?> postTransferTable(@RequestBody PilotDTO param) {
		
		Map<String, Object> map = null;
		
		try {
			map = service.transferTable(param);
			
		} catch (Exception e) {
			return new ResponseEntity<Map<String, String>>(service.errorMessage(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
	}
	
}
