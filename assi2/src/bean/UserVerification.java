package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.Check_NIC;

public class UserVerification {

	public static void main(String[] args) throws SQLException {

		// System.out.println(check_NIC("8RL4ENTK"));
	System.out.println(check_login("peter0502tw@gmail.com", "12e"));
	}

	public static int check_NIC(String NIC) throws SQLException {
		int check = 0;// no records
		// System.out.println (" 抓到的NIC "+);
		Connection connect = Basic_properties.getConnection();
		String sql = "select * from nic_records where NIC=?";
		PreparedStatement prest = connect.prepareStatement(sql);
		try {
			prest.setString(1, NIC);
			ResultSet resultSet = prest.executeQuery();
			if (resultSet.first() == true) {
				// System.out.println(resultSet.getInt("USED"));
				if (resultSet.getInt("USED") == 0) {
					check = 2;
				} // can be used
				else {
					check = 1;
				} // used
			}

		} finally {
			connect.close();
			prest.close();
		}
		System.out.println(" valid NIC?(0,1,2) " + check);
		return check;
	}
	
	public static int check_email(String email) throws SQLException {
		int check = 1;// no records
		Connection connect = Basic_properties.getConnection();
		String sql = "SELECT email FROM user where email=?";
		PreparedStatement prest = connect.prepareStatement(sql);
		
		prest.setString(1, email);
		
		try {		
			ResultSet resultSet = prest.executeQuery();
			//System.out.println("信箱是" + resultSet.getString("email"));
			if (resultSet.next()) {			
				check = 1;
				System.out.println("信箱是"+check);
			} // can't be used
			else {
				check = 0;
				System.out.println("信箱是" + check);
			} // can be used
		}

		finally {
			connect.close();
			prest.close();
		}
		
		//System.out.println(" valid email(true(can)/false(can't)) " + check);
		return check;
	}
	
	
	public static USER_property check_login(String email,String paswd) throws SQLException {
		USER_property u=null;
		Connection connect = Basic_properties.getConnection();
		String sql = "select email,password,nic_records.NIC,MP,name from user ,nic_records where email=? && password=? && nic_records.NIC=user.NIC;";
		PreparedStatement prest = connect.prepareStatement(sql);
		try {
			prest.setString(1, email);
			prest.setString(2,HashGenerator.getSHA256(paswd));
			ResultSet resultSet = prest.executeQuery();
			while(resultSet.next()){    		 
		    	  String email1=resultSet.getString("email");		
		    	  String password=resultSet.getString("password");
		    	  String fullname=resultSet.getString("name");
		    	  int MP=resultSet.getInt("MP");  	
		    	  String NIC=resultSet.getString("NIC");
		    	  u=new USER_property(email1,password,NIC,MP,fullname);
		    	  break;
		    	
		      }

		} finally {
			connect.close();
			prest.close();
		}
		return u;
	}
	
	
}
