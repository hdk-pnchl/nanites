package com.nanites.theta.business.service.impl.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nanites.theta.business.bo.user.BasicDetailEntity;
import com.nanites.theta.business.bo.user.UserEntity;
import com.nanites.theta.business.dao.impl.user.BasicDetailDAOImpl;

@Service
@Transactional
public class BasicDetailServiceImpl{

	@Autowired
	private BasicDetailDAOImpl basicDetailDAO;

	public UserEntity save(BasicDetailEntity basicDetail, long userID) {
		return basicDetailDAO.save(basicDetail, userID);
	}

	public UserEntity update(BasicDetailEntity basicDetail, long userID){
		return basicDetailDAO.update(basicDetail, userID);
	}

	public BasicDetailEntity get(long addressId) {
		return basicDetailDAO.get(addressId);
	}

	public List<BasicDetailEntity> list() {
		return basicDetailDAO.list();
	}

	public void delete(BasicDetailEntity basicDetail) {
		basicDetailDAO.delete(basicDetail);
	}

	public void deletePermanently(BasicDetailEntity basicDetail) {
		basicDetailDAO.deletePermanently(basicDetail);
	}
}