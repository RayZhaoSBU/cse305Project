package dao;

import java.util.HashMap;

import model.Login;

public class LoginTable {
	static private HashMap<String, Login> table; 
	private static LoginTable login_table;
	
	private LoginTable() {
		table = new HashMap<String, Login>();
	}
	
	static public LoginTable getTable() {
		if (table == null) {
			login_table = new LoginTable();
		}
		return login_table;
	}
	
	public void put(Login login) {
		this.table.put(login.getUsername(), login);
	}
	public void del(String username) {
		this.table.remove(username);
	}
	public Login get(String username) {
		return table.get(username);
	}
	public String getRole(String username) {
		return table.get(username).getRole();
	}
}
