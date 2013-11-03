package com.ehl.addressbook.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import com.ehl.addressbook.domain.User;
import com.ehl.base.util.BaseHibernateDao;


/***
 * dao实现层，这里将事务的操作跟关闭session的操作放到了dao层，实际开发中的规范是应该放在事务层， 因为事务处理属于事务
 */
public class UserDaoImpl extends BaseHibernateDao implements UserDao {

	public void deleteObject(User entity) {
		getSession().delete(entity);
	}

	public void deleteObjectById(Integer id) {
		getSession().delete(id);
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllObjects(Class<User> clazz, int from, int size) {
		return (List<User>) getSession().createQuery("from User")
				.setFirstResult((from - 1) * size).setMaxResults(size).list();
	}

	public User getObjectById(Class<User> className, Integer id) {
		return (User) getSession().get(className, id);
	}

	public void saveObject(User entity) {
		getSession().save(entity);
	}

	public void updateObject(User entity) {
		getSession().update(entity);
	}

	public List<User> getAllObjects(Class<User> entityClass) {
		return getSession().createQuery("from User").list();
	}

	public boolean checkName(String name, Class entityClass) {
		boolean flag = false;
		User user = (User) getSession().createCriteria(entityClass)
				.add(Restrictions.eq("name", name)).uniqueResult();
		if (user != null) {
			flag = true;
			return flag;
		}
		return flag;
	}

	public List<User> findByKeyVal(String keyWords, Class entityClass) {
		return getSession().createCriteria(entityClass)
				.add(Restrictions.like("name", keyWords+"%")).list();
	}
}
