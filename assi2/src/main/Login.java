package main;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import bean.USER_property;
import bean.UserVerification;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");

		String paswd = req.getParameter("password");
		String email = req.getParameter("email");
		try {
			if (email != null && email.length() != 0 && paswd != null && paswd.length() != 0) {
				USER_property u = UserVerification.check_login(email, paswd);
				HttpSession se=req.getSession();
				String host = req.getServerName();
				Cookie cookie=new Cookie("SESSION_LOGIN_USERNAME", email);
				cookie.setDomain(host);
				res.addCookie(cookie);
					se.setAttribute("USER_property",u);
					res.sendRedirect("user.jsp");
				
				}else{
					res.sendRedirect("notice.jsp?errorid=2");
				}
			

		} catch (Exception ex) {
			out.println("{\"message\":\"exception\"}");
		}

		out.close();
		
		
		String host = req.getServerName();
		Cookie cookie = new Cookie("SESSION_LOGIN_USERNAME", email); // 保存使用者名到Cookie
		cookie.setPath("/");
		cookie.setDomain(host);
		cookie.setMaxAge(99999999);
		res.addCookie(cookie);
		cookie = new Cookie("SESSION_LOGIN_PASSWORD", paswd);
		cookie.setPath("/");
		cookie.setDomain(host);
		cookie.setMaxAge(99999999);
		res.addCookie(cookie);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
