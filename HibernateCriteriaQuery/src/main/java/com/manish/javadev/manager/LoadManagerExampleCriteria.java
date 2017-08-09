package com.manish.javadev.manager;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;

import com.manish.javadev.model.Employee;
import com.manish.javadev.model.EmployeeAddress;
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

		Criteria criteria = session.createCriteria(EmployeeAddress.class);

		EmployeeAddress employee = new EmployeeAddress();
		employee.setId(1);
		// employee.setFirstName("Ajay");
		// employee.setSalary(70000);
		Example example = Example.create(employee);
		example.ignoreCase();

		criteria.add(example);
		List<EmployeeAddress> list = (List<EmployeeAddress>) criteria.list();
		for (EmployeeAddress emp : list) {
			System.out.println(emp);
		}
		sessionFactory.close();

		System.out.println("Done");
	}

}
