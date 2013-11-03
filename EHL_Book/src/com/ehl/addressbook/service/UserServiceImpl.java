package com.ehl.addressbook.service;

import java.util.List;

import org.hibernate.Transaction;

import com.ehl.addressbook.dao.UserDao;
import com.ehl.addressbook.dao.UserDaoImpl;
import com.ehl.addressbook.domain.User;
import com.ehl.base.util.BaseHibernateDao;
import com.ehl.base.util.HibernateSessionFactory;


public class UserServiceImpl extends BaseHibernateDao implements UserService {

	private UserDao userDao = new UserDaoImpl();

	public void deleteObject(User entity) {
		Transaction tx = null;
		try {
			tx = getSession().beginTransaction();
			userDao.deleteObject(entity);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException("删除所有错误" + e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public void deleteObjectById(Integer id) {
		Transaction tx = null;
		try {
			tx = getSession().beginTransaction();
			userDao.deleteObjectById(id);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException("根据id错误" + e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	/*
	 * public List<User> getAllObjects(Class<User> clazz, int from, int size) {
	 * try { return userDao.getAllObjects(clazz, from, size); } catch (Exception
	 * e) { throw new RuntimeException("查找错误" + e); } finally {
	 * HibernateSessionFactory.closeSession(); } }
	 */

	public User getObjectById(Class<User> className, Integer id) {
		try {
			return userDao.getObjectById(className, id);
		} catch (Exception e) {
			throw new RuntimeException("根据id查找错误" + e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public void saveObject(User entity) {
		Transaction tx = null;
		try {
			tx = getSession().beginTransaction();
			userDao.saveObject(entity);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();

			throw new RuntimeException("保存错误" + e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public void updateObject(User entity) {
		Transaction tx = null;
		try {
			tx = getSession().beginTransaction();
			userDao.updateObject(entity);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			throw new RuntimeException("更新错误" + e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public List<User> getAllObjects(Class<User> entityClass) {
		try {
			return userDao.getAllObjects(entityClass);
		} catch (Exception e) {
			throw new RuntimeException("查找错误" + e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public boolean checkName(String name, Class entityClass) {
		
		try {			
			return userDao.checkName(name, entityClass);
		} catch (Exception e) {
			throw new RuntimeException("根据name错误" + e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}

	public List<User> findByKeyVal(String keyWords, Class entityClass) {
		try {
			return userDao.findByKeyVal(keyWords, entityClass);
		} catch (Exception e) {
			throw new RuntimeException("根据id查找错误" + e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
	}
}
