/*
 * StringUtil.java
 * �ַ���������
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
     * <b>����һ����ָ���ַ��������ַ���.</b>
     * @param str ����ַ���.
     * @param len �����ַ�������

	 * @return ����ַ���.
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
     * <b>����һ����ָ���ַ��������ַ���.</b>
     * @param str ԭ�ַ���.
     * @param fillStr ����ַ���.
     * @param len ����ַ�������
	 * @return ����ַ���.
     */
    public static String lpad(String str, String fillStr, int len) {
    	str = str.trim();
    	String retval = StringUtil.fill(fillStr, len - str.length()) + str;
    	return retval;
    }

    /**
     * @author linbh [2008-03-14].
     * <b>��ȡ��ǰʱ��.</b>
     * @param formatStr ���ڸ�ʽ����"yyyy-MM-dd".
	 * @return ��ǰʱ���ַ���.
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
	 * @desc ��������ʱ��Ĳ�ֵ
	 * @param time1 ʱ���ַ���
	 * @param time2 ʱ���ַ���
	 * @return ʱ����λ:�룩
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
     * <b>�ָ��ַ���.</b>
     * @param str Դ�ַ���.
     * @param flag �ָ���.
     * @return �ָ�����ַ���List.
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
     * <b>�ַ����滻�����������ַ�����ĳһ�Ӵ�ʹ�������ַ����滻��.</b>
     * @param str �ַ���.
     * @param str1 ���滻�Ӵ� 
     * @param str2 �滻�Ӵ� 
	 * @return ����ַ���.
     */
    public static String replace(String str,String str1,String str2) {
    	String retVal = "";
    	String tempStr = str.trim();
    	str1 = str1.trim();
    	str2 = str2.trim();
    	
    	int subLen = str1.length(); //���滻�Ӵ�����

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
     * <b>���Դ�Сд�ַ����滻�����������ַ�����ĳһ�Ӵ�ʹ�������ַ����滻��.</b>
     * @param str �ַ���.
     * @param str1 ���滻�Ӵ� 
     * @param str2 �滻�Ӵ� 
	 * @return ����ַ���.
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
     * <b>�û�SQL����������ַ�.</b>
     * @param sql SQL���.
	 * @return SQL�ַ���.
     */
    public static String convertSql(String sql) {
    	if (sql == null || sql.equals(""))
    	{
    		return "";
    	}
    	sql = sql.replace("��", "=");
    	sql = sql.replace("��", "%");
    	sql = sql.replace("��", "+");
    	    
    	return sql;
    }
    
   /**
    * @author LDQ [2008-04-21].
	* @����˵������Object����ת������ָ���ָ����������ַ���
	* @param srcs-->Object����
	* @param separator-->�ָ�����
	* @return ��ָ���ָ����ֿ����ַ���
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
     * <b>XML�����ַ�������.</b>
     * @param filter XML����.
	 * @return ���˺��XML����.
     */
	public static String xmlDataFilter(String filter){

		if (filter == null || filter.equals(""))
		{
			return filter;
		}
		
		filter = filter.replace("&", "&amp;");   //����&����
		filter = filter.replace("<", "&lt;");    //����С�ں�
		filter = filter.replace(">", "&gt;");    //���˴��ں� 
		filter = filter.replace("'", "&apos;");  //���˵�����
		filter = filter.replace("\"", "&quot;"); //����˫����
		
		return filter;
	}
	
    /**
     * @author guoll [2008-08-19].
     * @ȥ���ַ���ĩβ��0
	 * @return ȥ��ĩβ0���ַ���.
     */
	public static String removeEndZero(String s){
		
		while(s.endsWith("0")){
			s = s.substring(0, s.length()-1);
		}
		return s;
	}
	
//    /**
//     * @author linbh [2008-09-09].
//     * <b>���SQL���.</b>
//     * @param sql ��׼SQL���.
//	 * @return ��ֺ��SQL��䣨�磺SELECT�Ӿ䡢FROM�Ӿ䡢WHERE�Ӿ䡢GROUP BY�Ӿ䡢ORDER BY�Ӿ䣩.
//     */
//	public static HashMap splitSQL(String sql){
//				
//		String upperSQL = upperCaseSQL(sql);
//		
//		//����"FROM"�Ӿ�
//		int iFromLoc = findStr(upperSQL,"FROM");
//		if (iFromLoc > 0){
//			upperSQL = upperSQL.substring(0, iFromLoc) + "^" + upperSQL.substring(iFromLoc);
//		}
//		
//		//����"WHERE"�Ӿ�
//		int iwhereLoc = findStr(upperSQL,"WHERE");
//		if (iwhereLoc > 0){
//			upperSQL = upperSQL.substring(0, iwhereLoc) + "^" + upperSQL.substring(iwhereLoc);
//		}
//		
//		//����"GROUP BY"�Ӿ�
//		int iGroupLoc = findStr(upperSQL,"GROUP BY");
//		if (iGroupLoc > 0){
//			upperSQL = upperSQL.substring(0, iGroupLoc) + "^" + upperSQL.substring(iGroupLoc);
//		}
//		
//		//����"ORDER BY"�Ӿ�
//		int iOrderLoc = findStr(upperSQL,"ORDER BY");
//		if (iOrderLoc > 0){
//			upperSQL = upperSQL.substring(0, iOrderLoc) + "^" + upperSQL.substring(iOrderLoc);
//		}
//		
//		//����HashMap��
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
     * <b>�������в���ָ�����Ӵ�.</b>
     * @param str ����.
     * @param subStr �Ӵ�.
	 * @return �Ӵ��������г��ֵ�λ��.
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
     * <b>�ж�SQL������������ŵ������Ƿ����.</b>
     * @param str SQL���.
	 * @return �Ƿ���ȣ����Ϊtrue,����Ϊfalse ��.
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
     * <b>ת��SQL����йؼ���Ϊ��д����select��where��from��group by��order by�ȣ�.</b>
     * @param str SQL���.
	 * @return ת�����SQL���.
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
	 * �ж��ַ����Ƿ�������
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
	 * �ж��ַ�����Ϊ��
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
	 * �ж��ַ���Ϊ��
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return !isNotEmpty(str);
	}

	/**
	 * ������ת��SQL��ʶ���ַ��� 123,432,2312 id in('123','432','2312')
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
     * <b>����,��ɾ!</b>
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
	 *�ַ�null����Ϊ��
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
	 * @����˵����  �Ѷ���ת��Ϊ�ַ���
	 * @�޸��ߣ�  luhaiyou
	 */
	public static String objectToString(Object o){
		return o!=null?o.toString():"";
	}
	/**
	 * 
	 * @����˵����  ������Ϊnull���ַ��������޸�Ϊ""
	 * @�޸��ߣ�  luhaiyou
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
	 * @����: linbh
	 * @�汾�ţ�1.0
	 * @����˵������ȡ��ҳSQL���.
	 * @������String sql SQL���
	 * @������int currPage ��ǰҳ��
	 * @������int pageSize ҳ��С
	 * @���أ�String perPageSql ��ҳSQL��䴮
	 * @�������ڣ�2008-3-25
	 */
   public static String getPerPageSql(String sql,int currPage,int pageSize) {
		long beginRow = (currPage-1)*pageSize+1; //��ʼ��¼��
		long endRow = currPage*pageSize; //������¼��
		
		String perPageSql = "SELECT * FROM (SELECT a.*,rownum row_num FROM (" + sql + ")a WHERE rownum<='" + endRow + "')b WHERE b.row_num>='"+beginRow+"'";

		return perPageSql;
	}
}

