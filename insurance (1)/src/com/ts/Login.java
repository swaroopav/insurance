package com.ts;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String uid=request.getParameter("uid");
		String pwd=request.getParameter("pwd");
		UserBean details=new InsuranceDAO().isUser(uid,pwd);
		out.print("pass");
		if(details.getRole()=="")
		{
			out.print("error");
		RequestDispatcher rd=request.getRequestDispatcher("Login.jsp");
		rd.include(request, response);
		}
		else
		{
			HttpSession session=request.getSession();
			session.setAttribute("Uname",details.getUsername());
			if(details.getRole().equals("user"))
			{
				response.sendRedirect("mainmenu_user.jsp");
			}
		
		else if(details.getRole().equals("admin"))
		{
			response.sendRedirect("mainmenu_admin.jsp");
			}
		}
		
	}

}
