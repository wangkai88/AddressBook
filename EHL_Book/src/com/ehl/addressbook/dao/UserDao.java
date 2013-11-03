package com.ehl.addressbook.dao;

import java.util.List;

import com.ehl.addressbook.domain.User;



public interface UserDao extends BaseDao<User, Integer> {
	//��֤�û����Ƿ����
   public boolean checkName(String name,Class entityClass);
   //���ݹؼ��ֲ�ѯ�û���Ϣ
   public List<User> findByKeyVal(String keyWords,Class entityClass);
}
