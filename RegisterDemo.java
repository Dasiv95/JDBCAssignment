package july13;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class RegisterDemo {

	public static void main(String[] args) {

		CustInfo info = new CustInfo("siva", "abcd12342", "MALE", 34, "belljava2017@gmail.com");
		RegistrationRepository rr = new RegistrationRepository();
		rr.createRegistration();

		int statusResult = rr.insertCustInfo(info);

		System.out.println("Insertion Status Report - inserted:" + statusResult);
		
		CustInfo info1 = new CustInfo("Rose", "abcd12342", "MALE", 34, "rose@gmail.com");

		rr.createRegistration();

		int statusResult1 = rr.insertCustInfo(info1);

		System.out.println("Insertion Status Report - inserted:" + statusResult1);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Select a name whose details you want to update : ");
		String updName = sc.next();
		rr.createRegistration();
		System.out.println("What do you want to update : ");
		String updField = sc.next();
		if (updField.equals("age")) {
			System.out.println("What do you want update " + updField + " with :");
			int updValue = sc.nextInt();
			int statusUpdate = rr.updateInfo(updName, updField, updValue);
			if(statusUpdate==1)
			System.out.println("Updated successfully");
			else
				System.out.println("Sorry.. couldnt update ");

		} else {
			System.out.println("What do you want update " + updField + " with :");
			String updValue = sc.next();
			int statusUpdate = rr.updateInfo(updName, updField, updValue);
			if(statusUpdate==1)
				System.out.println("Updated successfully");
				else
					System.out.println("Sorry.. couldnt update ");
		}
		
		System.out.println("Based on what column do you want to delete the rows :");
		String delColumn = sc.next();
		System.out.println("What value of "+delColumn+" do you want to delete :");
		
		if(delColumn.equals("age")){
			int delValue = sc.nextInt();
			int delStatus = rr.deleteInfo(delColumn,delValue);
			if(delStatus==1)
				System.out.println("deleted successfully");
			else
				System.out.println("Sorry.. couldn't perform your request");
			
		}
		else{
			String delValue = sc.next();
			int delStatus = rr.deleteInfo(delColumn,delValue);
			if(delStatus==1)
				System.out.println("deleted successfully");
			else
				System.out.println("Sorry.. couldn't perform your request");
		}
	
	
		System.out.println("Please enter your login name :");
		String uName = sc.next();
		System.out.println("Please enter your password :");
		String uPass = sc.next();
		int logRes = rr.login(uName, uPass);
		if(logRes == 1){
			rr.userDetails(uName);
			System.out.println("You have successfully logged in");
			System.out.println("NAME :"+rr.userName);
			System.out.println("Gender :"+rr.userGender);
			System.out.println("Age : "+rr.userAge);
			System.out.println("Email :"+rr.userEmail);
		}
		
		else
			System.out.println("Sorry your password is wrong");

	}

}
