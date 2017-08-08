package com.manish.javadev.manager;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

		System.out.println("==Way2 === from Employee =============");
		// HQL Query Example Way1
		String sql = "from Employee";
		Query query = session.createQuery(sql);
		List<Employee> allEmp = (List<Employee>) query.list();
		for (Employee emp : allEmp) {
			System.out.println(emp);
		}
		System.out.println("==Way2 === from Employee where id>5===");
		// HQL Query Example Way2
		String sql2 = "from Employee where id>5";
		Query query2 = session.createQuery(sql2);
		allEmp = (List<Employee>) query2.list();
		for (Employee emp : allEmp) {
			System.out.println(emp);
		}

		System.out.println("==Way3 === from Employee===");
		System.out.println("==Way3 === setFirstResult===");
		System.out.println("==Way3 === setMaxResults===");
		// HQL Query Example Way3
		String sql3 = "from Employee";
		Query query3 = session.createQuery(sql3);
		query3.setFirstResult(2);
		query3.setMaxResults(5);
		allEmp = (List<Employee>) query3.list();
		for (Employee emp : allEmp) {
			System.out.println(emp);
		}
		System.out.println("==Way4 === Select firstName from Employee===");
		// HQL Query Example Way4
		String sql4 = "Select firstName from Employee";
		Query query4 = session.createQuery(sql4);
		List<String> empNameList = (List<String>) query4.list();
		for (String empName : empNameList) {
			System.out.println(empName);
		}

		System.out.println("==Way5 === Select firstName from Employee===");
		// HQL Query Example Way5
		String sql5 = "Select firstName,lastName from Employee";
		Query query5 = session.createQuery(sql5);
		List<Object[]> objNamedList = (List<Object[]>) query5.list();
		for (Object[] objects : objNamedList) {
			System.out.println(Arrays.toString(objects));
		}

		// HQL Query Example Way4
		String sql6 = "Select map(id,firstName) from Employee";
		Query query6 = session.createQuery(sql6);
		System.out.println(query6.list());
		sessionFactory.close();
		System.out.println("Done");
	}

}
