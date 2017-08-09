package com.manish.javadev.manager;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.manish.javadev.model.Employee;
import com.manish.javadev.model.ResultEntity;
import com.manish.javadev.util.HibernateUtil;

public class LoadManagerForNamedQuery {

	public static void main(String[] args) {

		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();

		Transaction tx = session.beginTransaction();

		// HQL Named Query Example

		System.out.println("Getting All Employee HQL");
		Query query = session.getNamedQuery("ALL_EMPLOYEE_BY_HQL");
		List<Employee> empListHQL = (List<Employee>) query.list();
		for (Employee emp : empListHQL) {
			System.out.println("Employee Data = " + emp);
		}

		// SQL Named Query Example
		System.out.println("\n\nGetting All Employee SQL");
		query = session.getNamedQuery("ALL_EMPLOYEE_BY_SQL");
		List<Employee> empList = (List<Employee>) query.list();
		for (Employee emp : empList) {
			System.out.println("Employee Data = " + emp);
		}

		System.out.println("\n\nGoing to fetch Employee with id 2");
		query = session.getNamedQuery("EMPLOYEE_BY_ID_HQL");
		query.setInteger("id", 2);
		Employee emp = (Employee) query.uniqueResult();
		System.out.println("Employee Id =  " + emp.getId() + " Employee Name="
				+ emp.getFirstName());

		System.out.println("\n\nEmployee whose salary greater than 200");
		query = session.getNamedQuery("EMPLOYEE_BY_SALARY_HQL");
		query.setDouble("salary", 200);
		empList = (List<Employee>) query.list();
		for (Employee emp1 : empList) {
			System.out.println(emp1);

		}

		System.out.println("\n\nGoing to use Join");

		query = session.getNamedQuery("EMPLOYEE_ADDRESS_JOIN");
		List<ResultEntity> reEmpList = query.list();
		for (ResultEntity resultEntity : reEmpList) {
			System.out.println(resultEntity);

		}

		sessionFactory.close();
		System.out.println("Done");
	}

}
