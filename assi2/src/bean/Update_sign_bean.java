package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Update_sign_bean {
	
	public static void main(String[] args) throws SQLException {
		//set_sign("peter","Universal Basic Income ");
	 //System.out.println(check_sign("peter","Universal Basic Income "));
	// System.out.println(set_sign("peter","2"));
	 System.out.println(signed_number("1")); 
	 
	 
}

	public static  int check_sign(String user,String title) throws SQLException {
		int ch=123;
		String sql="SELECT * FROM update_sign where user=? && title=?";
		 Connection connect = Basic_properties.getConnection(); 
		 PreparedStatement pstmt = connect.prepareStatement(sql);
		 pstmt.setString(1, user);
		 pstmt.setString(2, title);
		 ResultSet rs = pstmt.executeQuery();
		 if(!rs.first())ch=0;
		 return ch;
				

		
	}
	
	public static  int set_sign(String user,String title) throws SQLException {
		String sql="UPDATE petitions SET SIGN=SIGN+'1' WHERE title=?";
		 Connection connect = Basic_properties.getConnection(); 
		 PreparedStatement pstmt = connect.prepareStatement(sql);
		 pstmt.setString(1, title);
		 int rs = pstmt.executeUpdate();
		 
				try {
					
					if(rs==1) {
					connect.close();
					pstmt.close();
					if(set_user_signed(user, title)==1) {
					return 1;	
					}
					else return 0;
					}
					
				} catch (SQLException e) {
					// TODO 自動產生的 catch 區塊
					e.printStackTrace();
				}
				
				
		return 0;
		
	}
	public static int set_user_signed(String user,String title) throws SQLException {
		String sql="INSERT INTO `update_sign`(`user`,`title`,`signed`)VALUES(?,?,1)";
	 Connection connect = Basic_properties.getConnection(); 
	 
		PreparedStatement pstmt = connect.prepareStatement(sql);
		try {
			pstmt.setString(1, user);
			pstmt.setString(2, title);
			int resultSet = pstmt.executeUpdate();		
			return resultSet;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connect.close();
		}
		return 0;
	
	
	
	}
	
	public static int signed_number(String title) throws SQLException {
		int aa=899;
		String sql="SELECT sign FROM petitions where title=?";
	 Connection connect = Basic_properties.getConnection(); 
	 
		PreparedStatement pstmt = connect.prepareStatement(sql);
		try {
			pstmt.setString(1, title);
			ResultSet resultSet = pstmt.executeQuery();	
			if(resultSet.next())
			aa=resultSet.getInt("sign"); 
			//return Integer.getInteger();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pstmt.close();
			connect.close();
		}
		return aa;
	
	
	
	}
	
	
}
