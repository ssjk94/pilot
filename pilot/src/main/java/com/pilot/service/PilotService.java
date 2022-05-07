package com.pilot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pilot.config.Log;
import com.pilot.dto.PilotDTO;
import com.pilot.mapper.primary.PrimaryMapper;
import com.pilot.mapper.sub.SubMapper;

@Service
public class PilotService {

	@Autowired
	private PrimaryMapper p_dao;

	@Autowired
	private SubMapper s_dao;

	public Map<String, String> getConnectPrimaryTest() throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("RESULT", "00");
		map.put("MESSAGE", p_dao.getConnectTest());
		
		return map;
	}

	public Map<String, String> getConnectSubTest() throws Exception {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("RESULT", "00");
		map.put("MESSAGE", s_dao.getConnectTest());
		
		return map;
	}

	public Map<String, String> postCreateTable(PilotDTO param) throws Exception {
		
		p_dao.postCreateTable(param);
		
		Map<String, String> map = p_dao.getTable(param);
		map.put("RESULT", "00");
		
		return map;
	}

	public Map<String, Object> getPrimaryTableDataList(PilotDTO param) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		for (String table : param.getTableList()) {
			List<Map<String, Object>> list = p_dao.getTableData(table);
			dataMap.put(table, list);
			
		}
		map.put("RESULT", "00");
		map.put("MESSAGE", dataMap);
		
		return map;
	}

	public Map<String, Object> getSubTableDataList(PilotDTO param) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		for (String table : param.getTableList()) {
			List<Map<String, String>> list = s_dao.getTableData(table);
			dataMap.put(table, list);
			
		}
		map.put("RESULT", "00");
		map.put("MESSAGE", dataMap);
		
		return map;
	}

	public Map<String, Object> postInsertTable(PilotDTO param) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		List<String> valueList = param.getValueList();
		
		if (valueList.size() == 0) {
			throw new Exception("table[" + param.getTable() + "] value is empty");
		}

		// PostgreSQL은 1000개 이상의 multi-insert를 지원하지 않는다.
		if (valueList.size() < 1000) {
			map.put(param.getTable(), p_dao.postInsertTable(param));
			map.put("RESULT", "00");
			return map;
		}

		int vListSize = valueList.size() / 1000;

		for (int i = 0; i < vListSize + 1; i++) {

			PilotDTO tmpParam = new PilotDTO(param.getTable(), param.getColumnList());
			List<String> tmpValueList = null;

			if (i == vListSize) {
				tmpValueList = valueList.subList(i * 1000, valueList.size());
			} else {
				tmpValueList = valueList.subList(i * 1000, (i + 1) * 1000);
			}
			tmpParam.setValueList(tmpValueList);
			list.addAll(p_dao.postInsertTable(tmpParam));
		}
		map.put("RESULT", "00");
		map.put(param.getTable(), list);

		return map;
	}

	public Map<String, Object> transferTable(PilotDTO param) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<String> tableList = param.getTableList();

		for (String table : tableList) {
			List<Map<String, Object>> dataList = p_dao.getTableData(table);
			
			if (dataList.size() == 0) {
//				throw new Exception("table[" + param.getTable() + "] data is empty");
				continue;
			}
			
			List<String> columnList = new ArrayList<String>();
			Map<String, Object> columns = dataList.get(0);
			columns.forEach((colName, colVal) -> {
				columnList.add(colName);
			});
			param.setColumnList(columnList);
			param.setDataList(dataList);
			param.setTable(table);
			s_dao.setTable(param);

			if (dataList.size() < 1000) {
				s_dao.setTableData(param);
				dataMap.put(table, dataList);
				map.put("RESULT", "00");
				map.put("MESSAGE", dataMap);
				return map;
			}

			int dListSize = dataList.size() / 1000;

			for (int i = 0; i < dListSize + 1; i++) {

				PilotDTO tmpParam = new PilotDTO(param.getTable(), param.getColumnList());
				List<Map<String, Object>> tmpDataList = null;
				Log.getLog().info("i : " + i);

				if (i == dListSize) {
					tmpDataList = dataList.subList(i * 1000, dataList.size());
				} else {
					tmpDataList = dataList.subList(i * 1000, (i + 1) * 1000);
				}
				Log.getLog().info("tmpDataList" + tmpDataList);
				tmpParam.setDataList(tmpDataList);
				s_dao.setTableData(tmpParam);
			}
			dataMap.put(table, dataList);
		}
		Log.getLog().info("dataMap : " + dataMap);
		map.put("RESULT", "00");
		map.put("MESSAGE", dataMap);

		return map;
	}

	public Map<String, String> errorMessage(String message) {

		Map<String, String> map = new HashMap<String, String>();
		
		Log.getLog().error(message);
		map.put("MESSAGE", message);
		map.put("RESULT", "01");
		
		return map;
	}

}
