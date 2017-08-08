package com.manish.javadev.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.manish.javadev.model.Employee;
import com.manish.javadev.model.EmployeeAddress;
import com.manish.javadev.util.HibernateUtil;

public class InsertEmployeeManager {
	public static void main(String[] args) {
		// Prep work
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		// Save Employee 1
		Employee emp = new Employee("Divya1", "Srivastava", 70000);
		session.save(emp);
		EmployeeAddress employeeAddress = new EmployeeAddress(emp.getId(),
				"Lucknow", "UP");
		session.save(employeeAddress);

		// Save Employee 2
		emp = new Employee("Rajesh", "Srivastava", 50000);
		session.save(emp);
		employeeAddress = new EmployeeAddress(emp.getId(), "Lucknow", "UP");
		session.save(employeeAddress);

		// Save Employee 3
		emp = new Employee("Ajay", "Srivastava", 150000);
		session.save(emp);
		employeeAddress = new EmployeeAddress(emp.getId(), "Lucknow", "UP");
		session.save(employeeAddress);

		// Save Employee 4
		emp = new Employee("Raju", "Sinha", 40000);
		session.save(emp);
		employeeAddress = new EmployeeAddress(emp.getId(), "Lucknow", "UP");
		session.save(employeeAddress);

		// Save Employee 5
		emp = new Employee("Manish", "Sinha", 55000);
		session.save(emp);
		employeeAddress = new EmployeeAddress(emp.getId(), "Lucknow", "UP");
		session.save(employeeAddress);

		// Save Employee 6 As Divyaa for like Query
		emp = new Employee("Divya11", "Sinha", 45000);
		session.save(emp);
		employeeAddress = new EmployeeAddress(emp.getId(), "Lucknow", "UP");
		session.save(employeeAddress);

		// Save Employee 7 As Manish for like Query
		emp = new Employee("Manish1", "Srivastava", 40000);
		session.save(emp);
		employeeAddress = new EmployeeAddress(emp.getId(), "Lucknow", "UP");
		session.save(employeeAddress);

		// Save Employee 8 As Manish for like Query
		emp = new Employee("Manish11", "Srivastava", 45000);
		session.save(emp);
		employeeAddress = new EmployeeAddress(emp.getId(), "Lucknow", "UP");
		session.save(employeeAddress);

		tx.commit();
		session.close();
		System.out.println("Done");
	}
}
