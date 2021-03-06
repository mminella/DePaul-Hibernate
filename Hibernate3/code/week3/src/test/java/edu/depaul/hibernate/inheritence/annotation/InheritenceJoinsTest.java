package edu.depaul.hibernate.inheritence.annotation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import edu.depaul.hibernate.domain.annotation.JoinContractEmployee;
import edu.depaul.hibernate.domain.annotation.JoinFullTimeEmployee;
import edu.depaul.hibernate.domain.annotation.JoinPartTimeEmployee;

@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class InheritenceJoinsTest {

	@SuppressWarnings("serial")
	private List<String> names = new ArrayList<String>(){{
		add("Michael Minella");
		add("Terry Minella");
		add("Rudy Neuberger");
		add("Russ Benes");
	}};

	@Autowired
	private SessionFactory sessionFactory;

	private Random randomGenerator = new Random();

	@Test
	@Transactional
	public void test() {
		Session session = sessionFactory.getCurrentSession();

		saveContractEmployees(session);
		savePartTimeEmployees(session);
		saveFullTimeEmployees(session);
	}

	private void savePartTimeEmployees(Session session) {

		JoinPartTimeEmployee employee = new JoinPartTimeEmployee();

		employee.setHourlyRate(randomGenerator.nextInt(20));
		employee.setVacation(randomGenerator.nextInt(365));
		int index = randomGenerator.nextInt(names.size());
		employee.setName(names.get(index));
		names.remove(index);

		System.out.println("The part timer is " + employee.getName());

		session.save(employee);
	}

	/**
	 *
	 */
	private void saveFullTimeEmployees(Session session) {
		JoinFullTimeEmployee employee = new JoinFullTimeEmployee();

		employee.setSalary(randomGenerator.nextInt());
		employee.setVacation(randomGenerator.nextInt(365));
		int index = randomGenerator.nextInt(names.size());
		employee.setName(names.get(index));
		names.remove(index);

		System.out.println("The full timer is " + employee.getName());

		session.save(employee);
	}

	/**
	 *
	 */
	private void saveContractEmployees(Session session) {
		JoinContractEmployee employee = new JoinContractEmployee();

		employee.setDailyRate(randomGenerator.nextDouble());
		int index = randomGenerator.nextInt(names.size());
		employee.setName(names.get(index));
		names.remove(index);

		System.out.println("The contractor is " + employee.getName());

		session.save(employee);
	}
}
