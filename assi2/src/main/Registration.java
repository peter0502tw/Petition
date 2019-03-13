package main;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.Thread;

import javax.servlet.annotation.WebServlet;

//import sun.security.provider.JavaKeyStore.*;
import bean.*;
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static void main(String args[]) {

	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		String email = req.getParameter("email");
		String password =req.getParameter("password");
		String name = req.getParameter("name");
		String birth = req.getParameter("day")+req.getParameter("month")+req.getParameter("year");
		String address = req.getParameter("address");
		String NIC = req.getParameter("NIC");
		USER_property user = new USER_property();
		user.set_email(email);
		user.set_password(password);
		user.set_name(name);
		user.set_birthday(birth);
		user.set_address(address);
		user.set_NIC(NIC);

				Basic_properties insert = new Basic_properties();

				// 嚙緻嚙踝蕭
				try {
					boolean result = insert.insert_user(user);
					System.out.println("boolean email "+result);
					if (result=true) {
						out.println("successful");
						try {
							Thread.sleep(4000);
						} catch (InterruptedException e1) {
							// TODO 自動產生的 catch 區塊
							e1.printStackTrace();
						}
						res.sendRedirect("login.html");
					}
					else {
						
						out.println("try it again");
					
						res.sendRedirect("registration.html");
					}
					
				} catch (SQLException  e) {
					// TODO 自動產生的 catch 區塊
					e.printStackTrace();
				}

	
	
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
}
