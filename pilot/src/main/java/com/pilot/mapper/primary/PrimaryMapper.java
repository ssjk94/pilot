package com.pilot.mapper.primary;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pilot.dto.PilotDTO;

@Mapper
public interface PrimaryMapper {

	@Select(
		"select current_database() as dbname"
	)
	public String getConnectTest() throws Exception;

	@Update(
		"create table if not exists ${table} ("
		+ " col_01 serial primary key"
		+ " , col_02 varchar(255) not null"
		+ ")"
	)
	public int postCreateTable(PilotDTO param) throws Exception;
	
	@Select(
		"select table_name as table"
		+ " from information_schema.tables"
		+ " where table_schema = 'public'"
		+ " and table_name = #{table}"
	)
	public Map<String, String> getTable(PilotDTO param) throws Exception;

	
	@Select({"<script>"
		+ "insert into ${table} ("
		+ " col_02 "
		+ " ) values "
		+ " <foreach collection='valueList' item='value' separator=','>"
		+ " ( #{value} )"
		+ " </foreach>"
		+ " returning *"
		+ " </script>"
	})
	public List<Map<String, String>> postInsertTable(PilotDTO param) throws Exception;
	
	@Select("select *"
			+ " from ${table}"
			+ " order by 1 asc")
	public List<Map<String, Object>> getTableData(String table) throws Exception;
}
