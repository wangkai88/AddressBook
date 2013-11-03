/*
 * StringUtil.java
 * 字符串操作类
 * linbh 2008-03-14
 */
package com.ehl.base.common;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.lang.String;
import java.lang.reflect.Field;
import java.util.StringTokenizer;


public class StringUtil {
	
    /**
     * @author linbh [2008-03-14].
     * <b>建立一个由指定字符串填充的字符串.</b>
     * @param str 填充字符串.
     * @param len 生成字符串长度

	 * @return 结果字符串.
     */
    public static String fill(String str, int len) {
    	int i;
    	String retval = "";
    	for (i = 0; i < len; i++)
    		retval = retval + str;

    	return retval;
    }
    
    /**
     * @author linbh [2009-02-26].
     * <b>建立一个由指定字符串填充的字符串.</b>
     * @param str 原字符串.
     * @param fillStr 填充字符串.
     * @param len 结果字符串长度
	 * @return 结果字符串.
     */
    public static String lpad(String str, String fillStr, int len) {
    	str = str.trim();
    	String retval = StringUtil.fill(fillStr, len - str.length()) + str;
    	return retval;
    }

    /**
     * @author linbh [2008-03-14].
     * <b>获取当前时间.</b>
     * @param formatStr 日期格式，如"yyyy-MM-dd".
	 * @return 当前时间字符串.
     */
    public static String getCurrDateTime(String formatStr){
    	String strdate=null;
    	Date dNow = new Date();
    	SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
    	strdate=formatter.format(dNow);
    	return strdate;
    }

