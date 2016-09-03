package com.nanites.theta.business.util;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.nanites.theta.business.type.bo.user.Roles;

public class CommonUtil {
	protected static AtomicReference<Long> currentTime = new AtomicReference<>(System.currentTimeMillis());

	/**
	 * will fetch unique long value based on current time. to avoid duplicates,
	 * AtomicReference is used which provides volatile behaviour for counter.
	 */
	public static Long nextRegNo() {
		Long prev;
		Long next = System.currentTimeMillis();
		do {
			prev = currentTime.get();
			/*
			 * we need this, as its likely program executes so fast that, prev
			 * and next both might have the same value.
			 */
			next = next > prev ? next : prev + 1;
		}
		/*
		 * each time we fetch 1 new ID, we update its value in currentTime. And
		 * we take reference of it to make sure, we are threadsafe.
		 * 
		 * compareAndSet will provide a check against stale values. i.e. while
		 * updating currentTime's values, if existing copied value of
		 * currentTime isnt same as the current value of currentTime, some other
		 * thread already have taken the nextID.
		 */
		while (!currentTime.compareAndSet(prev, next));
		return next;
	}

	public void populateRoles() {

	}

	public static boolean isAdmin(Collection<SimpleGrantedAuthority> authorities) {
		boolean isAdmin = false;
		for (SimpleGrantedAuthority authority : authorities) {
			if (authority.getAuthority().equals(Roles.ADMIN.getName())) {
				isAdmin = true;
			}
		}
		return isAdmin;
	}

	public static boolean isAdmin() {
		boolean isAdmin = false;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			@SuppressWarnings("unchecked")
			Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) auth.getAuthorities();
			for (SimpleGrantedAuthority authority : authorities) {
				if (authority.getAuthority().equals(Roles.ADMIN.getName())) {
					isAdmin = true;
				}
			}
		}
		return isAdmin;
	}

	public static boolean isAuth(Authentication auth) {
		boolean isAuth = false;
		if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
			isAuth = true;
		}
		return isAuth;
	}

	public static boolean isAuth() {
		boolean isAuth = false;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
			isAuth = true;
		}
		return isAuth;
	}

	public static long calculateNoOfPages(long rowCount, long rowPerPage) {
		long pageCount = 0;
		if (rowCount != 0 && rowPerPage != 0) {
			pageCount = rowCount / rowPerPage;
			if (rowCount % rowPerPage != 0) {
				pageCount++;
			}
		}
		return pageCount;
	}
}
