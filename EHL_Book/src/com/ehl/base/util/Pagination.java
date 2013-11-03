package com.ehl.base.util;

import java.util.ArrayList;
import java.util.List;

/***
 * 
 * @author 杨凯
 * 
 *         通用分页工具类
 */
public class Pagination<T> extends BaseHibernateDao {

	// 每页显示的记录数
	private static final int PAGESIZE = 5;
	// 总页数
	private Integer countPage;
	// 当前页
	private Integer nowPage;
	// 总记录数
	private Integer countRecond;
	// 当前页数据
	private List<T> entities;

	/**
	 * 默认构造器 用来实例化改类的私有化变量，必有构造器，不写会报错
	 */
	public Pagination() {
	}

	/**
	 * 传参构造器 用来对外提供该分页类封装的分页方法
	 */
	public Pagination(Class<T> className, int nowPage) {
		this.countRecond = getCountRecord(className);
		this.countPage = this.countRecond % PAGESIZE == 0 ? this.countRecond
				/ PAGESIZE : this.countRecond / PAGESIZE + 1;
		// 对当前页的处理
		if (nowPage <= 1) {
			this.nowPage = 1;
		} else {
			if (nowPage >= this.countPage) {
				this.nowPage = this.countPage;
			} else {
				this.nowPage = nowPage;
			}
		}
		//这里传过去的值一定要是全局的nowPage不能是参数nowPage，否则下一页的判读就不起作用了
		this.entities = getNowPageInfor(this.nowPage, className);
	}

	public void setCountPage(Integer countPage) {
		this.countPage = countPage;
	}

	public void setNowPage(Integer nowPage) {
		this.nowPage = nowPage;
	}

	public void setCountRecond(Integer countRecond) {
		this.countRecond = countRecond;
	}

	public void setEntities(List<T> entities) {
		this.entities = entities;
	}

	public Integer getCountPage() {
		return countPage;
	}

	public Integer getNowPage() {
		return nowPage;
	}

	public Integer getCountRecond() {
		return countRecond;
	}

	public List<T> getEntities() {
		return entities;
	}

	/**
	 * 从数据库获取总记录数的方法
	 * 
	 * @param className
	 * @return 方法说明：不直接放回值，通过先声明一个变量的方式，测试出错的时候不会再出现空指针异常；
	 *         query的uniqueResult（）方法返回一个long类型的值，所以这里要强转成Integer型的；
	 *         这里巧妙的借助toString()方法来完成强转要求，这个方法只适合用记录较少的情况；
	 *         记录数庞大的时候可以直接将上面分页用到的变量都声明成long型的
	 */

	public Integer getCountRecord(Class<T> className) {
		int i = 0;
		try {
			// 千万记住引号内聚合函数的书写，from后跟var前都有空格，没有会出错；这里var是随意起的变量临时名
			i = Integer.parseInt(this
					.getSession()
					.createQuery(
							"select count(var) from " + className.getName()
									+ " var").uniqueResult().toString());
			i = Integer.parseInt(this
					.getSession()
					.createQuery(
							"select count(var) from " + className.getName()
									+ " var").uniqueResult().toString());
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return i;
	}

	/**
	 * 
	 * 分页方法
	 * 
	 * @param nowPage
	 * @param className
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<T> getNowPageInfor(Integer nowPage, Class<T> className) {
		List<T> entities = new ArrayList<T>();
		try {
			entities = this.getSession().createCriteria(className)
					.setFirstResult((nowPage - 1) * PAGESIZE)
					.setMaxResults(PAGESIZE).list();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			HibernateSessionFactory.closeSession();
		}
		return entities;
	}
}
