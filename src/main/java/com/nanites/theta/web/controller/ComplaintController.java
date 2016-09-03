package com.nanites.theta.web.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nanites.theta.business.bo.ComplaintEntity;
import com.nanites.theta.business.bo.ResponseEntity;
import com.nanites.theta.business.service.impl.ComplaintServiceImpl;
import com.nanites.theta.business.type.response.ResponseParam;
import com.nanites.theta.business.util.CommonUtil;
import com.nanites.theta.business.util.SearchInput;

@Controller
@RequestMapping("/complaint")
public class ComplaintController implements ResourceLoaderAware{
	private static final Logger logger = Logger.getLogger(ComplaintController.class);
	
	// instance
	
	@Autowired
	private ComplaintServiceImpl complaintService;	
	@Autowired
	private ObjectMapper objectMapper;
	private ResourceLoader resourceLoader;

	// setter-getter
	
	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}
	
	// web

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity save(@RequestBody ComplaintEntity complaint) {
		logger.info(complaint);
		complaint = complaintService.save(complaint);
		ResponseEntity response = new ResponseEntity();
		response.setResponseEntity(complaint);
		return response;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity update(@RequestBody ComplaintEntity complaint) {
		complaint = complaintService.update(complaint);
		ResponseEntity response = new ResponseEntity();
		response.setResponseEntity(complaint);
		return response;
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity get(@RequestParam("messageId") long complaintId) {
		ComplaintEntity complaint = complaintService.get(complaintId);
		ResponseEntity response = new ResponseEntity();
		response.setResponseEntity(complaint);
		return response;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<ComplaintEntity> list() {
		List<ComplaintEntity> complaintList = null;
		if (CommonUtil.isAdmin()) {
			complaintList = complaintService.list();
		} else {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			complaintList = complaintService.list(auth.getName());
		}
		return complaintList;
	}
	
	@RequestMapping(value = "/listBySeach", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity listBySeach(@RequestBody SearchInput searchInput) {
		if (!CommonUtil.isAdmin()) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			searchInput.getSearchData().put(ResponseParam.EMAIL_ID.getDesc(), auth.getName());
		}

		List<ComplaintEntity> complaintList = complaintService.list(searchInput);
		long rowCount = complaintService.getTotalRowCount(searchInput);

		Map<String, String> respMap = new HashMap<String, String>();
		respMap.put(ResponseParam.ROW_COUNT.getDesc(), String.valueOf(rowCount));
		respMap.put(ResponseParam.CURRENT_PAGE_NO.getDesc(), String.valueOf(searchInput.getPageNo()));
		respMap.put(ResponseParam.TOTAL_PAGE_COUNT.getDesc(), String.valueOf(CommonUtil.calculateNoOfPages(rowCount, searchInput.getRowsPerPage())));
		respMap.put(ResponseParam.ROWS_PER_PAGE.getDesc(), String.valueOf(searchInput.getRowsPerPage()));

		ResponseEntity response = new ResponseEntity();
		response.setResponseData(respMap);
		response.setResponseEntity(complaintList);

		return response;
	}
	
	// data

	/**
	 * http://localhost:8080/theta/ctrl/complaint/getBanner
	 * 
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getColumnData", method = RequestMethod.GET)
	public @ResponseBody List<Object> getColumnData() throws IOException {
		Resource messageColumnJson = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(CommonUtil.isAuth(auth)) {
			Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) auth.getAuthorities();
			if(CommonUtil.isAdmin(authorities)) {
				messageColumnJson = this.resourceLoader.getResource("classpath:data/json/complaint/complaintColumnDataAdmin.json");
			}
			else {
				messageColumnJson = this.resourceLoader.getResource("classpath:data/json/complaint/complaintColumnDataMember.json");
			}
		}
		List<Object> patientColumnData = objectMapper.readValue(messageColumnJson.getFile(), List.class);
		logger.info("getPatientColumnData: " + patientColumnData);
		return patientColumnData;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getWizzardData", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> getWizzardData() throws IOException {
		Resource messageFormData = this.resourceLoader.getResource("classpath:data/json/complaint/complaintWizzardData.json");
		Map<String, Object> messageFormDataMap = objectMapper.readValue(messageFormData.getFile(), Map.class);
		logger.info("getMessageFormData: " + messageFormDataMap);
		
		return messageFormDataMap;
	}	
}
