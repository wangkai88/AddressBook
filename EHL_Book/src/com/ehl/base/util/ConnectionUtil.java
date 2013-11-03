package com.ehl.base.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



public class ConnectionUtil {
	
private static Properties config;//配置文件

private static Properties getProperties(){
	if(config ==null){
		config =new Properties();
		try {
			config.load(ConnectionUtil.class.getClassLoader().getResourceAsStream("jdbc.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return config;
}
/**
 * 创建数据库的连接
 */
public static Connection  getConnection() throws Exception{
	String driverClassName=getProperties().getProperty("jdbc.driver");
	String url=config.getProperty("jdbc.url");
	Class.forName(driverClassName);
	return DriverManager.getConnection(url,
				config.getProperty("jdbc.username"),config.getProperty("jdbc.password"));
	
	
}
/**
 * 关闭连接
 */

public static void closeConnection(ResultSet rs,Statement st,Connection conn ){
	if(rs!=null){
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(st!=null){}
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
}

}
