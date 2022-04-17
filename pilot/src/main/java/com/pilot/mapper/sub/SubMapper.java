package com.pilot.mapper.sub;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.pilot.dto.PilotDTO;

@Mapper
public interface SubMapper {

	@Select(
		"select version() as version"
	)
	public PilotDTO getConnectTest() throws Exception;
	
}
