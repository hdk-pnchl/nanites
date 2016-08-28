package com.nanites.theta.business.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nanites.theta.business.bo.AddressEntity;
import com.nanites.theta.business.dao.impl.AddressDAOImpl;

@Service
@Transactional
public class AddressServiceImpl{

	@Autowired
	AddressDAOImpl addressDAO;

	public AddressEntity save(AddressEntity address, long userID) {
		return addressDAO.save(address, userID);
	}

	public AddressEntity saveOrUpdate(AddressEntity address) {
		addressDAO.saveOrUpdate(address);
		return address;
	}

	public AddressEntity update(AddressEntity address){
		return addressDAO.update(address);
	}
	
	public AddressEntity update(AddressEntity address, long userID){
		return addressDAO.update(address);
	}

	public AddressEntity get(long addressId) {
		return addressDAO.get(addressId);
	}

	public List<AddressEntity> list() {
		return addressDAO.list();
	}

	public void delete(AddressEntity address) {
		addressDAO.delete(address);
	}

	public void deletePermanently(AddressEntity address) {
		addressDAO.deletePermanently(address);
	}
}