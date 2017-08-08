package com.manish.javadev.manager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.manish.javadev.model.Employee;
import com.manish.javadev.util.HibernateUtil;

public class InsertManager {
	public static void main(String[] args) {
		// Prep work
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();

		// Save Employee 1
		Employee emp = new Employee("Divy1", "Srivastava", 70000, "Bangalore");
		session.save(emp);

		// Save Employee 2
		emp = new Employee("Rajesh", "Srivastava", 50000, "Bangalore");
		session.save(emp);

		// Save Employee 3
		emp = new Employee("Ajay", "Srivastava", 150000, "Bangalore");
		session.save(emp);

		// Save Employee 4
		emp = new Employee("Raju", "Srivastava", 40000, "Bangalore");
		session.save(emp);

		// Save Employee 5
		emp = new Employee("Manish", "Srivastava", 55000, "Bangalore");
		session.save(emp);

		// Save Employee 6 As Divya for like Query
		emp = new Employee("Divy1", "Srivastava", 45000, "Bangalore");
		session.save(emp);

		// Save Employee 7 As Manish for like Query
		emp = new Employee("manish1", "Srivastava", 40000, "Bangalore");
		session.save(emp);

		// Save Employee 8 As Manish for like Query
		emp = new Employee("manish11", "Srivastava", 45000, "Bangalore");
		session.save(emp);

		tx.commit();
		session.close();
		System.out.println("Done");
	}
}
