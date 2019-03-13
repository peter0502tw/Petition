package main;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.*;

@WebServlet("/Logout")
public class Logout extends HttpServlet {
		public void doGet(HttpServletRequest req, 
				        HttpServletResponse res) 
		            throws ServletException, IOException {

				HttpSession se=req.getSession();
				se.removeAttribute("USER_property");
				res.sendRedirect("login.html");
		}
		public void doPost(HttpServletRequest req, 
					HttpServletResponse res) 
		            throws ServletException, IOException {
			doGet(req, res);}
	}


