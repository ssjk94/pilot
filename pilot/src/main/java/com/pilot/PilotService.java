package com.pilot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PilotService {

	@Autowired
	private PilotDao dao;
	
	public PilotDTO getConnectTest() throws Exception {
		return dao.getConnectTest();
	}

}
