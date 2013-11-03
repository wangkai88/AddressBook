package com.ehl.addressbook.service;

import java.util.List;

import com.ehl.addressbook.domain.User;


public interface UserService extends BaseService<User, Integer> {
	public boolean checkName(String name,Class entityClass);
	 public List<User> findByKeyVal(String keyWords,Class entityClass);
}
