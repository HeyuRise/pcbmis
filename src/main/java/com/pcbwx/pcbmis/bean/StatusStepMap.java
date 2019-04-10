package com.pcbwx.pcbmis.bean;

import java.util.HashMap;
import java.util.Map;

public class StatusStepMap {
	private Map<Integer, Integer> statusStepMapById = new HashMap<Integer, Integer>();
	private Map<String, Integer> statusStepMapByCode = new HashMap<String, Integer>();
	
	public Integer get(Integer status) {
		return statusStepMapById.get(status);
	}
	public void put(Integer status, Integer step) {
		statusStepMapById.put(status, step);
	}
	public Integer get(String status) {
		return statusStepMapByCode.get(status);
	}
	public void put(String status, Integer step) {
		statusStepMapByCode.put(status, step);
	}
}
