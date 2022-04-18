package com.pilot.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public String getConnectPrimaryTest() throws Exception {
		return p_dao.getConnectTest();
	}

	public String getConnectSubTest() throws Exception {
		return s_dao.getConnectTest();
	}

	public PilotDTO postCreateTable(PilotDTO param) throws Exception {
		p_dao.postCreateTable(param);
		return p_dao.getTable(param);
	}

	public List<PilotDTO> postInsertTable(PilotDTO param) throws Exception {

		List<PilotDTO> list = new ArrayList<PilotDTO>();

		List<String> valueList = param.getValueList();

		if (valueList.size() < 1000) {
			return p_dao.postInsertTable(param);
		}

		int vListSize = valueList.size() / 1000;

		for (int i = 0; i < vListSize + 1; i++) {

			PilotDTO tmpParam = new PilotDTO(param.getTable());
			List<String> tmpValueList = null;

			if (i == vListSize) {
				tmpValueList = valueList.subList(i * 1000, (i + 1) * 1000);
			} else {
				tmpValueList = valueList.subList(i * 1000, valueList.size());
			}
			tmpParam.setValueList(tmpValueList);
			list.addAll(p_dao.postInsertTable(tmpParam));
		}

		return list;
	}

	public Map<String, Object> transferTable(PilotDTO param) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		List<String> tableList = param.getTableList();

		for (String table : tableList) {
			List<Map<String, String>> dataList = p_dao.getTableData(table);
			List<String> columnList = new ArrayList<String>();
			Map<String, String> columns = dataList.get(0);
			columns.forEach((colName, colVal) -> {
				columnList.add(colVal);
			});
			param.setColumnList(columnList);
			param.setDataList(dataList);

			if (dataList.size() < 1000) {
				s_dao.setTableData(param);
				map.put(table, dataList);
				continue;
			}
		}

		return map;
	}

}
