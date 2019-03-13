package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Insert_comment
 */
@WebServlet("/Insert_comment")
public class Insert_comment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert_comment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		PrintWriter out = res.getWriter();
		String comment =req.getParameter("comment");
		String user = req.getParameter("user");
		String id = req.getParameter("id");
bean.Insert_comment insert_comment=new bean.Insert_comment();

				// 嚙緻嚙踝蕭
				try {
					int result = insert_comment.insert_comment(id, comment, user);
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
