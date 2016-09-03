package com.nanites.theta.business.bo;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ResponseEntity implements Serializable {
	private static final long serialVersionUID = 1012695220974239571L;
	
	private Map<String, String> responseData;
	private Object responseEntity;

	public ResponseEntity() {
	}
	
	public ResponseEntity(Builder builder) {
		this.responseData = builder.responseData;
		this.responseEntity = builder.responseEntity;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private Map<String, String> responseData;
		private Object responseEntity;
		
		public Builder responseData(Map<String, String> responseData) {
			this.responseData = responseData;
			return this;
		}
		
		public Builder responseEntity(Object responseEntity) {
			this.responseEntity = responseEntity;
			return this;
		}
		
		public ResponseEntity build() {
			return new ResponseEntity(this);
		}
	}
	
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
