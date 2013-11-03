package com.ehl.base.util;


import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public abstract class PagingUtil extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//==============属性===============
	protected int currPage = 1 ;
	protected int allPage ;
	protected int pageSize = 10 ;
	protected List<Integer> showNumberButtonList = new ArrayList<Integer>() ;
	
	//==============方法===============
	
	public abstract void setCurrList(int currPage,int pageSize);
	
	public abstract void setCurrAllPage(int pageSize);
	
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


