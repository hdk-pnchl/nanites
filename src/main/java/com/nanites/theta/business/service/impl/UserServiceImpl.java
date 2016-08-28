package com.nanites.theta.business.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nanites.theta.business.bo.RoleEntity;
import com.nanites.theta.business.bo.UserEntity;
import com.nanites.theta.business.bo.type.Roles;
import com.nanites.theta.business.dao.impl.UserDAOImpl;
import com.nanites.theta.business.util.SearchInput;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService {
	@Autowired
	private UserDAOImpl userDAO;
	@Autowired
	private AuthorityServiceImpl authorityService;
	
	public UserEntity save(UserEntity user) {
		RoleEntity role = authorityService.getAuthorityMap().get(Roles.MEMBER);
		user.getRoles().add(role);
		this.userDAO.save(user);
		return user;
	}
	
	public UserEntity update(UserEntity user) {
		this.userDAO.update(user);
		return user;
	}
	
	public UserEntity get(long userID) {
		return this.userDAO.get(userID);
	}
	
	public UserEntity get(String emailID) {
		return this.userDAO.get(emailID);
	}
	
	public List<UserEntity> list() {
		return this.userDAO.list();
	}
	
	public List<UserEntity> list(SearchInput searchInput) {
		return this.userDAO.list(searchInput);
	}
	
	public Long getTotalRowCount(SearchInput searchInput) {
		return this.userDAO.getTotalRowCount(searchInput);
	}

		
	public void deletePermanently(UserEntity user) {
		this.userDAO.delete(user);
	}
	
	public void delete(UserEntity user) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		User user = null;
		UserEntity userDetails = userDAO.get(emailId);
		if(userDetails != null) {
			List<GrantedAuthority> roles = this.buildUserAuthority(userDetails.getRoles());
			user = this.buildUserForAuthentication(userDetails, roles);
		}
		return user;
	}
	
	private User buildUserForAuthentication(UserEntity user, List<GrantedAuthority> authorities) {
		return new User(user.getEmailID(), user.getPassword(), user.isAccountEnabled(), user.isAccountExpired(), user.isAccountCredentialsExpired(),
				user.isAccountLocked(), authorities);
	}
	
	private List<GrantedAuthority> buildUserAuthority(Set<RoleEntity> roles) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
		// Build user's authorities
		for (RoleEntity userRole : roles) {
			setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
		}
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
		return Result;
	}
}