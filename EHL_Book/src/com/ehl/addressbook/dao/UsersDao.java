package com.ehl.addressbook.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ehl.addressbook.domain.User;
import com.ehl.base.util.DealSelectTemplate;

/**
 * ”√ªß√˚DAO
 */
public class UsersDao {
	
	@SuppressWarnings({ "null", "rawtypes" })
	public List<User> getkeyVals(String username){
		String sql = "select  t.RYID, t.XM from T_SYS_PERSON t where 1=1 " ;
		if(username!=null&&!"".equals(username)){
			sql +=" and t.XM like '%"+username+"%'";
		}
		List<User> users = new ArrayList();
		User user = null;
		List<Map<String,String>> policeResstatisticsObjs = DealSelectTemplate.selectQueryForListMap
				(sql, null,new User());
		for (int i = 0; i < policeResstatisticsObjs.size(); i++) {
			Map s= (Map)policeResstatisticsObjs.get(i);
//			user.setId(String.valueOf(s.get("RYID")));
			user = new User();
			user.setName(String.valueOf(s.get("XM")));

			System.out.println(String.valueOf(s.get("XM")));;
			users.add(user);
		}
		return users;
	}
}
