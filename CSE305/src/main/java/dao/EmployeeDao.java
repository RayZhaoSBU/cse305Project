package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Employee;

public class EmployeeDao {
	/*
	 * This class handles all the database operations related to the employee table
	 */
	
	public String addEmployee(Employee employee) {

		/*
		 * All the values of the add employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the employee details and return "success" or "failure" based on result of the database insertion.
		 */
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String sqlstr = "SELECT P.Email, P.SSN FROM Person P WHERE P.Email = \"" + employee.getEmail() + "\"";
			conn = DBAccessHelper.getDAO().getConnection();
			ResultSet rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			//Connect to data base
			if(rs.next()){
				return "failure";
			}else {
				sqlstr =("INSERT INTO Person (SSN, LastName, FirstName, Address, City, State, ZipCode, Telephone, Email, Password) "
						 + " VALUES ( " + " '"
						 + employee.getEmployeeID() + "', '" 
						 + employee.getLastName()   + "', '"
						 + employee.getFirstName()  + "', '"
						 + employee.getAddress()    + "', '"
						 + employee.getCity()		+ "', '"
						 + employee.getState()   	+ "', "
						 + employee.getZipCode()  	+ ", '"
						 + employee.getTelephone()	+ "', '"
						 + employee.getEmail()		+ "', '"
						 + employee.getPassword() 	+ "' )");
				DBAccessHelper.getDAO().execute(sqlstr, conn);
				
				sqlstr = "INSERT INTO Employee (EmployeeID, EmployeeLvl, StartDate, HourlyRate) " + " VALUES ( "
						 + Integer.parseInt(employee.getEmployeeID()) + ", '"
						 + "Employee" 				+ "', '"
						 + employee.getStartDate()	+ "', "
						 + employee.getHourlyRate() + ")";
				DBAccessHelper.getDAO().execute(sqlstr, conn);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "failure";
		} finally {
			//close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	public String editEmployee(Employee employee) {
		/*
		 * All the values of the edit employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String sqlstr = "SELECT P.Email, P.SSN FROM Person P WHERE P.Email = \"" + employee.getEmail() + "\"";
			conn = DBAccessHelper.getDAO().getConnection();
			ResultSet rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			//Connect to data base
			if(rs.next()){
				sqlstr =("UPDATE Person"
						 + " SET LastName = '" 	+ employee.getLastName()   	+ "', "
						 + " FirstName = '" 	+ employee.getFirstName()  	+ "', "
						 + " Address = \"" 		+ employee.getAddress()    	+ "\", "
						 + " City = '" 			+ employee.getCity()		+ "', "
						 + " State = '" 		+ employee.getState()   	+ "', "
						 + " ZipCode = " 		+ employee.getZipCode()  	+ ", "
						 + " Telephone = '" 	+ employee.getTelephone()	+ "', "
						 + " Email = '" 		+ employee.getEmail()		+ "', "
						 + " Password = '" 		+ employee.getPassword() 	+ "' "
						 + " WHERE SSN = '"		+ employee.getEmployeeID()  + "' ");
				DBAccessHelper.getDAO().execute(sqlstr, conn);
				
				String startDate= employee.getStartDate();
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-mm-dd");
				java.util.Date date = sdf1.parse(startDate);
				java.sql.Date sqlStartDate = new java.sql.Date(date.getTime()); 
				System.out.print(sqlStartDate);
				sqlstr = "UPDATE Employee"
						 + " SET EmployeeLvl = '" 	+ employee.getLevel() 						 + "', "
						 + " StartDate = '" 		+ sqlStartDate								 + "', "
						 + " HourlyRate = " 		+ employee.getHourlyRate() 					 + " "
						 + " WHERE EmployeeID = "	+ Integer.parseInt(employee.getEmployeeID()) + " ";
				DBAccessHelper.getDAO().execute(sqlstr, conn);
				
			}else return "failure";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "failure";
		} finally {
			//close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	public String deleteEmployee(String employeeID) {
		/*
		 * employeeID, which is the Employee's ID which has to be deleted, is given as method parameter
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
//			String sqlstr = "SELECT P.SSN FROM Person P WHERE P.SSN = \"" + employeeID + "\"";
			conn = DBAccessHelper.getDAO().getConnection();
//			ResultSet rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			PreparedStatement st1 = conn.prepareStatement("DELETE FROM Person WHERE SSN = " + employeeID + ";");
			PreparedStatement st2 = conn.prepareStatement("DELETE FROM Employee WHERE EmployeeID = " + employeeID + ";");
			st1.executeUpdate(); 
			st2.executeUpdate();
		}catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "failure";
		} finally {
			//close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	
	public List<Employee> getEmployees() {

		/*
		 * The students code to fetch data from the database will be written here
		 * Query to return details about all the employees must be implemented
		 * Each record is required to be encapsulated as a "Employee" class object and added to the "employees" List
		 */

		List<Employee> employees = new ArrayList<Employee>();
		Connection conn = null;
		try {
			String sqlstr = "SELECT * FROM Employee E, Person P where E.EmployeeID = P.SSN";
			conn = DBAccessHelper.getDAO().getConnection();
			ResultSet rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			//Connect to data base
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmail(rs.getString("Email"));
				employee.setFirstName(rs.getString("FirstName"));
				employee.setLastName(rs.getString("LastName"));
				employee.setAddress(rs.getString("Address"));
				employee.setCity(rs.getString("City"));
				employee.setStartDate(rs.getDate("StartDate").toString());
				employee.setState(rs.getString("State"));
				employee.setZipCode(rs.getInt("ZipCode"));
				employee.setTelephone(rs.getString("Telephone"));
				employee.setEmployeeID(rs.getString("SSN"));
				employee.setHourlyRate(rs.getFloat("HourlyRate"));
				employee.setPassword(rs.getString("Password"));
				employees.add(employee);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		} finally {
			//close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		/*Sample data begins*/
		
		/*Sample data ends*/
		
		return employees;
	}

	public Employee getEmployee(String employeeID) {

		/*
		 * The students code to fetch data from the database based on "employeeID" will be written here
		 * employeeID, which is the Employee's ID who's details have to be fetched, is given as method parameter
		 * The record is required to be encapsulated as a "Employee" class object
		 */

		Employee employee = new Employee();
		Connection conn = null;
		try {
			String sqlstr = "SELECT *" + " FROM Employee E, Person P "
					+ " WHERE E.EmployeeID = P.SSN AND P.SSN = '" + employeeID + "'";
			/*String sqlstr = "SELECT E.EmployeeID FROM Employee E, Person P WHERE E.EmployeeID = P.SSN AND P.SSN = '" + employeeID + "'";*/
			conn = DBAccessHelper.getDAO().getConnection();
			ResultSet rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			//Connect to data base
			if (rs.next()) {
  				employee.setEmployeeID(rs.getString("SSN"));
				employee.setLastName(rs.getString("LastName"));
				employee.setFirstName(rs.getString("FirstName"));
				employee.setAddress(returnToBr(rs.getString("Address")));
				employee.setCity(returnToBr(rs.getString("City")));
				employee.setState(returnToBr(rs.getString("State")));
				employee.setZipCode(rs.getInt("ZipCode"));
				employee.setTelephone(rs.getString("Telephone"));
				employee.setEmail(rs.getString("Email"));
				employee.setPassword(rs.getString("Password"));
				employee.setLevel(rs.getString("EmployeeLvl"));
				employee.setStartDate(rs.getString("StartDate"));
				employee.setHourlyRate(rs.getFloat("HourlyRate"));
			}else return null;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		} finally {
			//close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}		
		/*Sample data begins*/
/*		employee.setEmail("shiyong@cs.sunysb.edu");
		employee.setFirstName("Shiyong");
		employee.setLastName("Lu");
		employee.setAddress("123 Success Street");
		employee.setCity("Stony Brook");
		employee.setStartDate("2006-10-17");
		employee.setState("NY");
		employee.setZipCode(11790);
		employee.setTelephone("5166328959");
		employee.setEmployeeID("631-413-5555");
		employee.setHourlyRate(100);*/
		/*Sample data ends*/
		
		return employee;
	}
	
	public Employee getHighestRevenueEmployee() {
		
		/*
		 * The students code to fetch employee data who generated the highest revenue will be written here
		 * The record is required to be encapsulated as a "Employee" class object
		 */
		
		Employee employee = new Employee();
		Connection conn = null;
		try {
			String sqlstr = "SELECT CRR.Monitor FROM CustomerRepRevenue CRR WHERE CRR.Revenue >= ALL(SELECT CRR1.Revenue FROM CustomerRepRevenue CRR1) ";
			conn = DBAccessHelper.getDAO().getConnection();
			ResultSet rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			//Connect to data base
			if (rs.next()) {
				employee = this.getEmployee(rs.getString("Monitor"));
			}else return null;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		} finally {
			//close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}/*Sample data begins*/
/*		employee.setEmail("shiyong@cs.sunysb.edu");
		employee.setFirstName("Shiyong");
		employee.setLastName("Lu");
		employee.setEmployeeID("631-413-5555");*/
		/*Sample data ends*/
		
		return employee;
	}

	public String getEmployeeID(String username) {
		
		Connection conn = null;
		String ID = "";
		try {
			String sqlstr = "SELECT P.SSN FROM Person P WHERE P.Email = \"" + username + "\"";
			conn = DBAccessHelper.getDAO().getConnection();
			ResultSet rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			//Connect to data base
			if (rs.next()) {
				ID = rs.getString("SSN");
			}else return null;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		} finally {
			//close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		/*
		 * The students code to fetch data from the database based on "username" will be written here
		 * username, which is the Employee's email address who's Employee ID has to be fetched, is given as method parameter
		 * The Employee ID is required to be returned as a String
		 */

		return ID;
	}
	
	private static String returnToBr(String str) { 
		if (str == null || str.equals("")) { 
			return str; 
		} 
		StringBuffer sTmp = new StringBuffer(); 
		int i = 0; 
		while (i <= str.length()-1) { 
			if (str.charAt(i) == '\n') { 
				sTmp = sTmp.append("<br>"); 
			} else if (str.charAt(i) == ' ') { 
				sTmp = sTmp.append("&nbsp;"); 
			}else if(str.charAt(i) == '\''){sTmp = sTmp.append("&#39");}
			else{ 
				sTmp = sTmp.append(str.substring(i,i+1)); 
			} 
			i++; 
		} 
		String S1;
		S1=sTmp.toString();
		return S1; 
	} 

}
