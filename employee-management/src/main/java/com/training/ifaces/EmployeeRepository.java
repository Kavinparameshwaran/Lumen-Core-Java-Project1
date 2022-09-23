package com.training.ifaces;

import java.time.LocalDate;
import java.util.Collection;

import com.training.exceptions.EmployeeNotFoundException;
import com.training.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee> {
	public Collection<Employee> findByFirstName(String firstName) throws EmployeeNotFoundException;

	public Collection<Employee> findFirstNameAndPhoneNumberOfAll() throws EmployeeNotFoundException;

	public boolean updateByEmailAndPhoneNumberOfAnEmployee(String updatedEmail, long phoneNumber, String email) throws EmployeeNotFoundException;

	public boolean deleteByFirstName(String firstName, String email) throws EmployeeNotFoundException;

	public Collection<Employee> findFirstNameAndEmailOfAllByBirthday(LocalDate dateOfBirth)
			throws EmployeeNotFoundException;

	public Collection<Employee> findFirstNameAndPhoneNumberOfAllByWeddingDate(LocalDate weddingDate)
			throws EmployeeNotFoundException;
}
