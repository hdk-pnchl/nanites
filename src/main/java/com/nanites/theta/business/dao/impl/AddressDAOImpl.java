package com.nanites.theta.business.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nanites.theta.business.bo.AddressEntity;

@Repository
@Transactional
public class AddressDAOImpl extends AbstractDAO{
	public AddressEntity save(AddressEntity address, long patientId) {
		this.getSession().save(address);
		return null;
	}

	public AddressEntity saveOrUpdate(AddressEntity address) {
		this.getSession().saveOrUpdate(address);
		return address;
	}

	public AddressEntity update(AddressEntity address) {
		this.getSession().update(address);
		return address;
	}
	
	public AddressEntity update(AddressEntity address, long userID) {
		this.getSession().update(address);
		return address;
	}
	
	public AddressEntity get(long addressID) {
		AddressEntity address = null;
		Object patientObject = this.getSession().get(AddressEntity.class, addressID);
		if (patientObject != null) {
			address = (AddressEntity) patientObject;
		}
		return address;
	}

	@SuppressWarnings("unchecked")
	public List<AddressEntity> list() {
		Criteria criteria = getSession().createCriteria(AddressEntity.class);
		return (List<AddressEntity>) criteria.list();
	}

	public void delete(AddressEntity address) {
		// TODO Auto-generated method stub
	}

	public void deletePermanently(AddressEntity address) {
		this.getSession().delete(address);
	}
}