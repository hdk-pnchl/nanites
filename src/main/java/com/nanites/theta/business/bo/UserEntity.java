package com.nanites.theta.business.bo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nanites.theta.business.bo.type.Sex;
import com.nanites.theta.business.util.CommonUtil;

@Entity
@Table
public class UserEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1457413248383951436L;
	
	// instance

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String emailID;
	private String password;
	private long regNO;
	private int contactNO;

	private int age;
	private Sex sex;
	private boolean isMarried;
	private String education;
	private String occupation;
	
	private Date createdOn = new Date();
	private Date lastUpdatedOn = new Date();
	
	private boolean isAccountExpired = true;
	private boolean isAccountLocked = true;
	private boolean isAccountEnabled = true;
	private boolean isAccountCredentialsExpired = true;
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
	private AddressEntity address;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE", joinColumns = @JoinColumn(name = "userID"), inverseJoinColumns = @JoinColumn(name = "roleID"))
	private Set<RoleEntity> roles = new HashSet<RoleEntity>();
	
	//constructor

	public UserEntity() {
		this.populateRegNo();
	}
	
	/**
	 * This should not be used from anywhere other then BasicDetailEntity
	 * constructor
	 */
	private void populateRegNo() {
		this.setRegNO(CommonUtil.nextRegNo());
	}
	
	// setter-getter

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmailID() {
		return emailID;
	}
	
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public long getRegNO() {
		return regNO;
	}
	
	public void setRegNO(long regNO) {
		this.regNO = regNO;
	}
	
	public int getAge() {
		return age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public Sex getSex() {
		return sex;
	}
	
	public void setSex(Sex sex) {
		this.sex = sex;
	}
	
	public boolean isMarried() {
		return isMarried;
	}
	
	public void setMarried(boolean isMarried) {
		this.isMarried = isMarried;
	}
	
	public String getEducation() {
		return education;
	}
	
	public void setEducation(String education) {
		this.education = education;
	}
	
	public String getOccupation() {
		return occupation;
	}
	
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	
	public Date getCreatedOn() {
		return createdOn;
	}
	
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}
	
	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}
	
	public AddressEntity getAddress() {
		return address;
	}
	
	public void setAddress(AddressEntity address) {
		this.address = address;
	}
	
	public Set<RoleEntity> getRoles() {
		return roles;
	}
	
	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

	public boolean isAccountExpired() {
		return isAccountExpired;
	}

	public void setAccountExpired(boolean isAccountExpired) {
		this.isAccountExpired = isAccountExpired;
	}

	public boolean isAccountLocked() {
		return isAccountLocked;
	}

	public void setAccountLocked(boolean isAccountLocked) {
		this.isAccountLocked = isAccountLocked;
	}

	public boolean isAccountEnabled() {
		return isAccountEnabled;
	}

	public void setAccountEnabled(boolean isAccountEnabled) {
		this.isAccountEnabled = isAccountEnabled;
	}

	public boolean isAccountCredentialsExpired() {
		return isAccountCredentialsExpired;
	}

	public void setAccountCredentialsExpired(boolean isAccountCredentialsExpired) {
		this.isAccountCredentialsExpired = isAccountCredentialsExpired;
	}

	public int getContactNO() {
		return contactNO;
	}

	public void setContactNO(int contactNO) {
		this.contactNO = contactNO;
	}
	
	// override
}
