package com.example.demo.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.training.exceptions.EmployeeNotFoundException;
import com.training.ifaces.EmployeeRepository;
import com.training.model.Employee;

public class EmployeeRepositoryImpl implements EmployeeRepository {

	private Connection con;

	public EmployeeRepositoryImpl(Connection con) {
		super();
		this.con = con;
	}

	@Override
	public Collection<Employee> findAll() throws EmployeeNotFoundException {
		List<Employee> employeeList = new ArrayList<>();
		Employee employee;
		String sql = "select * from employee_management";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				employee = mapRowToObjectForAllColumns(resultSet);
				employeeList.add(employee);
				while (resultSet.next()) {
					employee = mapRowToObjectForAllColumns(resultSet);
					employeeList.add(employee);
				}
			} else {
				throw new EmployeeNotFoundException("ERR-100", "Employee is not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public boolean save(Employee obj) {
		String query="insert into employee_management values(?,?,?,?,?,?,?)";
        int rowAdded=0;
        try(PreparedStatement statement=con.prepareStatement(query)){
            statement.setString(1,obj.getFirstName());
            statement.setString(2,obj.getLastName());
            statement.setString(3,obj.getAddress());
            statement.setString(4,obj.getEmail());
            statement.setLong(5, obj.getPhoneNumber());
            statement.setDate(6, Date.valueOf(obj.getDateOfBirth()));
            if(obj.getWeddingDate()!=null) {
                statement.setDate(7,Date.valueOf(obj.getWeddingDate()));
            }
            else {
            	statement.setDate(7, null);
            }
            rowAdded=statement.executeUpdate();

        }
        catch (Exception e) {
            System.err.println("Employee with the same mail id is already found");
        }
        if(rowAdded==1) {
            return true;
        }
        return false;
	}

	@Override
	public Collection<Employee> findByFirstName(String firstName) throws EmployeeNotFoundException {
		Collection<Employee> employeeList = new ArrayList<>();
		employeeList = findAll().stream().filter(e -> e.getFirstName().equals(firstName)).collect(Collectors.toList());
		if (employeeList.isEmpty()) {
			throw new EmployeeNotFoundException("ERR-102", "Employee Not found with the given name: " + firstName);
		} else {
			return employeeList;
		}
	}

	@Override
	public Collection<Employee> findFirstNameAndPhoneNumberOfAll() throws EmployeeNotFoundException {
		Collection<Employee> employeeList = new ArrayList<>();
		Employee employee;
		String sql = "select firstName,phoneNumber from employee_management";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				employee = mapRowToObjectForFirstNameAndPhoneNumber(resultSet);
				employeeList.add(employee);
				while (resultSet.next()) {
					employee = mapRowToObjectForFirstNameAndPhoneNumber(resultSet);
					employeeList.add(employee);
				}
			} else {
				throw new EmployeeNotFoundException("ERR-103", "No Employees Found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public boolean updateByEmailAndPhoneNumberOfAnEmployee(String updatedEmail, long phoneNumber, String email)
			throws EmployeeNotFoundException {
		String sql = "update employee_management SET email=?, phoneNumber=? where email=?";
		int rowUpdated = 0;
		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, updatedEmail);
			statement.setLong(2, phoneNumber);
			statement.setString(3, email);
			rowUpdated = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rowUpdated == 1) {
			return true;
		} else {
			throw new EmployeeNotFoundException("ERR-104",
				"Employee with the given email: " + email + " cannot be found.So this employee cannot be updated");
		}

	}

	@Override
	public boolean deleteByFirstName(String firstName, String email) throws EmployeeNotFoundException {
		int rowDeleted = 0;
		String sql = "delete from employee_management where firstName=? and email=?";
		try (PreparedStatement statement = con.prepareStatement(sql)) {
			statement.setString(1, firstName);
			statement.setString(2, email);
			rowDeleted = statement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (rowDeleted == 1) {
			return true;
		} else {
			throw new EmployeeNotFoundException("ERR-105",
					"Particular Employee with the given name: " + firstName + " and email: " + email + " is not found");
		}
	}

	@Override
	public Collection<Employee> findFirstNameAndEmailOfAllByBirthday(LocalDate dateOfBirth)
			throws EmployeeNotFoundException {
		List<Employee> employeeList = new ArrayList<>();
		Employee employee;
		String sql = "select firstName,email from employee_management where MONTH(dateOfBirth)=? and DAY(dateOfBirth)=?";

		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			Date date = Date.valueOf(dateOfBirth);
			pstmt.setInt(1, date.getMonth()+1);
			pstmt.setInt(2, date.getDate());
			System.out.println(date.getMonth()+" "+date.getDate());
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				employee = mapRowToObjectForFirstNameAndEmail(resultSet);
				employeeList.add(employee);
				while (resultSet.next()) {
					employee = mapRowToObjectForFirstNameAndEmail(resultSet);
					employeeList.add(employee);
				}
			} else {
				throw new EmployeeNotFoundException("ERR-106",
						"No Employees have been found with the given date of birth: " + dateOfBirth);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	@Override
	public Collection<Employee> findFirstNameAndPhoneNumberOfAllByWeddingDate(LocalDate weddingDate)
			throws EmployeeNotFoundException {
		List<Employee> employeeList = new ArrayList<>();
		Employee employee;
		String sql = "select firstName,phoneNumber from employee_management where  MONTH(weddingDate)=? and DAY(weddingDate)=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql)) {
			Date date = Date.valueOf(weddingDate);
			pstmt.setInt(1, date.getMonth()+1);
			pstmt.setInt(2, date.getDate());
			ResultSet resultSet = pstmt.executeQuery();
			if (resultSet.next()) {
				employee = mapRowToObjectForFirstNameAndPhoneNumber(resultSet);
				employeeList.add(employee);
				while (resultSet.next()) {
					employee = mapRowToObjectForFirstNameAndPhoneNumber(resultSet);
					employeeList.add(employee);
				}
			} else {
				throw new EmployeeNotFoundException("ERR-107",
						"No Employees have been found with the given Wedding Date: " + weddingDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeList;
	}

	private Employee mapRowToObjectForAllColumns(ResultSet resultSet) throws SQLException {
		String firstName = resultSet.getString("firstName");
		String lastName = resultSet.getString("lastName");
		String address = resultSet.getString("address");
		String email = resultSet.getString("email");
		long phoneNumber = resultSet.getLong("phoneNumber");
		LocalDate dateOfBirth = resultSet.getDate("dateOfBirth").toLocalDate();
		LocalDate weddingDate = null;
		if (resultSet.getDate("weddingDate") != null) {
			weddingDate = resultSet.getDate("weddingDate").toLocalDate();
		}
		return new Employee(firstName, lastName, address, email, phoneNumber, dateOfBirth, weddingDate);
	}

	private Employee mapRowToObjectForFirstNameAndPhoneNumber(ResultSet resultSet) throws SQLException {
		String firstName = resultSet.getString("firstName");
		long phoneNumber = resultSet.getLong("phoneNumber");
		return new Employee(firstName, phoneNumber);
	}

	private Employee mapRowToObjectForFirstNameAndEmail(ResultSet resultSet) throws SQLException {
		String firstName = resultSet.getString("firstName");
		String email = resultSet.getString("email");
		return new Employee(firstName, email);
	}

}
