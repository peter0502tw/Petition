package main;



import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import bean.Basic_properties;
import bean.Check_7days;
@WebServlet("/Get_Petition")
public class Get_Petition  extends HttpServlet  {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	PreparedStatement pstmt;
	public void doGet(HttpServletRequest req, 
			        HttpServletResponse res) 
	            throws ServletException, IOException{
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		String sql="SELECT TITLE,CONTENT,DATE,SIGN FROM petitions";
		 Connection connect = Basic_properties.getConnection(); 
	    	
			try {
				pstmt = connect.prepareStatement(sql);
				
				ResultSet rs = pstmt.executeQuery();
				
				
	    	   while(rs.next()){
	    		   rs.getDate("DATE");
	    		   String expire=Check_7days.check_day(rs.getString("TITLE"));
	    		   
	    		   
	    		   out.println("<tr><td id=\""+rs.getString("TITLE")+"\">"+rs.getString("TITLE")+"</td><td>"+
	    				   rs.getString("CONTENT")+"</td><td>"+  
	    				   rs.getString("DATE")+"<br><br></td><td id=\""+rs.getString("TITLE")+"\">"+  
	    				   rs.getString("SIGN")+"</td>"+"<td><button "+expire+" onclick=\"click_num('"+rs.getString("TITLE")+"')\" id='"+rs.getString("TITLE")+"'>sign</button></td>");  
out.println("<td><button  onclick=\"comment('"+rs.getString("TITLE")+"')\" id='"+rs.getString("TITLE")+"'>view comment</button></td>");

	    	   }	   
	    	  	}catch(SQLException | ParseException ex){
			ex.printStackTrace();	
		}	 
			
			out.close();
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO �۰ʲ��ͪ� catch �϶�
				e.printStackTrace();
			} 
 		   try {
			connect.close();
		} catch (SQLException e) {
			// TODO �۰ʲ��ͪ� catch �϶�
			e.printStackTrace();
		}  	  
	}
		
		
	public void doPost(HttpServletRequest req, 
				HttpServletResponse res) 
	            throws ServletException, IOException {
		doGet(req, res);
	}
}

