package com.ehl.base.util;

import java.util.ArrayList;
import java.util.List;

/***
 * 
 * @author �
 * 
 *         ͨ�÷�ҳ������
 */
public class Pagination<T> extends BaseHibernateDao {

	// ÿҳ��ʾ�ļ�¼��
	private static final int PAGESIZE = 5;
	// ��ҳ��
	private Integer countPage;
	// ��ǰҳ
	private Integer nowPage;
	// �ܼ�¼��
	private Integer countRecond;
	// ��ǰҳ����
	private List<T> entities;

	/**
	 * Ĭ�Ϲ����� ����ʵ���������˽�л����������й���������д�ᱨ��
	 */
	public Pagination() {
	}

	/**
	 * ���ι����� ���������ṩ�÷�ҳ���װ�ķ�ҳ����
	 */
	public Pagination(Class<T> className, int nowPage) {
		this.countRecond = getCountRecord(className);
		this.countPage = this.countRecond % PAGESIZE == 0 ? this.countRecond
				/ PAGESIZE : this.countRecond / PAGESIZE + 1;
		// �Ե�ǰҳ�Ĵ���
		if (nowPage <= 1) {
			this.nowPage = 1;
		} else {
			if (nowPage >= this.countPage) {
				this.nowPage = this.countPage;
			} else {
				this.nowPage = nowPage;
			}
		}
		//���ﴫ��ȥ��ֵһ��Ҫ��ȫ�ֵ�nowPage�����ǲ���nowPage��������һҳ���ж��Ͳ���������
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
	 * �����ݿ��ȡ�ܼ�¼���ķ���
	 * 
	 * @param className
	 * @return ����˵������ֱ�ӷŻ�ֵ��ͨ��������һ�������ķ�ʽ�����Գ����ʱ�򲻻��ٳ��ֿ�ָ���쳣��
	 *         query��uniqueResult������������һ��long���͵�ֵ����������Ҫǿת��Integer�͵ģ�
	 *         ��������Ľ���toString()���������ǿתҪ���������ֻ�ʺ��ü�¼���ٵ������
	 *         ��¼���Ӵ��ʱ�����ֱ�ӽ������ҳ�õ��ı�����������long�͵�
	 */

	public Integer getCountRecord(Class<T> className) {
		int i = 0;
		try {
			// ǧ���ס�����ھۺϺ�������д��from���varǰ���пո�û�л��������var��������ı�����ʱ��
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
	 * ��ҳ����
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
