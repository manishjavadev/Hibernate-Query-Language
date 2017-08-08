package com.manish.javadev.manager;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.manish.javadev.model.Employee;
import com.manish.javadev.util.HibernateUtil;

public class LoadManagerExampleCriteria {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		/**
		 * ===========How to use Hibernate Example Query
		 * 
		 * Hibernate ignore two things in example,
		 * 
		 * 1) If any of the property has a value null then its not going to
		 * consider. 2) If property having primary key. Note: Apart from Primary
		 * key if any value you set then that value is going to Consider in
		 * query
		 */

		Employee employee = new Employee();
		employee.setId(new Long(1));
		employee.setFirstName("Divy1");
		Example example = Example.create(employee);
		Criteria criteria = session.createCriteria(Employee.class).add(example);
		List list = criteria.list();

		sessionFactory.close();

		System.out.println("Done");
	}

}
