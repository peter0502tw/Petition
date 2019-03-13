package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Check_7days {
	
	
	
	public static void main(String[] args) throws SQLException, ParseException {

	System.out.println(Check_7days.check_day("Universal Basic Income "));
	}

	
	public static String check_day(String title) throws SQLException, ParseException{
		String differ="";
		int different_day;
		Date d = new Date();  
	String sql="SELECT DATE,SIGN FROM petitions where TITLE=?";
	 Connection connect = Basic_properties.getConnection(); 
  	
		
			PreparedStatement pstmt = connect.prepareStatement(sql);
			pstmt.setString(1,title);
			ResultSet rs = pstmt.executeQuery();

			
			
    	   while(rs.next()){
    		   different_day=differentDays(rs.getDate("DATE"),d);
    		  int num= rs.getInt("SIGN");
    	   if(different_day>7 && num<2) {differ="style=\"display:none\"";}}
    	   return differ;
    	   
	}
	public static int differentDays(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }



}
