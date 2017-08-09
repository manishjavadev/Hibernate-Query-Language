package com.manish.javadev.manager;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;

import com.manish.javadev.model.Employee;
import com.manish.javadev.model.EmployeeAddress;
import com.manish.javadev.util.HibernateUtil;

public class LoadManagerForCriteria {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		/**
		 * ===========How to use Like With Criteria===========
		 */
		System.out.println("Like Query With Criteria");
		Criteria criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.like("firstName", "Di%"));
		List list = criteria.list();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object object = iterator.next();
			System.out.println(object);

		}

		/**
		 * ===========How to use in("",""..) operator With Criteria===========
		 */
		System.out.println("\n\nin() operator With Criteria");
		criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.in("firstName", new String[] { "Divya1",
				"Manish1", "Raju" }));
		list = criteria.list();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object object = iterator.next();
			System.out.println(object);

		}

		/**
		 * ===========How to use Order By With Criteria===========
		 */

		System.out.println("\n\nOrder By Query With Criteria");
		criteria = session.createCriteria(Employee.class);
		criteria.add(Restrictions.like("firstName", "Divya%")).addOrder(
				Order.asc("salary"));
		list = criteria.list();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object object = iterator.next();
			System.out.println(object);
		}

		/**
		 * ===========How to use Projection===========
		 * 
		 * If you are adding more than one property using set Projection then
		 * only last added projection will consider
		 */
		System.out.println("\n\nSingle property with Criteria");
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
		System.out
				.println("\n\nMultiple Property with ProjectionList & Criteria");
		criteria = session.createCriteria(Employee.class);
		ProjectionList proList = Projections.projectionList();
		proList.add(Projections.property("firstName")).add(
				Projections.property("lastName"));
		criteria.setProjection(proList);

		for (Iterator iterator = criteria.list().iterator(); iterator.hasNext();) {
			Object[] objArray = (Object[]) iterator.next();
			System.out.println("First Name = " + objArray[0]
					+ "  And Last Name = " + objArray[1]);
		}
		sessionFactory.close();

		/*
		 * Detached queries and subqueries
		 * 
		 * The DetachedCriteria class allows you to create a query outside the
		 * scope of a session and then execute it using an arbitrary Session.
		 */

		DetachedCriteria detCriteria = DetachedCriteria.forClass(EmployeeAddress.class)
				.setProjection(Property.forName("empId"));

		criteria = session.createCriteria(Employee.class);

		list = criteria.add(Subqueries.geAll("id", detCriteria)).list();

		System.out.println("Done");

		System.out.println("Done");
	}

}
