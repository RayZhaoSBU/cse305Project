package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import model.Login;

public class LoginDao {
	/*
	 * This class handles all the database operations related to login functionality
	 */
	enum Role {
		Manager, 
		Employee,
		Customer
	}
	
	public Login login(String username, String password) {
		/*
		 * Return a Login object with role as "manager", "customerRepresentative" or "customer" if successful login
		 * Else, return null
		 * The role depends on the type of the user, which has to be handled in the database
		 * username, which is the email address of the user, is given as method parameter
		 * password, which is the password of the user, is given as method parameter
		 * Query to verify the username and password and fetch the role of the user, must be implemented
		 */
		Login login = new Login();

		
		Connection conn = null;
		try {
			String sqlstrEployee = "SELECT P.Email, P.SSN, E.EmployeeLvl FROM Person P,  Employee E WHERE P.Email = \"" + username + "\" AND P.Password = \"" + password + "\" AND P.SSN = E.EmployeeID";
			String sqlstrCustomer = "SELECT P.Email, P.SSN FROM Person P, Customer C WHERE P.Email = \"" + username + "\" AND P.Password = \"" + password + "\" AND  C.CustomerID = P.SSN";
			ResultSet rs = null;
			//Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			//executeQuery string 
			if((rs = DBAccessHelper.getDAO().executeQuery(sqlstrEployee, conn)).next()){	
				if(rs.getString("EmployeeLvl").equals("Manager")) {
					login.setRole("manager");
				}else {
					login.setRole("customerRepresentative");
				}
			}else if((rs = DBAccessHelper.getDAO().executeQuery(sqlstrCustomer, conn)).next()) {
				login.setRole("customer");
			} else
				return null;
			login.setUsername(rs.getString("Email"));
		} catch (SQLException e) {
			//close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			//close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		//login.setRole("manager");
		return login;
		
	}
	
	public String addUser(Login login) {
		/*
		 * Query to insert a new record for user login must be implemented
		 * login, which is the "Login" Class object containing username and password for the new user, is given as method parameter
		 * The username and password from login can get accessed using getter methods in the "Login" model
		 * e.g. getUsername() method will return the username encapsulated in login object
		 * Return "success" on successful insertion of a new user
		 * Return "failure" for an unsuccessful database operation
		 */
		if (login == null) {
			return "failure";
		}else {

			LoginTable.getTable().put(login);
		}
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/
	}

}
