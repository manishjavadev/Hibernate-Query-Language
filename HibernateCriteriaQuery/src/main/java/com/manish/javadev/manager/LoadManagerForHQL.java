package com.manish.javadev.manager;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.manish.javadev.model.Employee;
import com.manish.javadev.util.HibernateUtil;

public class LoadManagerForHQL {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		System.out.println("==Select all property =============");
		// HQL Query Example Way1
		String hql = "from Employee";
		Query query = session.createQuery(hql);
		List<Employee> allEmp = (List<Employee>) query.list();
		for (Employee emp : allEmp) {
			System.out.println(emp);
		}
		System.out.println("==Select all property, but conditionaly ===");
		// HQL Query Example Way2
		hql = "from Employee where id > 5";
		query = session.createQuery(hql);
		allEmp = (List<Employee>) query.list();
		for (Employee emp : allEmp) {
			System.out.println(emp);
		}

		System.out
				.println("==Select all property, but conditionaly with  setFirstResult and setMaxResults===");
		// HQL Query Example Way3
		hql = "from Employee";
		query = session.createQuery(hql);
		query.setFirstResult(2);
		query.setMaxResults(5);
		allEmp = (List<Employee>) query.list();
		for (Employee emp : allEmp) {
			System.out.println(emp);
		}
		System.out.println("==Select One Particular property=====");
		// HQL Query Example Way4
		hql = "Select firstName from Employee";
		query = session.createQuery(hql);
		List<String> empNameList = (List<String>) query.list();
		for (String empName : empNameList) {
			System.out.println(empName);
		}

		System.out.println("==Select More than One Particular property=====");
		// HQL Query Example Way5
		hql = "Select firstName,lastName from Employee";
		query = session.createQuery(hql);
		List<Object[]> objNamedList = (List<Object[]>) query.list();
		for (Object[] objects : objNamedList) {
			String firstName = (String) objects[0];
			String lastName = (String) objects[1];
			System.out.println("First Name = " + firstName + "Last Name = "
					+ lastName);
		}
		for (Object[] objects : objNamedList) {
			System.out.println(Arrays.toString(objects));
		}

		sessionFactory.close();
		System.out.println("Done");
	}

}
