package com.example.demo.sevices;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.example.demo.repository.EmployeeRepositoryImpl;
import com.example.demo.utils.ConnectionFactory;
import com.training.exceptions.EmployeeNotFoundException;
import com.training.ifaces.EmployeeRepository;
import com.training.model.Employee;

public class EmployeeService {
	Connection con;
	EmployeeRepository repo;
	private static final Logger logger = LogManager.getRootLogger();

	public EmployeeService() {
		super();
		this.con = ConnectionFactory.getMySqlConnection();
		this.repo = new EmployeeRepositoryImpl(con);
	}

	public void save(Employee obj) {
		boolean added=this.repo.save(obj);
		
		if (added) {
			logger.info("is Employee Created:=" + added);
		} else {
			logger.error("is Employee Created:=" + added);
		}

	}

	public void findByFirstName(String firstName) {
		Collection<Employee> employeeList = new ArrayList<>();
		try {
			employeeList = this.repo.findByFirstName(firstName);
			logger.info("List of employees who are having first name as: " + firstName);
		} catch (EmployeeNotFoundException e) {
			logger.error("Not able to find with first name: " + firstName);
		}
		for (Employee employee : employeeList) {
			logger.info(employee);
		}
	}

	public void findFirstNameAndPhoneNumberOfAll() {
		Collection<Employee> employeeList = new ArrayList<>();
		try {
			employeeList = this.repo.findFirstNameAndPhoneNumberOfAll();
			logger.info("First name and PhoneNumber of all employees");
		} catch (EmployeeNotFoundException e1) {
			logger.error("First name and PhoneNumber of employees cannot be found");
		}

		employeeList.forEach(e -> System.out.println(" FirstName :"+e.getFirstName() + " and PhoneNumber: " + e.getPhoneNumber()));
	}

	public void updateByEmailAndPhoneNumberOfAnEmployee(String updatedEmail, long phoneNumber, String email) {
		try {
			boolean update = this.repo.updateByEmailAndPhoneNumberOfAnEmployee(updatedEmail, phoneNumber, email);
			logger.info("Does an employee with email: " + email + " get updated:=" + update);
		} catch (EmployeeNotFoundException e) {
			logger.error(
					"Employee with the given email: " + email + " cannot be found.So this employee cannot be updated");
		}
	}

	public void deleteByFirstName(String firstName, String email) {
		try {
			boolean delete = this.repo.deleteByFirstName(firstName, email);
			logger.info("Does an employee with email: " + email + " get deleted:=" + delete);
		} catch (EmployeeNotFoundException e) {
			logger.error(
					"Particular Employee with the given name: " + firstName + " and email: " + email + " is not found");
		}
	}

	public void findFirstNameAndEmailOfAllByBirthday(LocalDate dateOfBirth) {
		Collection<Employee> employeeList = new ArrayList<>();
		try {
			employeeList = this.repo.findFirstNameAndEmailOfAllByBirthday(dateOfBirth);
			logger.info("First name and PhoneNumber of all employees who have born on=" + dateOfBirth);
		} catch (EmployeeNotFoundException e1) {
			logger.error("No Employees have been found with the given date of birth: " + dateOfBirth);
		}
		employeeList.forEach(e -> System.out.println("FirstName :"+e.getFirstName() + " and Email :" + e.getEmail()));
	}

	public void findFirstNameAndPhoneNumberOfAllByWeddingDate(LocalDate weddingDate) {
		Collection<Employee> employeeList = new ArrayList<>();
		
		try {
			employeeList = this.repo.findFirstNameAndPhoneNumberOfAllByWeddingDate(weddingDate);
			logger.info("First name and PhoneNumber of all employees who got married on=" + weddingDate);

		} catch (EmployeeNotFoundException e1) {
			logger.error("No Employees have been found with the given date of birth: " + weddingDate);
		}

		employeeList.forEach(e -> System.out.println(" FirstName :"+e.getFirstName() + " and PhoneNumber: " + e.getPhoneNumber()));
	}
}
