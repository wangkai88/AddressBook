package com.ehl.base.util;


public class AllPagesUtil {

	public static int getPages(int allLine,int pageSize){
		try{
			return (allLine-1)/pageSize+1 ;
		}catch(Exception e){
			System.out.println("pageSize²»ÄÜÎªÁã"); ;
		}
		return 1;
	}
}
