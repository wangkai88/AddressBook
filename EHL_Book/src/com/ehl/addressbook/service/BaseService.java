package com.ehl.addressbook.service;

import java.util.List;

public interface BaseService<T,PK> {

	/**
	 * ��������ʵ��
	 * 
	 * @param entityClass
	 * @return
	 */
	public List<T> getAllObjects(Class<T> entityClass);

	/**
	 * ��id����ʵ��
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public T getObjectById(Class<T> entityClass, PK id);


	/**
	 * ����ʵ��
	 * 
	 * @param entity
	 */
	public void saveObject(T entity);

	/**
	 * ɾ��ʵ��
	 * 
	 * @param entity
	 */
	public void deleteObject(T entity);

	/**
	 * ��idɾ��ʵ��
	 * 
	 * @param id
	 */
	public void deleteObjectById(PK id);

	/**
	 * ����ʵ��
	 * 
	 * @param entity
	 */
	public void updateObject(T entity);
}
