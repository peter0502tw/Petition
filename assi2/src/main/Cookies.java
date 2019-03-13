package main;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Cookies
 */
@WebServlet("/Cookies")
public class Cookies extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cookies() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   
	    Cookie[] cookies = request.getCookies();
	    if(cookies != null) {
	      for(Cookie cookie : cookies) {
	        String name = cookie.getName();
	        String value = cookie.getValue();
	        if("user".equals(name) && "allen0818".equals(value)) {
	          request.setAttribute(name, value);
	          request.getRequestDispatcher("user.jsp").forward(request, response);
	          return;
	        }
	      }
	    }
	     
	    response.sendRedirect("login.html");
	  }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}



