package com.training.model;

import java.time.LocalDate;
import java.util.Scanner;
import com.example.demo.sevices.EmployeeService;
import com.training.exceptions.EmployeeNotFoundException;

public class App {
	public static long getPhoneNumber(Scanner input) {
        try {
            System.out.println("Enter the phone number : ");
            long phoneNumber=Long.parseLong(input.nextLine());
            return phoneNumber;
            
        }
        catch(NumberFormatException e) {
            System.err.println("Phone Number should not contain letters");
            return getPhoneNumber(input);
        }
    }
	public static LocalDate getDateOfBirth(Scanner input) {
        try {
            System.out.println("Enter the Date of Birth in the format (YYYY-MM-DD): ");
            LocalDate dateOfBirth = LocalDate.parse(input.nextLine());
            return dateOfBirth;
            
        }
        catch(Exception e) {
            System.err.println("Date of Birth should not contain letters");
            return getDateOfBirth(input);
        }
    }
	public static LocalDate getWeddingDate(Scanner input) {
        try {
            System.out.println("Enter the Wedding date in the format (YYYY-MM-DD): ");
            LocalDate weddingDate = LocalDate.parse(input.nextLine());
            return weddingDate;
            
        }
        catch(Exception e) {
            System.out.println("Wedding date should not contain letters");
            return getWeddingDate(input);
        }
    }

	public static LocalDate getWeddingChoice(Scanner input) {
		while (true) {
			System.out.println("Are you married? y/n or Y/N");
			String weddingChoice = input.nextLine();
			if (weddingChoice.equalsIgnoreCase("y")) {
				 LocalDate weddingDate=getWeddingDate(input);
				return weddingDate;
			}
			else if (weddingChoice.equalsIgnoreCase("n")) {
				return null;
			}
			else {
				System.out.println("PROVIDE VALID OPTION!!!!!!!!!!!");
			}
		}
		
	}

	public static void takeInputFromUser() throws EmployeeNotFoundException {
		Scanner input = new Scanner(System.in);
		EmployeeService service = new EmployeeService();
		while (true) {
			System.out.println("Enter number between 1 to 7");
			System.out.println("1->Add Employee");
			System.out.println("2->Find Employees By First Name");
			System.out.println("3->Find First Name and Phone Number of all Employees");
			System.out.println("4->Update Email and PhoneNumber of a Particular Employee");
			System.out.println("5->Delete Employee by First Name");
			System.out.println("6->Find First Name and Email of all Employees by Birthday");
			System.out.println("7->Find First Name and Phone Number of all Employees by Wedding Date");
			System.out.println("Enter the number");
			int choice = Integer.parseInt(input.nextLine());
			if (choice == 1) {
				System.out.println("-------------Enter required details to add Employee---------------------");
				System.out.println("First Name:");
				String firstName = input.nextLine();
				System.out.println("Last Name:");
				String lastName = input.nextLine();
				System.out.println("Address:");
				String address = input.nextLine();
				System.out.println("Email:");
				String email = input.nextLine();
				long phoneNumber =getPhoneNumber(input);
				LocalDate dateOfBirth = getDateOfBirth(input);
				LocalDate weddingDate =getWeddingChoice(input);
				service.save(new Employee(firstName, lastName, address, email, phoneNumber, dateOfBirth, weddingDate));
			} else if (choice == 2) {
				System.out.println("2->Find Employees By First Name");
				System.out.println("First Name:");
				String firstName = input.nextLine();
				service.findByFirstName(firstName);
			} else if (choice == 3) {
				System.out.println("3->Find First Name and Phone Number of all Employees");
				service.findFirstNameAndPhoneNumberOfAll();
			} else if (choice == 4) {
				System.out.println("4->Update Email and PhoneNumber of a Particular Employee");
				System.out.println("Updated Email:");
				String updatedEmail = input.nextLine();
				long phoneNumber =getPhoneNumber(input);
				System.out.println("Old Email:");
				String email = input.nextLine();
				service.updateByEmailAndPhoneNumberOfAnEmployee(updatedEmail, phoneNumber, email);
			} else if (choice == 5) {
				System.out.println("5->Delete Employee by First Name");
				System.out.println("First Name:");
				String firstName = input.nextLine();
				System.out.println("Email:");
				String email = input.nextLine();
				service.deleteByFirstName(firstName, email);
			} else if (choice == 6) {
				System.out.println("6->Find First Name and Email of all Employees by Birthday");
				LocalDate dateOfBirth = getDateOfBirth(input);
				service.findFirstNameAndEmailOfAllByBirthday(dateOfBirth);
			} else if (choice == 7) {
				System.out.println("7->Find First Name and Phone Number of all Employees by Wedding Date");
				LocalDate weddingDate = getWeddingDate(input);
				service.findFirstNameAndPhoneNumberOfAllByWeddingDate(weddingDate);
			} else {
				System.out.println("ONLY ENTER THE NUMBER BETWEEN 1 TO 7!!!!!!!!!");
				continue;
			}
			if (!getContinueChoice(input, service)) {
				break;
			}

		}

	}

	public static boolean getContinueChoice(Scanner input, EmployeeService service) throws EmployeeNotFoundException {
		while (true) {
			System.out.println("Do you want to continue? y/n or Y/N");
			String willingToContinue = input.nextLine();
			if (willingToContinue.equalsIgnoreCase("y")) {
				return true;

			} else if (willingToContinue.equalsIgnoreCase("n")) {
				input.close();
				System.out.println("Successfully Exited from the Menu");
				return false;

			} else {
				System.out.println("PROVIDE VALID OPTION!!!!!!!!!!!");
			}
		}

	}

	public static void main(String[] args) throws EmployeeNotFoundException {
		takeInputFromUser();
	}

}
