package com.project.persistence.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.project.persistence.dao.GenericDao;

@SuppressWarnings({"unchecked", "rawtypes"})
public class GenericDaoImpl <T extends Serializable> implements	GenericDao<T>{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	protected Class<T> entity;
	
	protected int maxResults;
	
	protected int actualResult;
	
	protected boolean polarity;
	
	protected Criterion criterion;
	
	public static final String ANSI_GREEN = "\033[32m";
	
	public static final String ANSI_RESET = "\033[0m";
	
	protected GenericDaoImpl(final Class<T> entity){
		this.entity = entity;
		this.actualResult = 0;
	}

	public void setEntity(Class<T> entity) {
		this.entity = entity;
	}
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public List<T> findAll() {
		return (List<T>) getCurrentSession().createQuery("from " + entity.getSimpleName()).list();
	}
	
	@Transactional
	public List<T> findAll(int maxResults) {
		this.actualResult = 0;
		this.maxResults = maxResults;
		Query limitedQuery;
		limitedQuery = getCurrentSession().createQuery("from " + entity.getSimpleName());
		limitedQuery.setFirstResult(actualResult);
		limitedQuery.setMaxResults(this.maxResults);
		polarity = false;
		return (List<T>) limitedQuery.list();
	}
	
	@Transactional
	public void saveOrUpdate(T entity) {
		getCurrentSession().saveOrUpdate(entity);	
	}
	
	@Transactional
    public List<T> findByCriteria(Criterion criterion) {
        Criteria criteria = getCurrentSession().createCriteria(entity);
        criteria.add(criterion);
        return criteria.list();
    }

	@Transactional
	public List<T> findByCriteria(Criterion criterion, int maxResults){
		this.actualResult = 0;
		this.maxResults = maxResults;
		this.criterion = criterion;
		Criteria limitedCriteria;
		limitedCriteria = getCurrentSession().createCriteria(entity);
		limitedCriteria.add(this.criterion);
		limitedCriteria.setFirstResult(this.actualResult);
		limitedCriteria.setMaxResults(this.maxResults);		
		polarity = true;
		List<T> list = limitedCriteria.list();
		return limitedCriteria.list();
	}
	
	@Transactional
	public List getMore() {
		this.actualResult += this.maxResults;
		if(!polarity){
			Query limitedQuery;
			limitedQuery = getCurrentSession().createQuery("from " + entity.getSimpleName());
			limitedQuery.setFirstResult(actualResult);
			limitedQuery.setMaxResults(this.maxResults);
			return (List<T>)  limitedQuery.list();
		}
		else{
			Criteria limitedCriteria;
			limitedCriteria = getCurrentSession().createCriteria(entity);
			limitedCriteria.add(this.criterion);
			limitedCriteria.setFirstResult(this.actualResult);
			limitedCriteria.setMaxResults(this.maxResults);
			return (List<T>) limitedCriteria.list();
		}
	}
	
	@Transactional
	public T getByIndex(int index) {
		Query quer = getCurrentSession().createQuery("from " + entity.getSimpleName());
		quer.setFirstResult(index);
		quer.setMaxResults(1);
		List<T> temp = quer.list();
		return temp.get(0);
	}

	@Transactional
	public int verificarIndex(int index) {
		if(index == -1) return -1;
		Query quer = getCurrentSession().createQuery("from " + entity.getSimpleName());
		List<T> temp = quer.list();
		if(index == temp.size()) return 0;
		return 1;
	}

	@Transactional
	public int getLastIndex() {
		Query quer = getCurrentSession().createQuery("from " + entity.getSimpleName());
		List<T> temp = quer.list();
		return temp.size() - 1;
	}

	
}
