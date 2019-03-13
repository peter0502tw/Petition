package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert_comment {
	
	
	public static void main(String[] args) throws SQLException {
Insert_comment insert_comment=new Insert_comment();
insert_comment.insert_comment("1", "i don't like", "peter");
}

public int insert_comment(String id,String comment,String user) throws SQLException {
	int resultSet=0;
	String sql = "INSERT INTO `comments`(`ID`,`COMMENT`,`BY_MP`)VALUES(?,?,?)";


	Connection connect = Basic_properties.getConnection();
	PreparedStatement pstmt = connect.prepareStatement(sql);
	try {
		pstmt.setString(1, id);
		pstmt.setString(2, comment);
		pstmt.setString(3, user);
		resultSet = pstmt.executeUpdate();
		// System.out.println(resultSet);
		
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		pstmt.close();
		connect.close();
	}
	return resultSet;
	
}	
	
}
