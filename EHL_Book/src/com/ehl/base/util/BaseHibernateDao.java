package com.ehl.base.util;

import org.hibernate.Session;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDao implements IBaseHibernateDao {
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
}