package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Auction;
import model.Bid;
import model.Customer;
import model.Employee;
import model.Item;

public class AuctionDao {
	
	public List<Auction> getAllAuctions() {
		
		List<Auction> auctions = new ArrayList<Auction>();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Auction" class object and added to the "auctions" ArrayList
		 * Query to get data about all the auctions should be implemented
		 */
		
		Connection conn = null;
		try {
			String sqlstr = "SELECT * FROM Auction A, Bid B where A.AuctionID = B.AuctionID";
			conn = DBAccessHelper.getDAO().getConnection();
			ResultSet rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			//Connect to data base
			while (rs.next()) {
				for (int i = 0; i < 10; i++) {
					Auction auction = new Auction();
					auction.setAuctionID(rs.getInt("AuctionID"));
					auction.setBidIncrement(rs.getFloat("BidIncrement"));
					auction.setMinimumBid(rs.getFloat("MinimumBid"));
					auction.setCopiesSold(rs.getInt("Copies_Sold"));
					auction.setItemID(rs.getInt("ItemID"));
					String sqlstr2 = "SELECT B.BidPrice FROM Bid B WHERE B.AuctionID = " + rs.getInt("AuctionID") + " AND B.BidPrice >= ALL(SELECT B2.BidPrice FROM Bid B2) ";
					Connection conn2 = conn = DBAccessHelper.getDAO().getConnection();
					ResultSet rs2 = DBAccessHelper.getDAO().executeQuery(sqlstr2, conn2);
					if(rs2.next()) {
						auction.setClosingBid(rs2.getInt("BidPrice"));
						auction.setCurrentBid(rs2.getInt("BidPrice"));
						auction.setCurrentHighBid(rs2.getInt("BidPrice"));
						conn2.close();
					}else {
						conn2.close();
						return null;
					}
					auction.setReserve(10);
					auctions.add(auction);
				}
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
/*		for (int i = 0; i < 10; i++) {
			Auction auction = new Auction();
			auction.setAuctionID(1);
			auction.setBidIncrement(10);
			auction.setMinimumBid(10);
			auction.setCopiesSold(12);
			auction.setItemID(1234);
			auction.setClosingBid(120);
			auction.setCurrentBid(120);
			auction.setCurrentHighBid(120);
			auction.setReserve(10);
			auctions.add(auction);
		}*/
		/*Sample data ends*/
		
		return auctions;

	}
	

	public List<Auction> getAuctions(String customerID) {
		
		List<Auction> auctions = new ArrayList<Auction>();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Auction" class object and added to the "auctions" ArrayList
		 * Query to get data about all the auctions in which a customer participated should be implemented
		 * customerID is the customer's primary key, given as method parameter
		 */
		
		Connection conn = null;
		try {
			String sqlstr = "SELECT * FROM Auction A, Bid B where A.AuctionID = B.AuctionID AND B.CustomerID = \"" + customerID + "\"";
			conn = DBAccessHelper.getDAO().getConnection();
			ResultSet rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			//Connect to data base
			while (rs.next()) {
				for (int i = 0; i < 10; i++) {
					Auction auction = new Auction();
					auction.setAuctionID(rs.getInt("AuctionID"));
					auction.setBidIncrement(rs.getFloat("BidIncrement"));
					auction.setMinimumBid(rs.getFloat("MinimumBid"));
					auction.setCopiesSold(rs.getInt("Copies_Sold"));
					auction.setItemID(rs.getInt("ItemID"));
					String sqlstr2 = "SELECT B.BidPrice FROM Bid B WHERE B.AuctionID = " + rs.getInt("AuctionID") + " AND B.BidPrice >= ALL(SELECT B2.BidPrice FROM Bid B2) ";
					Connection conn2 = conn = DBAccessHelper.getDAO().getConnection();
					ResultSet rs2 = DBAccessHelper.getDAO().executeQuery(sqlstr2, conn2);
					if(rs2.next()) {
						auction.setClosingBid(rs2.getInt("BidPrice"));
						auction.setCurrentBid(rs2.getInt("BidPrice"));
						auction.setCurrentHighBid(rs2.getInt("BidPrice"));
						conn2.close();
					}else {
						conn2.close();
						return null;
					}
					auction.setReserve(10);
					auctions.add(auction);
				}
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
		
		
		
		
		/*Sample data begins
		for (int i = 0; i < 5; i++) {
			Auction auction = new Auction();
			auction.setAuctionID(1);
			auction.setBidIncrement(10);
			auction.setMinimumBid(10);
			auction.setCopiesSold(12);
			auction.setItemID(1234);
			auction.setClosingBid(120);
			auction.setCurrentBid(120);
			auction.setCurrentHighBid(120);
			auction.setReserve(10);
			auctions.add(auction);
		}
		Sample data ends*/
		
		return auctions;

	}

	public List<Auction> getOpenAuctions(String employeeEmail) {
		List<Auction> auctions = new ArrayList<Auction>();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Auction" class object and added to the "auctions" ArrayList
		 * Query to get data about all the open auctions monitored by a customer representative should be implemented
		 * employeeEmail is the email ID of the customer representative, which is given as method parameter
		 */
		
		/*Sample data begins*/
		
		Connection conn = null;
		try {
			String sqlstr = "SELECT * FROM Auction A, Bid B, Person P, Post Ps"
					+ " WHERE A.AuctionID = B.AuctionID "
					+ " AND A.Monitor = P.SSN "
					+ " AND P.Email = \"" + employeeEmail + "\" "
					+ " AND Ps.AuctionID = A.AuctionID "
					+ " AND Ps.ExpireDate > CURDATE()";
			System.out.println(sqlstr);
			conn = DBAccessHelper.getDAO().getConnection();
			ResultSet rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			//Connect to data base
			while (rs.next()) {
				for (int i = 0; i < 10; i++) {
					Auction auction = new Auction();
					auction.setAuctionID(rs.getInt("AuctionID"));
					auction.setBidIncrement(rs.getFloat("BidIncrement"));
					auction.setMinimumBid(rs.getFloat("MinimumBid"));
					auction.setCopiesSold(rs.getInt("Copies_Sold"));
					auction.setItemID(rs.getInt("ItemID"));
					String sqlstr2 = "SELECT B.BidPrice FROM Bid B WHERE B.AuctionID = " + rs.getInt("AuctionID") + " AND B.BidPrice >= ALL(SELECT B2.BidPrice FROM Bid B2) ";
					Connection conn2 = conn = DBAccessHelper.getDAO().getConnection();
					ResultSet rs2 = DBAccessHelper.getDAO().executeQuery(sqlstr2, conn2);
					if(rs2.next()) {
						auction.setClosingBid(rs2.getInt("BidPrice"));
						auction.setCurrentBid(rs2.getInt("BidPrice"));
						auction.setCurrentHighBid(rs2.getInt("BidPrice"));
						conn2.close();
					}else {
						conn2.close();
						return null;
					}
					auction.setReserve(10);
					auctions.add(auction);
				}
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
		
		/*
		for (int i = 0; i < 5; i++) {
			Auction auction = new Auction();
			auction.setAuctionID(1);
			auction.setBidIncrement(10);
			auction.setMinimumBid(10);
			auction.setCopiesSold(12);
			auction.setItemID(1234);
			auction.setClosingBid(120);
			auction.setCurrentBid(120);
			auction.setCurrentHighBid(120);
			auction.setReserve(10);
			auctions.add(auction);
		}
		Sample data ends*/
		
		return auctions;

		
		
	}

	public String recordSale(String auctionID) {
		/*
		 * The students code to update data in the database will be written here
		 * Query to record a sale, indicated by the auction ID, should be implemented
		 * auctionID is the Auction's ID, given as method parameter
		 * The method should return a "success" string if the update is successful, else return "failure"
		 */
		/* Sample data begins */
		return "success";
		/* Sample data ends */
	}

	public List getAuctionData(String auctionID, String itemID) {
		
		List output = new ArrayList();
		Item item = new Item();
		Bid bid = new Bid();
		Auction auction = new Auction();
		Customer customer = new Customer();
		
		/*
		 * The students code to fetch data from the database will be written here
		 * The item details are required to be encapsulated as a "Item" class object
		 * The bid details are required to be encapsulated as a "Bid" class object
		 * The auction details are required to be encapsulated as a "Auction" class object
		 * The customer details are required to be encapsulated as a "Customer" class object
		 * Query to get data about auction indicated by auctionID and itemID should be implemented
		 * auctionID is the Auction's ID, given as method parameter
		 * itemID is the Item's ID, given as method parameter
		 * The customer details must include details about the current winner of the auction
		 * The bid details must include details about the current highest bid
		 * The item details must include details about the item, indicated by itemID
		 * The auction details must include details about the item, indicated by auctionID
		 * All the objects must be added in the "output" list and returned
		 */
		
		Connection conn = null;
		try {
			String sqlstr = "SELECT * FROM Auction A, Bid B, Person P, Post Ps, Item I"
					+ " WHERE I.ItemID = " + Integer.parseInt(itemID)
					+ " AND A.AuctionID = " + Integer.parseInt(auctionID)
					+ " AND B.AuctionID = A.AuctionID "
					+ " AND B.BidPrice > ALL(SELECT B2.BidPrice FROM Bid B2) "
					+ " AND B.CustomerID = P.SSN ";

			conn = DBAccessHelper.getDAO().getConnection();
			ResultSet rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			//Connect to data base
			while (rs.next()) {
				
				while (rs.next()) {
					item.setItemID(rs.getInt("ItemID"));
					item.setDescription(rs.getString("Description"));
					item.setType(rs.getString("ItemType"));
					item.setName(rs.getString("ItemName"));
					
					bid.setCustomerID(rs.getString("CustomerID"));
					bid.setBidPrice(rs.getInt("BidPrice"));
					
					customer.setCustomerID(rs.getString("CustomerID"));
					customer.setFirstName(rs.getString("FirstName"));
					customer.setLastName(rs.getString("LastName"));
					
					auction.setMinimumBid(rs.getInt("MinimumBid"));
					auction.setBidIncrement(rs.getInt("BidIncrement"));
					auction.setCurrentBid(rs.getInt("BidPrice"));
					auction.setCurrentHighBid(rs.getInt("BidPrice"));
					auction.setAuctionID(Integer.parseInt(auctionID));
				}
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
		for (int i = 0; i < 4; i++) {
			item.setItemID(123);
			item.setDescription("sample description");
			item.setType("BOOK");
			item.setName("Sample Book");
			
			bid.setCustomerID("123-12-1234");
			bid.setBidPrice(120);
			
			customer.setCustomerID("123-12-1234");
			customer.setFirstName("Shiyong");
			customer.setLastName("Lu");
			
			auction.setMinimumBid(100);
			auction.setBidIncrement(10);
			auction.setCurrentBid(110);
			auction.setCurrentHighBid(115);
			auction.setAuctionID(Integer.parseInt(auctionID));
		}
		/*Sample data ends*/
		
		output.add(item);
		output.add(bid);
		output.add(auction);
		output.add(customer);
		
		return output;

	}

	
}
