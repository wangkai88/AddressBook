package com.ehl.addressbook.dao;

import java.util.List;

import com.ehl.addressbook.domain.User;



public interface UserDao extends BaseDao<User, Integer> {
	//验证用户名是否存在
   public boolean checkName(String name,Class entityClass);
   //根据关键字查询用户信息
   public List<User> findByKeyVal(String keyWords,Class entityClass);
}
