package main;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserVerification;

/**
 * Servlet implementation class Check_NIC
 */
@WebServlet("/Check_NIC")
public class Check_NIC extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		String NIC=req.getParameter("NIC");
		String email=req.getParameter("email");
	//	System.out.print("§ì¨ìªº"+NIC);
		try{
			if(NIC!=null && NIC.length()!=0){
				UserVerification userVerification=new UserVerification();
				int check=userVerification.check_NIC(NIC);
				out.println(check);
				if(check==0){
					
					out.println(0);
				}else if(check==1){
					
					out.println(1);
				}
				else if(check==2){
					out.println(2);
				}
			}
			if(email!=null &&email.length()!=0) {
				UserVerification userVerification=new UserVerification();
				int check=userVerification.check_email(email);
				if(check==1) {
					out.println(1);
				}else {
					out.println(0);
				}
			}

		}catch(Exception ex){
			out.println("{\"message\":\"exception\"}");	
		}
		
		
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest req, HttpServletResponse res)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	
	}

	  
	
	
}
//{\"NIC.check_NIC\":0} 
/*successmsg.hide();
failmsg.hide();

if(ret.existing_user==0){
	 $("#success").html("invalid NIC");
	 failmsg.show();
     	 $("#submit").prop('disabled', true);
}
else if(ret.existing_user==1){
	 $("#success").html("is in used");
	 failmsg.show();
     	 $("#submit").prop('disabled', true);
}

else if(ret.existing_user==2){
	 $("#success").html("can be used");
	 successmsg.show();
	 $("#submit").prop('disabled', false);
}

*/
