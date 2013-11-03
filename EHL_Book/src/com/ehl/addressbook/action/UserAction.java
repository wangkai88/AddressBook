package com.ehl.addressbook.action;

import java.util.List;


import com.ehl.addressbook.domain.User;
import com.ehl.addressbook.service.UserService;
import com.ehl.addressbook.service.UserServiceImpl;
import com.ehl.base.action.BaseAction;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends BaseAction {

	// 判断用户名称后发回的信息
	private String msg;
	// ajax请求发过来的那么名称
	private String name;
	//关键字
	private String keyWord;
	private List<User> users;
	
	private UserService service = new UserServiceImpl();
	
	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String checkName() {
		boolean flag = service.checkName(name, User.class);
		if (flag) {
			msg = "用户名已经存在";
		} else {
			msg = "用户名可以使用";
		}
		return "checkName";
	}
	public String keyVals() {
//		users = service.findByKeyVal(keyWord, User.class);
		String keyWord = request.getParameter("keyWord");
		System.out.println(keyWord);
		System.out.println(users);
		return "keyVals";
	}
	@Override
	public void setCurrList(int curPage, int pageSize) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCurrAllPage(int pageSize) {
		// TODO Auto-generated method stub
		allPage = 10 ;
		this.fillShowNumber() ;
	}
	
	
	public String go(){
		this.setCurrAllPage(pageSize) ;
		return SUCCESS ;
	}
}
