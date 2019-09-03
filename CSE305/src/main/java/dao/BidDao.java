package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Bid;
import model.Customer;

public class BidDao {

	public List<Bid> getBidHistory(String auctionID) {

		List<Bid> bids = new ArrayList<Bid>();

		String sqlstr = "SELECT B.CustomerID, B.BidPrice, B.BidTime, B.AuctionID" + " FROM Bid B "
				+ " WHERE B.AuctionID = '" + auctionID + "' ";

		Connection conn = null;
		ResultSet rs = null;
		// Connect to data base
		try {
			conn = DBAccessHelper.getDAO().getConnection();
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);

			while (rs.next()) {
				Bid bid = new Bid();
				bid.setAuctionID(rs.getInt("AuctionID"));
				bid.setCustomerID(rs.getString("CustomerID"));
				bid.setBidTime(rs.getString("B.BidTime"));
				bid.setBidPrice(rs.getInt("BidPrice"));
				bids.add(bid);
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

		return bids;
	}

	public List<Bid> getAuctionHistory(String customerID) {

		List<Bid> bids = new ArrayList<Bid>();

		/*
		 * The students code to fetch data from the database Each record is required to
		 * be encapsulated as a "Bid" class object and added to the "bids" ArrayList
		 * customerID, which is the Customer's ID, is given as method parameter Query to
		 * get the bid history of all the auctions in which a customer participated,
		 * indicated by customerID, must be implemented
		 */

		String sqlstr = "SELECT B.BidPrice, B.BidTime, B.AuctionID " + " FROM Bid B " + " WHERE B.CustomerID = '"
				+ customerID + "' ";

		Connection conn = null;
		ResultSet rs = null;
		// Connect to data base
		try {
			conn = DBAccessHelper.getDAO().getConnection();
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);

			if (rs.next()) {
				Bid bid = new Bid();
				bid.setAuctionID(rs.getInt("AuctionID"));
				bid.setCustomerID(""+customerID);
				bid.setBidTime(rs.getString("B.BidTime"));
				bid.setBidPrice(rs.getInt("BidPrice"));
				bids.add(bid);
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

		return bids;
	}

	public Bid submitBid(String auctionID, String itemID, Float currentBid, Float maxBid, String customerID) {

		Bid bid = new Bid();

		/*
		 * The students code to insert data in the database auctionID, which is the
		 * Auction's ID for which the bid is submitted, is given as method parameter
		 * itemID, which is the Item's ID for which the bid is submitted, is given as
		 * method parameter currentBid, which is the Customer's current bid, is given as
		 * method parameter maxBid, which is the Customer's maximum bid for the item, is
		 * given as method parameter customerID, which is the Customer's ID, is given as
		 * method parameter Query to submit a bid by a customer, indicated by
		 * customerID, must be implemented After inserting the bid data, return the bid
		 * details encapsulated in "bid" object
		 */
		
		Connection conn = null;
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH); 
		int date = c.get(Calendar.DATE); 
		
		String currTime = year + "-" + month + "-" + date;
				
		try {
			String sqlstr = 
					"INSERT INTO `CSE305_PROJ`.`Bid` (`ItemID`, `CustomerID`, `AuctionID`, `BidTime`, `BidPrice`, `MaxBid`)" +
					"VALUES( " + " '" + 
					itemID + "',  '" + 
					customerID + "', '" +
					auctionID + "', '" +
					currTime + "',  '" + 
					currentBid + "', '" +
					maxBid +"' )";
			//Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			//executeQuery string 
			DBAccessHelper.getDAO().execute(sqlstr, conn);
						
		} finally {
			//close connection
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		bid.setAuctionID(Integer.parseInt(auctionID));
		bid.setCustomerID(customerID);
		bid.setBidTime(currTime);
		bid.setBidPrice(currentBid);
		bid.setMaxBid(maxBid);
		
		return bid;
	}

	public List<Bid> getSalesListing(String searchKeyword) {

		List<Bid> bids = new ArrayList<Bid>();

		/*
		 * The students code to fetch data from the database Each record is required to
		 * be encapsulated as a "Bid" class object and added to the "bids" ArrayList
		 * searchKeyword, which is the search parameter, is given as method parameter
		 * Query to produce a list of sales by item name or by customer name must be
		 * implemented The item name or the customer name can be searched with the
		 * provided searchKeyword
		 */

		Connection conn = null;
		ResultSet rs = null;
				
		try {
			String sqlstr = 
					" SELECT B.BidPrice, B.BidTime, B.CustomerID, B.AuctionID" +
					" FROM Item I, Bid B, Auction A WHERE I.ItemID = A.ItemID AND B.AuctionID = A.AuctionID AND ( I.ItemName LIKE '%" + 
					searchKeyword + "%' ) AND B.BidPrice >= ALL (SELECT B2.BidPrice FROM Bid B2 WHERE B.AuctionID = B2.AuctionID)";
			//Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			//executeQuery string 
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			
			while (rs.next() &&rs.getString("AuctionID")!=null) {
				Bid bid = new Bid();
				bid.setAuctionID(+ rs.getInt("AuctionID"));
				bid.setCustomerID(rs.getString("CustomerID"));
				bid.setBidTime(rs.getString("BidTime"));
				bid.setBidPrice(rs.getFloat("BidPrice"));
				bids.add(bid);
			}
		}catch (SQLException e) {
			// close connection
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
		
		
		/* Sample data begins */

		/* Sample data ends */

		return bids;
	}

}
