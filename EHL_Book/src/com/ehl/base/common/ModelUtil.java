package com.ehl.base.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.util.finder.ClassFinder.FieldInfo;



/**
 * ʵ��Ĺ�����
 */
public class ModelUtil {
	public static Map<String, Field[]> modelFields = new HashMap<String, Field[]>();
	public static Map<String, String> modelJson = new HashMap<String, String>();

	/**
	 * �ж�ʵ�岻Ϊ��
	 * 
	 * @param obj
	 * @return
	 */
	public static Boolean isNotNull(Object obj) {
		if (obj != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * �õ�������Լ���
	 * 
	 * @param c
	 * @param itself
	 *            �Ƿ���������ֶ�
	 * @return
	 */
	public static Field[] getClassFields(Class<?> c, boolean itself) {
		if (itself) {
			if (modelFields.get(c.getName()) != null) {
				return modelFields.get(c.getName());
			} else {
				Field[] fields = c.getDeclaredFields();
				modelFields.put(c.getName(), fields);
				return fields;
			}
		} else {
			if (modelFields.get(c.getName()) != null) {
				return modelFields.get(c.getName());
			} else {
				List<Field> fields = new ArrayList<Field>();
				getAllDeclaredFields(c, fields);
				Field[] fies = new Field[fields.size()];
				fields.toArray(fies);
				modelFields.put(c.getName(), fies);
				return fies;
			}
		}
	}

	/**
	 * ��c����ȡ��ȫ���ֶ�,��������
	 * 
	 * @param c
	 * @param fields
	 */
	public static void getAllDeclaredFields(Class<?> c, List<Field> fields) {
		Field[] fies = c.getDeclaredFields();
		Collections.addAll(fields, fies);
		Class<?> parent = c.getSuperclass();
		if (parent != Object.class) {
			getAllDeclaredFields(parent, fields);
		} else {
			return;
		}
	}

	

}
