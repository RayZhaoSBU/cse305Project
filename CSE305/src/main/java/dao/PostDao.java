package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Employee;
import model.Item;
import model.Post;

public class PostDao {

	
	public List<Item> getSalesReport(Post post) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Item" class object and added to the "items" List
		 * Query to get sales report for a particular month must be implemented
		 * post, which has details about the month and year for which the sales report is to be generated, is given as method parameter
		 * The month and year are in the format "month-year", e.g. "10-2018" and stored in the expireDate attribute of post object
		 * The month and year can be accessed by getter method, i.e., post.getExpireDate()
		 */
		
		List<Item> items = new ArrayList<Item>();
				
		
		Connection conn = null;
		try {

			String select = " I.ItemName, S.SoldPrice ";
			String from = " Sold S, Item I, Auction A, Post P ";
			String where = " S.AuctionID = A.AuctionID AND A.ItemID = I.ItemID AND P.AuctionID = A.AuctionID AND "
							+ " P.ExpireDate > '" + post.getExpireDate() + "-1' AND P.ExpireDate <= '" + post.getExpireDate()  + "-30' ";

			String sqlstr = "SELECT " + select + " FROM " + from + " WHERE " + where;
			System.out.println(sqlstr);
			ResultSet rs = null;
			// Connect to data base
			conn = DBAccessHelper.getDAO().getConnection();
			// executeQuery string
			rs = DBAccessHelper.getDAO().executeQuery(sqlstr, conn);
			while (rs.next()){
				Item item = new Item();
				item.setName(rs.getString("ItemName"));
				item.setSoldPrice(rs.getInt("SoldPrice"));
				items.add(item);
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
				
		return items;
		
	}
}
