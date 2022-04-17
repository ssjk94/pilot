package com.pilot.mapper.primary;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.pilot.dto.PilotDTO;

@Mapper
public interface PrimaryMapper {

	@Select(
		"select version() as version"
	)
	public PilotDTO getConnectTest() throws Exception;

}
