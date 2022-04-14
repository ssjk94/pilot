package com.pilot;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PilotDao {

	@Select(
		"select version() as version"
		+ ", uid"
		+ ", pwd"
		+ ", uname"
		+ ", notes"
		+ ", input_id"
		+ ", input_dt"
		+ ", update_id"
		+ ", update_dt"
		+ ", del_flag"
		+ " from pilot_user"
		+ " where 1 = 1"
	)
	public PilotDTO getConnectTest() throws Exception;

}
