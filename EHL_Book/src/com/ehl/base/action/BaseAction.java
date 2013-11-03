package com.ehl.base.action;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.ehl.base.common.JsonBuilder;
import com.ehl.base.common.ModelUtil;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 通用的基础Action类封装
 */
public abstract class BaseAction extends ActionSupport implements ServletResponseAware, ServletRequestAware,
					Serializable {
	private static final long serialVersionUID = 1L;
	//==============属性===============
	protected int currPage = 1 ;
	protected int allPage ;
	protected int pageSize = 10 ;
	protected List<Integer> showNumberButtonList = new ArrayList<Integer>() ;
	
	//==============方法===============
	
	public abstract void setCurrList(int currPage,int pageSize);
	
	public abstract void setCurrAllPage(int pageSize);
	
	/** 日志输出对象 */
	private static Logger logger = Logger.getLogger(BaseAction.class);
	/** 请求对象 */
	protected HttpServletRequest request;
	/** 返回对象 */
	protected HttpServletResponse response;
	/** 项目部署的WebRoot路径 */
	public static String webrootAbsPath;
	/** 项目部署的class路径 */
	public static String absClassPath;
	/** Json工具类 */
	public static JsonBuilder jsonBuilder;
	/** 为了json排除的字段 */
	public String excludes = ""; // checked
	static {
		jsonBuilder = JsonBuilder.getInstance();
	}
	public void fillShowNumber(){
		if(currPage>2&&currPage<allPage-2){
			showNumberButtonList.add(currPage-1) ;
			showNumberButtonList.add(currPage) ;
			showNumberButtonList.add(currPage+1) ;
			showNumberButtonList.add(currPage+2) ;
		}else if(currPage>=allPage-2){
			showNumberButtonList.add(allPage-3) ;
			showNumberButtonList.add(allPage-2) ;
			showNumberButtonList.add(allPage-1) ;
			showNumberButtonList.add(allPage) ;
			
		}else{
			showNumberButtonList.add(1) ;
			showNumberButtonList.add(2) ;
			showNumberButtonList.add(3) ;
			showNumberButtonList.add(4) ;
		}
	}

	
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}

	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	protected void toWrite(String contents) {
		if (ModelUtil.isNotNull(response)) {
			response.setContentType("application/json;charset=UTF-8;");
			response.setHeader("Cache-Control", "no-cache");
			Writer writer = null;
			try {
				response.setCharacterEncoding("UTF-8");
				writer = response.getWriter();
				writer.write(contents);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					writer.flush();
					writer.close();
					response.flushBuffer();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	protected void toWriteXml(String contents) {
		if (ModelUtil.isNotNull(response)) {
			response.setContentType("text/xml;charset=UTF-8;");
			response.setHeader("Cache-Control", "no-cache");
			Writer writer = null;
			try {
				response.setCharacterEncoding("UTF-8");
				writer = response.getWriter();
				writer.write(contents);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					writer.flush();
					writer.close();
					response.flushBuffer();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	
	//==============get set 方法============
	
	
	public int getCurrPage() {
		return currPage;
	}
	
	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	
	public int getAllPage() {
		return allPage;
	}
	
	public void setAllPage(int allPage) {
		this.allPage = allPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public List<Integer> getShowNumberButtonList() {
		return showNumberButtonList;
	}
	
	public void setShowNumberButtonList(List<Integer> showNumberButtonList) {
		this.showNumberButtonList = showNumberButtonList;
	}
}
