package com.pilot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilot.dto.PilotDTO;
import com.pilot.mapper.primary.PrimaryMapper;
import com.pilot.mapper.sub.SubMapper;

@Service
public class PilotService {

	@Autowired
	private PrimaryMapper p_dao;
	
	@Autowired
	private SubMapper s_dao;
	
	public PilotDTO getConnectPrimaryTest() throws Exception {
		return p_dao.getConnectTest();
	}
	
	public PilotDTO getConnectSubTest() throws Exception {
		return s_dao.getConnectTest();
	}

}
