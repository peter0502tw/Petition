package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.TitlePaneLayout;

import com.sun.javafx.scene.layout.region.Margins.Converter;

import bean.Basic_properties;
import bean.USER_property;

/**
 * Servlet implementation class Insert_petition
 */
@WebServlet("/Insert_petition")
public class Insert_petition extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert_petition() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Date d = new Date();  
        SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd");//12小时制  
        
		PrintWriter out = res.getWriter();
		String title =req.getParameter("title");
		String content = req.getParameter("content");
		String date =ss.format(d);
		String user_email = req.getParameter("user");
		USER_property user = new USER_property();
	
		user.set_email(user_email);
		user.set_name(title);
		user.set_address(content);
		user.set_birthday(date);

				Basic_properties insert = new Basic_properties();

				// 嚙緻嚙踝蕭
				try {
					int result = insert.insert_petition(user);
					System.out.println("boolean petition "+result);
						out.println(result);
					
				} catch (SQLException  e) {
					// TODO 自動產生的 catch 區塊
					e.printStackTrace();
				}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
