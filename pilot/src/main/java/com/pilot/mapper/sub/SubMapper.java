package com.pilot.mapper.sub;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.pilot.dto.PilotDTO;

@Mapper
public interface SubMapper {

	@Select(
		"select current_database() as dbname"
	)
	public String getConnectTest() throws Exception;
	
	@Update({"<script>"
		+ "create table if not exists ${table} ("
		+ " col_01 serial primary key"
		+ " , col_02 varchar(255) not null"
		+ ")"
		+ " </script>"
	})
	public void setTable(PilotDTO param);

	@Insert({"<script>"
		+ "insert into ${table} ("
		+ "	<foreach collection='columnList' item='column' separator=','>"
		+ " ${column}"
		+ " </foreach>"
		+ " )"
		+ " values"
		+ " <foreach collection='dataList' item='data' separator=','>"
		+ " ("
		+ " <foreach collection='data' item='item' separator=','>"
		+ " #{item}"
		+ " </foreach>"
		+ " )"
		+ " </foreach>"
		+ " on conflict do nothing"
		+ " returning *"
		+ " </script>"
	})
	public int setTableData(PilotDTO param);
	
	@Select(
		"select *"
		+ " from ${table}"
		+ " order by 1 asc")
	public List<Map<String, String>> getTableData(String table) throws Exception;
	
}
