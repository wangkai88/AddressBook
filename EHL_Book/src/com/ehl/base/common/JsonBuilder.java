package com.ehl.base.common;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json���� ������
 */
public class JsonBuilder {
	/**
	 * �õ�JsonBuilderʵ��������
	 * 
	 * @return
	 */
	public static JsonBuilder getInstance() {
		return JsonHolder.JSON_BUILDER;
	}

	/**
	 * ��̬�ڲ���
	 */
	public static class JsonHolder {
		public static final JsonBuilder JSON_BUILDER = new JsonBuilder();
		public static ObjectMapper mapper = new ObjectMapper();
	}
	/**
	 * ��һ������ʵ�������Json���ݸ�ʽ
	 * 
	 * @param obj
	 * @return
	 */
	public String toJson(Object obj) {
		try {
			return JsonHolder.mapper.writeValueAsString(obj);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * ��һ��Json�ַ�����װΪָ�����Ͷ���
	 * 
	 * @param json
	 * @param c
	 * @return
	 */
	public Object fromJson(String json, Class<?> c) {
		json = cleanJson(json);
		try {
			Object obj = JsonHolder.mapper.readValue(json, c);
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * ��һ��JsonArray����ת����һ��List�ļ�ֵ�� [{name:'zsp',value:1},{name:'zsp',value:2}]
	 * 
	 * @param json
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Map> fromJsonArray(String json) {
		json = cleanJson(json);
		List<Map> dataList = (List<Map>) fromJson(json, ArrayList.class);

		return dataList;
	}

	/**
	 * Ϊ�����ɹ�����Json
	 * 
	 * @param strData
	 * @return
	 */
	public String returnSuccessJson(String strData) {
		StringBuffer returnJson = new StringBuffer("{ \"success\" : true, \"obj\" : \"");
		returnJson.append(strData);
		returnJson.append("\"}");
		return returnJson.toString();
	}

	/**
	 * Ϊ����ʧ�ܷ���Json
	 * 
	 * @param strData
	 * @return
	 */
	public String returnFailureJson(String strData) {
		StringBuffer returnJson = new StringBuffer("{ success : false, obj : ");
		returnJson.append(strData);
		returnJson.append("}");
		return returnJson.toString();
	}

	/**
	 * Ϊ��ҳ�б��ṩJson��װ
	 * 
	 * @param count
	 *            ��¼����
	 * @param entities
	 *            ʵ���б�
	 * @param excludes
	 *            ��json����ʱ��Ҫ�ų�����������
	 * @return
	 */
	public String buildObjListToJson(Long count,
			Collection<? extends Object> records, boolean listJson) {
		try {
			StringBuffer pageJson = null;
			// �ж��Ƿ�չʾlist������
			if (listJson) {
				pageJson = new StringBuffer("{totalCount:" + count + ","
						+ "rows" + ":");
			} else {
				pageJson = new StringBuffer("");
			}
			// �������ϵ�json����
			StringWriter w = new StringWriter();
			JsonHolder.mapper.writeValue(w, records);
			pageJson.append(w);
			w.close();

			if (listJson) {
				pageJson.append("}");
			} else {
				pageJson.append("");
			}
			return pageJson.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/**
	 * ��ʽ��Json
	 * 
	 * @param json
	 * @return
	 */
	public String cleanJson(String json) {
		if (StringUtil.isNotEmpty(json)) {
			return json.replaceAll("\n", "");
		} else {
			return "";
		}
	}

	/**
	 * 
	 * @param jsonSql
	 *            [{sql:''},{}] ["asd","asdas"]
	 * @return
	 */
	public String[] jsonSqlToString(String jsonSql) {
		// �õ���������
		Object[] os = JSONArray.fromObject(jsonSql).toArray();
		String[] sqls = new String[os.length];
		for (int i = 0; i < os.length; i++) {
			// ʹ��JSONObject��sql��ȡ��ֵ
			JSONObject k = (JSONObject) os[i];
			String kk = (String) k.get("sql");
			sqls[i] = kk;
		}
		return sqls;
	}

//	/**
//	 * ����list���϶���json����[{},{},{}]
//	 * 
//	 * @param values
//	 * @param excludes��Ҫȥ�����ֶ�����   , �ֶηָ�
//	 * @return
//	 */
//	public String buildList(List<?> values, String excludes) {
//		StringBuffer returnJson = new StringBuffer("[");
//		for (Object obj : values) {
//			// ����json���ö���
//			JsonConfig cfg = new JsonConfig();
//			String[] excluds = excludes.split(",");
//			if (excluds.length > 0) {
//				// �����ų���������
//				cfg.setExcludes(excluds);
//			}
//			// ����ѭ������Ϊ���ԣ�������ѭ��
//			cfg.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
//			JSONObject jsonObject = JSONObject.fromObject(obj, cfg);
//			returnJson.append(jsonObject.toString() + ",");
//		}
//		if (values.size() > 0) {
//			returnJson.deleteCharAt(returnJson.length() - 1);
//		}
//		returnJson.append("]");
//		return returnJson.toString();
//	}

	
	/**
	 * ����list���϶���json����[{},{},{}]
	 * 
	 * @param values
	 * @param excludes��Ҫȥ�����ֶ�����   , �ֶηָ�
	 * @return
	 */
	public String buildList(List<?> values, String excludes) {
		StringBuffer returnJson = new StringBuffer("[");
		//excludes="pdaCount,deptId";
		String[] excluds = excludes.split(",");
		for (Object obj : values) {			
			JSONObject jsonObject = JSONObject.fromObject(obj);
//			 �����ų���������
			if(excluds!=null&&excluds.length>0){
				for(int n=0;n<excluds.length;n++){
					jsonObject.remove(excluds[n]);
				}	
			}
			returnJson.append(jsonObject.toString() + ",");
		}
		if (values.size() > 0) {
			returnJson.deleteCharAt(returnJson.length() - 1);
		}
		returnJson.append("]");
		return returnJson.toString();
	}
	
	/**
	 * ����map���϶���json����[{},{},{}]
	 * 
	 * @param values
	 * @param excludes��Ҫȥ�����ֶ�����   , �ֶηָ�
	 * @return
	 */
	public String buildMap(Map<?,List<?>> mapValues, String excludes) {
		Iterator<?> it = mapValues.keySet().iterator();
		StringBuffer returnJson = new StringBuffer("{");
        while (it.hasNext()) {
            String key = it.next().toString();
            List<?> list = mapValues.get(key);
            if(null!=list){
                returnJson.append("\""+key+"\""+":"+buildList(list, excludes) + ",");
            }
        }
        returnJson.deleteCharAt(returnJson.length()-1);
        returnJson.append("}");
		return returnJson.toString();
	}


}
