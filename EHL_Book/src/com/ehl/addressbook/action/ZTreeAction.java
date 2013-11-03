/*
 * $filename: ZTreeDemoAction.java,v $
 * $Date: Sep 27, 2013  $
 * Copyright (C) ZhengHaibo, Inc. All rights reserved.
 * This software is Made by Zhenghaibo.
 */
package com.ehl.addressbook.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;


/*
 *@author: ZhengHaibo  
 *web:     http://blog.csdn.net/nuptboyzhb
 *mail:    zhb931706659@126.com
 *Sep 27, 2013  Nanjing,njupt,China
 */
public class ZTreeAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3318989776253565435L;
    
	private int treeNodeId;//保存树节点的Id
	/**
	 * 返回树的根节点的Json格式数据
	 * @return
	 */
	public String getNodesDataById(){
		System.out.println("treeNodeId = "+treeNodeId);
		String iconPath = ",icon:\"zTree/css/zTreeStyle/img/diy/3.png\"";
		String nodesJson = "";
		if (treeNodeId == 1) {
			//nodesJson = "[{id:11, pId:"+treeNodeId+", name: \"廊坊市交警支队\",isParent:false"+iconPath+"}," +
			nodesJson = "{id:12, pId:"+2+", name: \"一大队\",isParent:false"+iconPath+"}," +
            "{id:13, pId:"+2+", name: \"二大队\",isParent:false"+iconPath+"}" +
            "{id:14, pId:"+2+", name: \"三大队\",isParent:false"+iconPath+"}" +
            "]";
		}else{
			nodesJson = "[]";//其他情况：父节点没有子节点
		}
		getPrintWriter().write(nodesJson);
		System.out.println(nodesJson);
		return SUCCESS;
	}
	
	/**
	 * 获得HttpServletResponse对象
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8;");
		return response;
	}
	
	public PrintWriter getPrintWriter() {
		PrintWriter pw = null;
		try {
			pw = getResponse().getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pw;
	}

	public int getTreeNodeId() {
		return treeNodeId;
	}

	public void setTreeNodeId(int treeNodeId) {
		this.treeNodeId = treeNodeId;
	}
}
