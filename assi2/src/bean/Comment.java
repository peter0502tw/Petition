package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Comment {

	public static void main(String[] args) throws SQLException {

	 System.out.println(view_comment("hey")); 
	 
	 
}
	
	
	public static String view_comment(String title) throws SQLException {
		String aa="";
		String sql="SELECT COMMENT,BY_MP FROM petitions,comments where comments.ID=petitions.id && TITLE=?";
	 Connection connect = Basic_properties.getConnection(); 
	 
		PreparedStatement pstmt = connect.prepareStatement(sql);
		try {
			pstmt.setString(1, title);
			ResultSet resultSet = pstmt.executeQuery();	
			if(resultSet.next())
			{
			String Comm =resultSet.getString("COMMENT"); 
			String MP =resultSet.getString("BY_MP");
			aa=Comm+" <p>commented by:"+MP;
			}
			else {aa="MPs haven't commented";}
		}
			catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connect.close();
		}
		
		return aa;
	
	
	
	}
	
}
