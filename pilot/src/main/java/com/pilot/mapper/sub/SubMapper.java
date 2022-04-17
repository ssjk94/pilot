package com.pilot.mapper.sub;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.pilot.dto.PilotDTO;

@Mapper
public interface SubMapper {

	@Select(
		"select current_database() as dbname"
	)
	public String getConnectTest() throws Exception;

	@Select({"<script>"
		+ "insert into ${table} ("
		+ "	<foreach collection='columnList' item='column' separator=','>"
		+ " ${column}"
		+ " </foreach>"
		+ " )"
		+ " values "
		+ " <foreach collection='valueList' item='item' separator=','>"
		+ " ( #{item} )"
		+ " </foreach>"
		+ " returning *"
		+ " </script>"
	})
	public void setTableData(PilotDTO param);
	
}
