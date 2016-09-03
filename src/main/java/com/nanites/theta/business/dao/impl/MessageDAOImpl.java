package com.nanites.theta.business.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nanites.theta.business.bo.MessageEntity;
import com.nanites.theta.business.type.response.ResponseParam;
import com.nanites.theta.business.util.SearchInput;

@Repository
@Transactional
public class MessageDAOImpl extends AbstractDAO{

	public MessageEntity save(MessageEntity message) {
		this.getSession().save(message);
		return message;
	}

	public MessageEntity update(MessageEntity message) {
		this.getSession().update(message);
		return message;
	}

	public MessageEntity saveOrUpdate(MessageEntity message) {
		this.getSession().saveOrUpdate(message);
		return message;
	}

	public MessageEntity get(long messageID) {
		MessageEntity message = null;
		Object patientObject = this.getSession().get(MessageEntity.class, messageID);
		if (patientObject != null) {
			message = (MessageEntity) patientObject;
		}
		return message;
	}

	@SuppressWarnings("unchecked")
	public List<MessageEntity> loadAll() {
		Criteria criteria = getSession().createCriteria(MessageEntity.class);
		return (List<MessageEntity>) criteria.list();
	}

	public List<MessageEntity> loadAllByEmailId(String emailID) {
		Criteria criteria = getSession().createCriteria(MessageEntity.class);
		if (emailID != null) {
			criteria.add(Restrictions.eq("emailID", emailID));
		}
		@SuppressWarnings("unchecked")
		List<MessageEntity> messageList = criteria.list();
		return messageList;
	}

	@SuppressWarnings("unchecked")
	public List<MessageEntity> loadAll(SearchInput searchInput) {
		int beginIndx = (searchInput.getPageNo() * searchInput.getRowsPerPage()) - searchInput.getRowsPerPage();
		Criteria criteria = this.getSession().createCriteria(MessageEntity.class);
		String emailID = searchInput.getSearchData().get(ResponseParam.EMAIL_ID.getDesc());
		if (emailID != null) {
			criteria.add(Restrictions.eq("emailID", emailID));
		}
		criteria.setFirstResult(beginIndx);
		criteria.setMaxResults(searchInput.getRowsPerPage());
		// criteria.addOrder(Order.asc("lastUpdatedOn"));
		return criteria.list();
	}

	public Long getTotalRowCount(SearchInput searchInput) {
		Criteria criteria = this.getSession().createCriteria(MessageEntity.class);
		String emailID = searchInput.getSearchData().get(ResponseParam.EMAIL_ID.getDesc());
		if (emailID != null) {
			criteria.add(Restrictions.eq("emailID", emailID));
		}		
		criteria.setProjection(Projections.rowCount());
		Long rowCount = (Long) criteria.uniqueResult();
		return rowCount;
	}

	public void delete(MessageEntity message) {
		// TODO Auto-generated method stub

	}

	public void deletePermanently(MessageEntity message) {
		this.getSession().delete(message);
	}
}