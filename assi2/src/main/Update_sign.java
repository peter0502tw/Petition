package main;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Update_sign_bean;

/**
 * Servlet implementation class Update_sign
 */
@WebServlet("/Update_sign")
public class Update_sign extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Update_sign() {
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
		String user = req.getParameter("user");
		String title = req.getParameter("title");
		try {
			if (title != null && title.length() != 0 && user != null && user.length() != 0) {
				if(Update_sign_bean.check_sign(user, title)==0)
					out.println(Update_sign_bean.set_sign(user, title));	
				else out.println(99);	
			} else {
				res.sendRedirect("notice.jsp?errorid=3");
			}

		} catch (Exception ex) {
			res.sendRedirect("notice.jsp?errorid=3");
		}

		out.close();

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
