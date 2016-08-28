package com.nanites.theta.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nanites.theta.business.bo.AddressEntity;
import com.nanites.theta.business.bo.ResponseEntity;
import com.nanites.theta.business.service.impl.AddressServiceImpl;

@Controller
@RequestMapping("/user/address")
public class AddressController {
	private static final Logger logger = Logger.getLogger(AddressController.class);
	@Autowired
	private AddressServiceImpl addressService;
	
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public void test(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().write("Yes, It works");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity save(@RequestBody AddressEntity address, @RequestParam("userID") long userID) {
		logger.info("["+userID+"]: "+address);
		address = addressService.save(address, userID);
		ResponseEntity response = new ResponseEntity();
		response.setResponseEntity(address);
		return response;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity update(@RequestBody AddressEntity address, @RequestParam("userID") long userID) {
		logger.info("["+userID+"]: "+address);
		ResponseEntity response = new ResponseEntity();
		address = addressService.update(address, userID);
		response.setResponseEntity(address);
		return response;
	}
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public @ResponseBody AddressEntity get(@RequestParam("addressId") long addressId) {
		logger.info("["+addressId+"]");
		AddressEntity address = addressService.get(addressId);
		return address;
	}
}