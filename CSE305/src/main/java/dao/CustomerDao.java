package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import java.util.stream.IntStream;

public class CustomerDao {
	/*
	 * This class handles all the database operations related to the customer table
	 */

	/**
	 * @param String searchKeyword
	 * @return ArrayList<Customer> object
	 */
	public List<Customer> getCustomers(String searchKeyword) {
		/*
		 * This method fetches one or more customers based on the searchKeyword and
		 * returns it as an ArrayList
		 */

		List<Customer> customers = new ArrayList<Customer>();
		
		/*
		 * The students code to fetch data from the database based on searchKeyword will
		 * be written here Each record is required to be encapsulated as a "Customer"
		 * class object and added to the "customers" List
		 */

		/* Sample data begins */

		Connection conn = null;
		try {
			if(searchKeyword==null) {
				String sqlgetAllCus = "SELECT * FROM Person P, Customer C WHERE P.SSN = C.CustomerID";
				ResultSet rs = null;
				// Connect to data base
				conn = DBAccessHelper.getDAO().getConnection();
				// executeQuery string
				rs = DBAccessHelper.getDAO().executeQuery(sqlgetAllCus, conn);
				while (rs.next()) {
					Customer customer = new Customer();
					customer.setCustomerID(rs.getString("SSN"));
					customer.setAddress(rs.getString("Address"));
					customer.setLastName(rs.getString("LastName"));
					customer.setFirstName(rs.getString("FirstName"));
					customer.setCity(rs.getString("City"));
					customer.setState(rs.getString("State"));
					customer.setEmail(rs.getString("Email"));
					customer.setZipCode(rs.getInt("ZipCode"));
					customer.setTelephone(rs.getString("Telephone"));
					customer.setCreditCard(rs.getString("CreditCard"));
					customer.setRating(rs.getInt("Rating"));
					customers.add(customer);
				}
				
			}
			else {

			String select = "P.SSN, P.Address, P.LastName, P.FirstName, P.City, P.State, P.Email, P.ZipCode, P.Telephone, P.CreditCard, P.Rating ";
			String from = "(SELECT * FROM Person INNER JOIN Customer ON Customer.CustomerID = Person.SSN) P ";
			String Where = "P.SSN LIKE '%" + searchKeyword + "%' OR P.Address LIKE '%" + searchKeyword
					+ "%' OR P.LastName LIKE '%" + searchKeyword + "%' OR P.FirstName LIKE '%" + searchKeyword
					+ "%' OR P.City LIKE '%" + searchKeyword + "%' OR P.State LIKE '%" + searchKeyword
					+ "%' OR P.ZipCode LIKE '%" + searchKeyword + "%' OR P.Telephone LIKE '%" + searchKeyword
					+ "%' OR P.CreditCard LIKE '%" + searchKeyword + "%' OR P.Rating = '" + searchKeyword + "'";

			String sqlstr = "SELECT " + select + " FROM " + from + " WHERE " + Where;

			ResultSet rs = null;
			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(rs.getString("SSN"));
				customer.setAddress(rs.getString("Address"));
				customer.setLastName(rs.getString("LastName"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setCity(rs.getString("City"));
				customer.setState(rs.getString("State"));
				customer.setEmail(rs.getString("Email"));
				customer.setZipCode(rs.getInt("ZipCode"));
				customer.setTelephone(rs.getString("Telephone"));
				customer.setCreditCard(rs.getString("CreditCard"));
				customer.setRating(rs.getInt("Rating"));
				customers.add(customer);
			}
			}
		} catch (SQLException e) {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		/* Sample data ends */

		return customers;
	}

	public Customer getHighestRevenueCustomer() {
		/*
		 * This method fetches the customer who generated the highest total revenue and
		 * returns it The students code to fetch data from the database will be written
		 * here The customer record is required to be encapsulated as a "Customer" class
		 * object
		 */
		Connection conn = null;
		Customer customer = null;
		try {

			String sqlstr = "SELECT CR.CustomerID, P.LastName, P.FirstName, P.Email "
					+ " FROM CustomerRevenue CR, Person P	"
					+ " WHERE CR.Revenue >= ALL (SELECT CR1.Revenue FROM CustomerRevenue CR1) AND CR.CustomerID = P.SSN";

			ResultSet rs = null;
			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);

			if (rs.next()) {
				customer = new Customer();
				customer.setCustomerID(rs.getString("CustomerID"));
				customer.setLastName(rs.getString("CustomerID"));
				customer.setFirstName(rs.getString("CustomerID"));
				customer.setEmail("shiyong@cs.sunysb.edu");
				/* Sample data ends */
			}
		} catch (SQLException e) {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return customer;
	}

	public List<Customer> getCustomerMailingList() {

		/*
		 * This method fetches the all customer mailing details and returns it The
		 * students code to fetch data from the database will be written here Each
		 * customer record is required to be encapsulated as a "Customer" class object
		 * and added to the "customers" List
		 */

		List<Customer> customers = new ArrayList<Customer>();

		Connection conn = null;
		try {
			String sqlstr = 
					"SELECT P.SSN, P.LastName, P.FirstName, P.Address, P.Email, P.City, P.State, P.ZipCode " + 
					"FROM Customer C, Person P " + 
					"WHERE C.CustomerID = P.SSN ";

			ResultSet rs = null;
			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);

			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(rs.getString("SSN"));
				customer.setAddress(rs.getString("Address"));
				customer.setLastName(rs.getString("LastName"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setCity(rs.getString("City"));
				customer.setState(rs.getString("State"));
				customer.setEmail(rs.getString("Email"));
				customer.setZipCode(rs.getInt("ZipCode"));
				customers.add(customer);
			}

		} catch (SQLException e) {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		/* Sample data begins */

		/* Sample data ends */

		return customers;
	}

	public Customer getCustomer(String customerID) {

		/*
		 * This method fetches the customer details and returns it customerID, which is
		 * the Customer's ID who's details have to be fetched, is given as method
		 * parameter The students code to fetch data from the database will be written
		 * here The customer record is required to be encapsulated as a "Customer" class
		 * object
		 */
		Customer customer = null;
		Connection conn = null;
		try {
			String sqlstr = 
					"SELECT P.SSN, P.LastName, P.FirstName, P.Address, P.Email, P.City, P.State, P.ZipCode, P.Telephone, C.CreditCard, C.Rating "
					+ " FROM Customer C, Person P "
					+ " WHERE C.CustomerID = P.SSN AND C.CustomerID = '" + customerID + "'";


			ResultSet rs = null;
			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);

			if (rs.next()) {
				customer = new Customer();
				customer.setCustomerID(rs.getString("SSN"));
				customer.setAddress(returnToBr(rs.getString("Address")));
				customer.setLastName(rs.getString("LastName"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setCity(returnToBr(rs.getString("City")));
				customer.setState(returnToBr(rs.getString("State")));
				customer.setEmail(rs.getString("Email"));
				customer.setZipCode(rs.getInt("ZipCode"));
				customer.setTelephone(rs.getString("Telephone"));
				customer.setCreditCard(rs.getString("CreditCard"));
				customer.setRating(rs.getInt("Rating"));
			}
			System.out.println(customer.toString());

		} catch (SQLException e) {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

		return customer;
	}

	public String deleteCustomer(String customerID) {

		/*
		 * This method deletes a customer returns "success" string on success, else
		 * returns "failure" The students code to delete the data from the database will
		 * be written here customerID, which is the Customer's ID who's details have to
		 * be deleted, is given as method parameter
		 */
		Connection conn = null;
		try {
			String sqlstr = "DELETE FROM Customer C, Person P WHERE C.CustomerID = P.SSN AND C.CustomerID = "
					+ customerID;

			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			// executeQuery string
			DBAccessHelper.getDAO().execute(sqlstr, conn);

		} finally {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		/* Sample data begins */
		return "success";
		/* Sample data ends */

	}

	public String getCustomerID(String username) {
		String customerID = null;
		Connection conn = null;
		try {
			String sqlstr = "SELECT C.CustomerID " + " FROM Customer C, Person P "
					+ " WHERE C.CustomerID = P.SSN AND P.Email = '" + username + "'";

			ResultSet rs = null;
			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);

			if (rs.next()) {
				customerID = rs.getString("CustomerID");
			}

		} catch (SQLException e) {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return "failure";
		} finally {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return customerID;
	}

	public List<Customer> getSellers() {

		/*
		 * This method fetches the all seller details and returns it The students code
		 * to fetch data from the database will be written here The seller (which is a
		 * customer) record is required to be encapsulated as a "Customer" class object
		 * and added to the "customers" List
		 */

		List<Customer> customers = new ArrayList<Customer>();

		Connection conn = null;
		try {
			String sqlstr = "SELECT DISTINCT P.SSN, P.LastName, P.FirstName, P.Address, P.Email, P.City, P.State, P.ZipCode "
					+ " FROM Post PS, Customer C, Person P " + " WHERE PS.CustomerID = P.SSN AND C.CustomerID = P.SSN ";

			ResultSet rs = null;
			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);

			while (rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerID(rs.getString("SSN"));
				customer.setAddress(rs.getString("Address"));
				customer.setLastName(rs.getString("LastName"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setCity(rs.getString("City"));
				customer.setState(rs.getString("State"));
				customer.setEmail(rs.getString("Email"));
				customer.setZipCode(rs.getInt("ZipCode"));
				customers.add(customer);
			}

		} catch (SQLException e) {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return customers;
	}

	public String addCustomer(Customer customer) {

		Connection conn = null;
		
		if(customer.getTelephone().length() > 11) {
			System.out.println("failure telephone " + customer.getTelephone().length());
			return "failure";
		}else if(customer.getZipCode() > 99999){
			System.out.println("failure zipcode " + customer.getZipCode());
			return "failure";
		}else if (customer.getCustomerID().length() > 9) {
			return "failure";
		}else if (customer.getCreditCard().length() > 16) {
			return "failure";
		}
		
		
		try {
			String sqlstrPerson = 
						"INSERT INTO Person (SSN, LastName, FirstName, Address, City, State, ZipCode, Telephone, Email, Password) " + 
						" VALUES ( " + " '" + 
						customer.getCustomerID() + "',  '" + 
						customer.getLastName() + "', '" +
						customer.getFirstName() + "', '" +
						customer.getAddress() + "',  '" + 
						customer.getCity() + "', '" +
						customer.getState() + "', " +
						customer.getZipCode() + ", '" +
						customer.getTelephone() + "', '" +
						customer.getEmail() + "', '" +
						customer.getPassword() +"' )";
	
			String sqlstrCustomer = 
					"INSERT INTO Customer (CustomerID, CreditCard, Rating) " + 
					" VALUES ( " + " '" + 
					customer.getCustomerID() + "', '" +
					customer.getCreditCard() + "', " +
					customer.getRating() + " )";
			
			//Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			//executeQuery string 
			DBAccessHelper.getDAO().execute(sqlstrPerson, conn);
			DBAccessHelper.getDAO().execute(sqlstrCustomer, conn);
			
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

	public String editCustomer(Customer customer) {
		Connection conn = null;
		String customerID = customer.getCustomerID();
		
		
		if(customer.getTelephone().length() > 11) {
			System.out.println("failure telephone " + customer.getTelephone().length());
			return "failure";
		}else if(customer.getZipCode() > 99999){
			System.out.println("failure zipcode " + customer.getZipCode());
			return "failure";
		}else if (customer.getCustomerID().length() > 9) {
			return "failure";
		}else if (customer.getCreditCard().length() > 16) {
			return "failure";
		}
		
		
		try {
			String sqlstrPerson = 
						"UPDATE Person " + 
						" SET SSN = '" + customer.getCustomerID() 
						+ "',  LastName = '" + customer.getLastName()
						+ "', FirstName = '" + customer.getFirstName()
						+ "', Address = '" + customer.getAddress()
						+ "',  City = '" + customer.getCity()
						+ "', State = '" + customer.getState()
						+ "', ZipCode = " + customer.getZipCode()
						+ ", Telephone = '" + customer.getTelephone()
						+ "', Email = '" +	customer.getEmail() + "' WHERE SSN = '" +  customerID + "' ";
//						+ "', '" +	customer.getPassword() + "' WHERE SSN = '" +  customerID + "' )";
	
			String sqlstrCustomer = 
					"UPDATE Customer " + 
					" SET  CustomerID = '" + customer.getCustomerID() 
					+ "', CreditCard = '" +	customer.getCreditCard()
					+ "', Rating = " + customer.getRating() 
					+ " WHERE CustomerID = '" +  customerID + "' ";
			
			//Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			//executeQuery string 
			DBAccessHelper.getDAO().execute(sqlstrPerson, conn);
			DBAccessHelper.getDAO().execute(sqlstrCustomer, conn);
			
		} finally {
			//close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	
		/* Sample data begins */
		return "success";
		/* Sample data ends */

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



