package com.manish.javadev.manager;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Subqueries;

import com.manish.javadev.model.Employee;
import com.manish.javadev.model.EmployeeAddress;
import com.manish.javadev.util.HibernateUtil;

public class CriteriaProjectionManager {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		/**
		 * What is Projection
		 * 
		 * Hibernate Projections are used in order to query only a subset of the
		 * attributes of an entity. or group of entities you're querying with
		 * Criteria.
		 * 
		 * You can also use Projections to specify distinct clauses and
		 * aggregate functions like max, sum and so on. It's like referring to
		 * which data you're fetching. Like modifying the select clause in an
		 * SQL query.
		 * 
		 */

		/**
		 * ===========How to use Projection===========
		 */

		/**
		 * 
		 * If you are adding more than one property using set Projection then
		 * only last added projection will consider
		 */
		System.out.println("\n\nSingle property with Criteria");
		Criteria criteria = session.createCriteria(Employee.class);
		criteria = session.createCriteria(Employee.class);
		criteria.setProjection(Projections.property("firstName"));
		criteria.setProjection(Projections.property("lastName"));
		List list = criteria.list();

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

		/**
		 * Detached queries and subqueries
		 * 
		 * The DetachedCriteria class allows you to create a query outside the
		 * scope of a session and then execute it using an arbitrary Session.
		 */

		/**
		 * The Below DetachedCriteria and Criteria condition will generate the
		 * query like that
		 * 
		 * Hibernate: select EMP_ID, FIRST_NAME, LAST_NAME, SALARY from EMPLOYEE
		 * where EMP_ID in (select EMP_ID from ADDRESS)
		 */
		DetachedCriteria detCriteria = DetachedCriteria.forClass(
				EmployeeAddress.class).setProjection(Property.forName("empId"));

		criteria = session.createCriteria(Employee.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.add(Subqueries.propertyIn("id", detCriteria));
		list = criteria.list();
		for (Object object : list) {
			System.out.println(object);
		}

		System.out.println("Done");
	}
}
