package com.ehl.addressbook.dao;

import java.util.List;

public interface BaseDao<T, PK> {

	/**
	 * 查找所有实体
	 * 
	 * @param entityClass
	 * @return
	 */
	public List<T> getAllObjects(Class<T> entityClass);

	/**
	 * 按id查找实体
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 */
	public T getObjectById(Class<T> entityClass, PK id);


	/**
	 * 保存实体
	 * 
	 * @param entity
	 */
	public void saveObject(T entity);

	/**
	 * 删除实体
	 * 
	 * @param entity
	 */
	public void deleteObject(T entity);

	/**
	 * 按id删除实体
	 * 
	 * @param id
	 */
	public void deleteObjectById(PK id);

	/**
	 * 更新实体
	 * 
	 * @param entity
	 */
	public void updateObject(T entity);
}
