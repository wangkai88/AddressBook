package com.ehl.addressbook.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ehl.base.util.DealSelectTemplate;
import com.sun.crypto.provider.RSACipher;

public class Test {

	/**
	 * 测试一下查询
	 * @param args
	 */
	public static void main(String[] args) {
		//测试只有一个查询结果
//		System.out.println(DealSelectTemplate.selectQueryForObject
//				("select name from student where id=98 ",null));
//		
		/*Student s =new Student();
		DealSelectTemplate.selectQueryForbean
		("select * from student where id=?", new Object[]{98}, s);
		System.out.println(s.getId());
		System.out.println(s.getName());*/
	/*	
		Sort s1=new Sort();
		DealSelectTemplate.selectQueryForbean
		("select * from answer where q_id=?", new Object[]{4}, s1);
		System.out.println(s1.getAn_id());
		System.out.println(s1.getContent());
		System.out.println(s1.getQ_id());*/
		
		/*Sort s1=new Sort();
		DealSelectTemplate dsp=new DealSelectTemplate();
		
		List list=	DealSelectTemplate.selectQueryForList
		("select * from answer where q_id=?", new Object[]{2}, s1);
		
		for (int i = 0; i < list.size(); i++) {
			 Sort s= (Sort)list.get(i);
             //System.out.println(str);

			 System.out.println(s.getAn_id());
			 System.out.println(s.getContent());
			 System.out.println(s.getQ_id());
			 
			
		}
		*/	
		
//		DealSelectTemplate.executeSql
//		("insert into answer values(9,'jj',5);",null);

//		String sql = "select distinct t.regionname from T_SITUATION_SIGN t";
		String sql = "select t.deptid,t.deptname,t.allcount,t.gpscount,t.pdacount from T_SITUATION_GPSRESSTATISTICS t where 1=1 order by t.deptid";
//		List policeResstatisticsObjs = DealSelectTemplate.selectQueryForList
//				(sql,null,new PoliceResstatisticsObj());
//		for (int i = 0; i < policeResstatisticsObjs.size(); i++) {
//			PoliceResstatisticsObj s= (PoliceResstatisticsObj)policeResstatisticsObjs.get(i);
//			System.out.println(s.getDeptName());;
//			 
//			
//		}
		//测试只有一个查询结果
//		for(PoliceResstatisticsObj obj :policeResstatisticsObjs){
//
//			System.out.println(obj.getDeptName());
//		}
//		String sqlobj = "select t.signtype,t.signnum from T_SITUATION_SIGN t";
//		sqlobj+=" where t.regionname='"+"一大队"+"' order by t.signtype";
//		List<SignObj> signObjs = new ArrayList<SignObj>();
//		signObjs = DealSelectTemplate.selectQueryForList
//		(sqlobj,null,new SignObj());
//		for(SignObj obj :signObjs){
//
//			System.out.println(obj.getValue());
//		}
		}
}
