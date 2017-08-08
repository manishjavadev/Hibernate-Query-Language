package com.manish.javadev.manager;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.manish.javadev.model.Employee;
import com.manish.javadev.util.HibernateUtil;

public class LoadManagerForCriteria {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		/**
		 * ===========How to use Like With Criteria===========
		 */
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.like("firstName", "Divya%"));
		List list = criteria.list();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String empName = (String) iterator.next();
			System.out.println(empName);

		}

		/**
		 * ===========How to use Order By With Criteria===========
		 */
		criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.like("firstName", "Divya%")).addOrder(Order.asc("salary"));
		list = criteria.list();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String empName = (String) iterator.next();
			System.out.println(empName);

		}
		/**
		 * ===========How to use Projection=========== If you are adding two
		 * property using set Projection then only last added projection will
		 * consider
		 */
		criteria = session.createCriteria(Employee.class);
		criteria.setProjection(Projections.property("firstName"));
		criteria.setProjection(Projections.property("lastName"));
		list = criteria.list();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			String empName = (String) iterator.next();
			System.out.println(empName);

		}

		/**
		 * ===========How to use ProjectionList===========
		 * 
		 * If you are adding more than one property then you have to use
		 * ProjectionList
		 * 
		 */
		criteria = session.createCriteria(Employee.class);
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("firstName")).add(Projections.property("lastName"));
		criteria.setProjection(proList);

		for (Iterator iterator = criteria.list().iterator(); iterator.hasNext();) {
			Object[] objArray = (Object[]) iterator.next();
			System.out.println(objArray[0]);
			System.out.println(objArray[1]);
		}
		sessionFactory.close();

		/*
		 * Detached queries and subqueries
		 * 
		 * The DetachedCriteria class allows you to create a query outside the
		 * scope of a session and then execute it using an arbitrary Session.
		 */

		System.out.println("Done");
	}

}
