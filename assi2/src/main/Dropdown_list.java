package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Basic_properties;

/**
 * Servlet implementation class Dropdown_list
 */
@WebServlet("/Dropdown_list")
public class Dropdown_list extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Dropdown_list() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PreparedStatement pstmt;
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		String sql="SELECT TITLE,id FROM petitions";
		 Connection connect = Basic_properties.getConnection(); 
	    	
			try {
				pstmt = connect.prepareStatement(sql);
				
				ResultSet rs = pstmt.executeQuery();
				
	    	   while(rs.next()){
	    		   String title=rs.getString("TITLE");
	    		   String id=rs.getString("id");
	    		   out.print("<option id='"+id+"' value='"+id+"'>"+title+"</option>"); 
	    	   }
	    	   out.print("</select>");
	    	   
	
	    	   pstmt.close();
	    		connect.close();
	    		} catch (SQLException e) {
		// TODO �۰ʲ��ͪ� catch �϶�
		e.printStackTrace();
	}
	}//out.print("<select id='dropdown' name='dropdown'>" +"<option value='-1' >choose</option>\r\n" + "\r\n");
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
