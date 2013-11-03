package com.ehl.base.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.sun.org.apache.bcel.internal.generic.NEW;


public class DealSelectTemplate {

	/**
	 * 查询返回结果有一个值，基本数据类型
	 * 
	 * @param sql
	 * @param values
	 * @return
	 */
	public static Object selectQueryForObject(String sql, Object[] values) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					ps.setObject(i + 1, values[i]);
				}
			}
			rs = ps.executeQuery();
			rs.next();
			return rs.getObject(1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionUtil.closeConnection(rs, ps, conn);
		}
		return null;
	}

	/**
	 * 返回一行结果的查询，封装约定：结果中列的名字必须等于javaBean中属性的名字。
	 *  obj 表示把查询结果封装成一个对象
	 */
	public static Object selectQueryForbean(String sql, Object[] values,
			Object obj) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					ps.setObject(i + 1, values[i]);
				}
			}
			rs = ps.executeQuery();
			if (rs.next()) {// 判断结果中有哪些列
				ResultSetMetaData metaData = rs.getMetaData();
				for (int i = 1; i<=metaData.getColumnCount(); i++) {
					
					String colunmName = metaData.getColumnName(i);// 获得列名
					colunmName=colunmName.toLowerCase();
					Object colunmValue = rs.getObject(colunmName);// 获取该列的值
					BeanUtils.setProperty(obj, colunmName, colunmValue);//给javabean设置属性的值

				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionUtil.closeConnection(rs,ps, conn);
		}

		return obj;
	}
	
	
	/**
	 *返回多行
	 * @return 
	 */
	
	public static List<? extends Object> selectQueryForList(String sql, Object[] values,Object bean) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Object> list=new ArrayList<Object>();  
		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					ps.setObject(i + 1, values[i]);
				}
			}
			rs = ps.executeQuery();
			while(rs.next()) {// 判断结果中有哪些列
				ResultSetMetaData metaData = rs.getMetaData();
				list.add(bean);
				for (int i = 1; i<=metaData.getColumnCount(); i++) {
					String colunmName = metaData.getColumnName(i);// 获得列名
					Object colunmValue = rs.getObject(colunmName);// 获取该列的值
					BeanUtils.setProperty(bean, colunmName, colunmValue);//给javabean设置属性的值
                    
				}
				bean=bean.getClass().newInstance();
			}
		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionUtil.closeConnection(rs,ps, conn);
		}

		return list;
	}
	/**
	 *返回多行
	 * @return map
	 */
	
	public static List<Map<String,String>> selectQueryForListMap(String sql, Object[] values,Object bean) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();  
		Map<String,String> mapbean=null;
		try {
			conn = ConnectionUtil.getConnection();
			ps = conn.prepareStatement(sql);
			if (values != null) {
				for (int i = 0; i < values.length; i++) {
					ps.setObject(i + 1, values[i]);
				}
			}
			rs = ps.executeQuery();
			while(rs.next()) {// 判断结果中有哪些列
				ResultSetMetaData metaData = rs.getMetaData();
				mapbean = new HashMap<String, String>();
				for (int i = 1; i<=metaData.getColumnCount(); i++) {
					String colunmName = metaData.getColumnName(i);//¥¥ 获得列名
					Object colunmValue = rs.getObject(colunmName);// 获取该列的值
					mapbean.put(colunmName, String.valueOf(colunmValue));
				}
				list.add(mapbean);
			}
		

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			ConnectionUtil.closeConnection(rs,ps, conn);
		}

		return list;
	}
	/**
	 * 处理增，删，改
	 */
	public static void executeSql(String sql,Object[] values){
		Connection conn =null;
		PreparedStatement ps =null;
		try{
			conn =ConnectionUtil.getConnection();
			ps=conn.prepareStatement(sql);
			if(values!=null){
				for (int i = 0; i < values.length; i++) {
					ps.setObject(i+1, values[i]);
				}
			}
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			ConnectionUtil.closeConnection(null, ps, conn);
		}
	}
	
	
	
}
