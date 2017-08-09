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

public class TestManager {
	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		/**
		 * The Below DetachedCriteria and Criteria condition will generate the
		 * query like that
		 * 
		 * Hibernate: select EMP_ID, FIRST_NAME, LAST_NAME, SALARY from EMPLOYEE
		 * where EMP_ID in (select EMP_ID from ADDRESS)
		 */
		DetachedCriteria detCriteria = DetachedCriteria.forClass(
				EmployeeAddress.class).setProjection(Property.forName("empId"));

		Criteria criteria = session.createCriteria(Employee.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.add(Subqueries.propertyIn("id", detCriteria));
		List list = criteria.list();
		for (Object object : list) {
			System.out.println(object);
		}
		System.out.println("Done");

	}
}
