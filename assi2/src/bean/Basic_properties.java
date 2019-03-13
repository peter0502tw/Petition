package bean;

import java.awt.List;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonObject;


import bean.USER_property;
public class Basic_properties {

	private static Connection connect = null;
	private static String host = "mysql.mcscw3.le.ac.uk:3306";
	private static String database = "ylh9";
	private static String username = "ylh9";
	private static String password = "eetheeB7";

	public static Connection getConnection() {
		if (connect == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				String conn_string = "jdbc:mysql://" + host + "/" + database;
				Connection connect = DriverManager.getConnection(conn_string, username, password);
				System.out.println("mysql successful " + connect);
				return connect;
			} catch (Exception ex) {
				System.out.println("mysql fail " + connect);
				ex.printStackTrace();
				return null;
				//
			}
		} else {
			System.out.println("mysql 銝��� " + connect);
			return connect;
		}
	}

	public static void main(String[] args) throws SQLException {
		        //  直接格式化輸出現在時間的方法
	/*	Basic_properties a= new Basic_properties();
		USER_property user=new USER_property();
		user.set_name("sfdfgdfgdfdfggf");
		user.set_address("sdfddfgde00ejdifgjdiogdgfgdfgfgddfg");
		user.set_birthday("1993-05-09");
		user.set_email("sdf");
		System.out.println(a.insert_petition(user));*/
		
		
		//System.out.println(getpetition());
	}

	public Boolean insert_user(USER_property get_user) throws SQLException {
		String sql = "INSERT INTO `user`(`email`,`name`,`birthday`,`address`,`password`,`NIC`) VALUES(?,?,?,?,?,?);";
		// UPDATE `nic_records` set `USED` =1 where `NIC`=?; pstmt.setString(7,
		// get_user.get_NIC());

		Connection connect = getConnection();
		PreparedStatement pstmt = connect.prepareStatement(sql);
		try {
			pstmt.setString(1, get_user.get_email());
			pstmt.setString(2, get_user.get_name());
			pstmt.setString(3, get_user.get_birthday());
			pstmt.setString(4, get_user.get_address());
			pstmt.setString(5, HashGenerator.getSHA256(get_user.get_password()));
			pstmt.setString(6, get_user.get_NIC());
			int resultSet = pstmt.executeUpdate();
			// System.out.println(resultSet);
			if (resultSet == 1) {
				pstmt.close();
				connect.close();
				boolean result = update_state(get_user.get_NIC());
				if (result == true) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connect.close();
		}
		return false;
	}

	public boolean update_state(String USER_property) throws SQLException {
		// sert�隞� 蝛箇

		String sql = "UPDATE `nic_records` set `USED` =1 where `NIC`=?;";

		Connection connect = getConnection();
		PreparedStatement pstmt = connect.prepareStatement(sql);

		pstmt.setString(1, USER_property);
		int resultSet = pstmt.executeUpdate();
		pstmt.close();
		connect.close();
		if (resultSet == 1) {
			return true;
		} else {
			return false;
		}
			
		
	}



public static String getpetition_comments() throws SQLException{
	String sql="SELECT TITLE,CONTENT,DATE,SIGN FROM petitions";
	 Connection connect = getConnection(); 
    	 PreparedStatement pstmt = connect.prepareStatement(sql);
		{	
    	 try (ResultSet rs = pstmt.executeQuery();){
    		// System.out.println(rs.getrow);
    	   while(rs.next()){
    		   
    		   JsonObject json = new JsonObject();
    		   
    		   int i=0;
    			System.out.println(rs.getRow());   
    		   json.addProperty("CONTENT",rs.getString("CONTENT"));
    		   json.addProperty("DATE", rs.getString("DATE"));
    		   json.addProperty("SIGN", rs.getString("SIGN"));
    		   json.addProperty("TITLE", rs.getString("TITLE"));
    		   System.out.println(rs.getString("CONTENT"));
    		   
    		
    		   
    		   
    		/*   Gson gson=new Gson();
    		   HashMap<String, String> map=new HashMap<String,String>();
	    	map.put("CONTENT", rs.getString("CONTENT")) ;
	    	map.put("DATE", rs.getString("DATE")) ;
	    	map.put("SIGN", rs.getString("SIGN")) ;
	    	map.put("TITLE", rs.getString("TITLE")) ;*/
	  
	    
		  	return json.toString();
	    	  
	      }
    	 
	}catch(SQLException ex){
		ex.printStackTrace();	
	}finally {
		pstmt.close();
		connect.close();
	}	 
	return "";
}
}


public int insert_petition(USER_property get_user) throws SQLException {
	String sql = "INSERT INTO `petitions`(`TITLE`,`CONTENT`,`DATE`,`CREATOR`,`SIGN`) VALUES(?,?,?,?,0);";

	Connection connect = getConnection();
	PreparedStatement pstmt = connect.prepareStatement(sql);
	try {
		pstmt.setString(1, get_user.get_name());
		pstmt.setString(2, get_user.get_address());
		pstmt.setString(3, get_user.get_birthday());
		pstmt.setString(4, get_user.get_email());
		 System.out.println(get_user.get_name()+get_user.get_address()+get_user.get_birthday()+get_user.get_email());
		int resultSet = pstmt.executeUpdate();
		if (resultSet == 1) {
				return 1;
		} else {
			return 0;
		}

	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		pstmt.close();
		connect.close();
	}
	return 1;
}

}
/**/