	/**
	 * @author zhaoy [2009-02-18].
	 * @desc 计算两个时间的差值
	 * @param time1 时间字符串
	 * @param time2 时间字符串
	 * @return 时间差（单位:秒）
	 */
	public static long getTimeInterval(String time1,String time2){
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		long lInterval = 0;
		try {
			java.util.Date date1 = myFormatter.parse(time1);
			java.util.Date date2 = myFormatter.parse(time2);
			lInterval = (Math.abs(date1.getTime() - date2.getTime())) / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return lInterval;
	}

    /**
     * @author linbh [2008-03-14].
     * <b>分割字符串.</b>
     * @param str 源字符串.
     * @param flag 分隔符.
     * @return 分隔后的字符串List.
     */
    public static List divide(String str,String flag){
    	List items = new ArrayList();    	
    	StringTokenizer st = new StringTokenizer(str, flag);
    	while (st.hasMoreTokens()) {
    		String itemstr=(String) st.nextToken();
    		items.add(itemstr);
    	}
    	
    	return items;
    }

    /**
     * @author linbh [2008-03-14].
     * <b>字符串替换（将包含在字符串中某一子串使用其它字符串替换）.</b>
     * @param str 字符串.
     * @param str1 被替换子串 
     * @param str2 替换子串 
	 * @return 结果字符串.
     */
    public static String replace(String str,String str1,String str2) {
    	String retVal = "";
    	String tempStr = str.trim();
    	str1 = str1.trim();
    	str2 = str2.trim();
    	
    	int subLen = str1.length(); //被替换子串长度

    	while (tempStr.indexOf(str1)>0) {
    		int loc = tempStr.indexOf(str1);
    		retVal = retVal + tempStr.substring(0,loc) + str2;
    		tempStr = tempStr.substring(loc+subLen);
    	}
    	retVal = retVal + tempStr;
    	    
    	return retVal;
    }

    /**
     * @author linbh [2009-07-08].
     * <b>忽略大小写字符串替换（将包含在字符串中某一子串使用其它字符串替换）.</b>
     * @param str 字符串.
     * @param str1 被替换子串 
     * @param str2 替换子串 
	 * @return 结果字符串.
     */	
	public static String replaceI(String str,String str1,String str2){
		str = str.trim();
		str1 = str1.trim();
		str2 = str2.trim();
		if (str.length() == 0 || str1.length() == 0 || str2.length() == 0){
			return str;
		}
		
		str = str.replaceAll("(?i)" + str1, str2);
		
		return str;
	}

    /**
     * @author linbh [2008-03-14].
     * <b>置换SQL语句中特殊字符.</b>
     * @param sql SQL语句.
	 * @return SQL字符串.
     */
    public static String convertSql(String sql) {
    	if (sql == null || sql.equals(""))
    	{
    		return "";
    	}
    	sql = sql.replace("＝", "=");
    	sql = sql.replace("％", "%");
    	sql = sql.replace("＋", "+");
    	    
    	return sql;
    }
    
   /**
    * @author LDQ [2008-04-21].
	* @函数说明：把Object数组转化成以指定分隔符隔开的字符串
	* @param srcs-->Object数组
	* @param separator-->分隔符号
	* @return 已指定分隔符分开的字符串
	*/
	public static String array2String(Object[] srcs, String separator) {
	   String s = new String("");
	   if (srcs != null) {
		    for (int i = 0; i < srcs.length; i++) {
		    	if(srcs[i]!=null){
		    		s += srcs[i] + separator;
		    	}else{
		    		s += "" ;
		    	}
		    }
		    return s.substring(0, s.length() - 1);
	   } else {
	    return "";
	   }
	}	
		
    /**
     * @author linbh [2008-07-13].
     * <b>XML特殊字符过滤器.</b>
     * @param filter XML数据.
	 * @return 过滤后的XML数据.
     */
	public static String xmlDataFilter(String filter){

		if (filter == null || filter.equals(""))
		{
			return filter;
		}
		
		filter = filter.replace("&", "&amp;");   //过滤&符号
		filter = filter.replace("<", "&lt;");    //过滤小于号
		filter = filter.replace(">", "&gt;");    //过滤大于号 
		filter = filter.replace("'", "&apos;");  //过滤单引号
		filter = filter.replace("\"", "&quot;"); //过滤双引号
		
		return filter;
	}
	
    /**
     * @author guoll [2008-08-19].
     * @去掉字符串末尾的0
	 * @return 去掉末尾0的字符串.
     */
	public static String removeEndZero(String s){
		
		while(s.endsWith("0")){
			s = s.substring(0, s.length()-1);
		}
		return s;
	}
	
//    /**
//     * @author linbh [2008-09-09].
//     * <b>拆分SQL语句.</b>
//     * @param sql 标准SQL语句.
//	 * @return 拆分后的SQL语句（如：SELECT子句、FROM子句、WHERE子句、GROUP BY子句、ORDER BY子句）.
//     */
//	public static HashMap splitSQL(String sql){
//				
//		String upperSQL = upperCaseSQL(sql);
//		
//		//查找"FROM"子句
//		int iFromLoc = findStr(upperSQL,"FROM");
//		if (iFromLoc > 0){
//			upperSQL = upperSQL.substring(0, iFromLoc) + "^" + upperSQL.substring(iFromLoc);
//		}
//		
//		//查找"WHERE"子句
//		int iwhereLoc = findStr(upperSQL,"WHERE");
//		if (iwhereLoc > 0){
//			upperSQL = upperSQL.substring(0, iwhereLoc) + "^" + upperSQL.substring(iwhereLoc);
//		}
//		
//		//查找"GROUP BY"子句
//		int iGroupLoc = findStr(upperSQL,"GROUP BY");
//		if (iGroupLoc > 0){
//			upperSQL = upperSQL.substring(0, iGroupLoc) + "^" + upperSQL.substring(iGroupLoc);
//		}
//		
//		//查找"ORDER BY"子句
//		int iOrderLoc = findStr(upperSQL,"ORDER BY");
//		if (iOrderLoc > 0){
//			upperSQL = upperSQL.substring(0, iOrderLoc) + "^" + upperSQL.substring(iOrderLoc);
//		}
//		
//		//存入HashMap中
//		HashMap<String,String> map = new HashMap<String,String>();
//		String[] aResult = StringHelper.split(upperSQL,"^");
//		for (int i = 0; i < aResult.length; i++) {
//			if (aResult[i].startsWith("SELECT")){
//				map.put("SELECT", aResult[i]);
//			}
//			
//			if (aResult[i].startsWith("FROM")){
//				map.put("FROM", aResult[i]);
//			}
//			
//			if (aResult[i].startsWith("WHERE")){
//				map.put("WHERE", aResult[i]);
//			}
//			
//			if(aResult[i].startsWith("GROUP")){
//				map.put("GROUP", aResult[i]);
//			}
//			
//			if (aResult[i].startsWith("ORDER")){
//				map.put("ORDER", aResult[i]);
//			}
//		}
//		
//		return map;
//	}

    /**
     * @author linbh [2008-09-09].
     * <b>在主串中查找指定的子串.</b>
     * @param str 主串.
     * @param subStr 子串.
	 * @return 子串在主串中出现的位置.
     */	
	private static int findStr(String str,String subStr){

		int iLoc = 0;
		
		while (true){
			iLoc = str.indexOf(subStr,iLoc);
			if (iLoc > 0){
				boolean isEquals = bracketIsEquals(str.substring(iLoc));
				if (isEquals){
					break;
				}else{
					iLoc += subStr.length();
				}
			}else{
				break;
			}
		}
		return iLoc;
	}

    /**
     * @author linbh [2008-09-09].
     * <b>判断SQL语句中左右括号的数量是否相等.</b>
     * @param str SQL语句.
	 * @return 是否相等（相等为true,不等为false ）.
     */	
	private static boolean bracketIsEquals(String str){
		
		boolean bResult = false;
		String strLBracket = "(";
		String strRBracket = ")";
		
		int iLBcount = (str.length() - str.replace(strLBracket, "").length()) / strLBracket.length();
		int iRBcount = (str.length() - str.replace(strRBracket, "").length()) / strRBracket.length();
		if (iLBcount == iRBcount){
			bResult = true;
		}
		
		return bResult;
	}

    /**
     * @author linbh [2008-09-09].
     * <b>转换SQL语句中关键字为大写（如select、where、from、group by、order by等）.</b>
     * @param str SQL语句.
	 * @return 转换后的SQL语句.
     */	
	private static String upperCaseSQL(String sql){
		String strSql = "";
		if (!sql.equals("")){
			strSql = sql.trim();
			strSql = strSql.replaceAll("(?i)select", "SELECT");
			strSql = strSql.replaceAll("(?i)from", "FROM");
			strSql = strSql.replaceAll("(?i)where", "WHERE");
			strSql = strSql.replaceAll("(?i)group by", "GROUP BY");
			strSql = strSql.replaceAll("(?i)order by", "ORDER BY");
			strSql = strSql.replaceAll("(?i)distinct", "DISTINCT");
			strSql = strSql.replaceAll("(?i)union", "UNION");
			strSql = strSql.replaceAll("(?i)having", "HAVING");
		}
		
		return strSql;
	}
	/**
	 * 判断字符串是否是整数
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isInteger(String number) {
		boolean isNumber = false;
		if (StringUtil.isNotEmpty(number)) {
			isNumber = number.matches("^([1-9]\\d*)|(0)$");
		}
		return isNumber;
	}

	/**
	 * 判断字符串不为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str) {
		boolean isNotEmpty = false;
		if (str != null && !str.trim().equals("")
				&& !str.trim().equalsIgnoreCase("NULL")) {
			isNotEmpty = true;
		}
		return isNotEmpty;
	}

	/**
	 * 判断字符串为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return !isNotEmpty(str);
	}

	/**
	 * 将数组转成SQL认识的字符串 123,432,2312 id in('123','432','2312')
	 * 
	 * @param ids
	 * @return
	 */
	public static String fromArrayToStr(String[] ids) {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < ids.length; i++) {
			str.append("'" + ids[i] + "',");
		}
		if (ids.length > 0) {
			str.deleteCharAt(str.length() - 1);
		}
		return str.toString();
	}
	/**
     * @author linbh [2008-09-09].
     * <b>测试,勿删!</b>
     */
	public static void main(String args[]) {
		//long lll = StringUtil.getTimeInterval("2009-02-01 10:01:00","2009-02-01 11:05:00");
		String str = "LBHHello,Lbh!";
		str = replaceI(str ,"lbh", "WWJ");
//		HashMap hp = splitSQL("Select sysdate fRom dual WHERE 1=1");

		System.out.println(str);
	}
	/**
	 * 
	 *字符null处理为空
	 *@param object
	 *@return String
	 *@author chuanhl
	 *@date Jun 18, 2013
	 *@throws
	 */
	public static String trimStr(Object object){
		return object==null?"":object.toString();
	}
	/**
	 * 
	 * @功能说明：  把对象转换为字符串
	 * @修改者：  luhaiyou
	 */
	public static String objectToString(Object o){
		return o!=null?o.toString():"";
	}
	/**
	 * 
	 * @功能说明：  把所有为null的字符串属性修改为""
	 * @修改者：  luhaiyou
	 */
	public static void encodeBeanStringProperty(Object o){
		Field[] fields = o.getClass().getDeclaredFields();
		try {
			for(Field f : fields){
				f.setAccessible(true);
				if(f.getType()==String.class){
					if(f.get(o)==null){
						f.set(o, "");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	/**
	 * @作者: linbh
	 * @版本号：1.0
	 * @函数说明：获取单页SQL语句.
	 * @参数：String sql SQL语句
	 * @参数：int currPage 当前页号
	 * @参数：int pageSize 页大小
	 * @返回：String perPageSql 单页SQL语句串
	 * @创建日期：2008-3-25
	 */
   public static String getPerPageSql(String sql,int currPage,int pageSize) {
		long beginRow = (currPage-1)*pageSize+1; //起始记录行
		long endRow = currPage*pageSize; //结束记录行
		
		String perPageSql = "SELECT * FROM (SELECT a.*,rownum row_num FROM (" + sql + ")a WHERE rownum<='" + endRow + "')b WHERE b.row_num>='"+beginRow+"'";

		return perPageSql;
	}
}

