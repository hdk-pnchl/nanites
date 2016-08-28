package com.nanites.theta.business.bo;

import java.io.Serializable;
import java.util.Map;

public class ResponseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1012695220974239571L;

	private Map<String, String> responseData;
	private Object responseEntity;

	public Map<String, String> getResponseData() {
		return responseData;
	}

	public void setResponseData(Map<String, String> responseData) {
		this.responseData = responseData;
	}

	public Object getResponseEntity() {
		return responseEntity;
	}

	public void setResponseEntity(Object responseEntity) {
		this.responseEntity = responseEntity;
	}

}
