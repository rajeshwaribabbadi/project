package com.space.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.space.bean.Userdetails;
import com.space.daoimpl.AdminDAOimpl;
import com.space.daoimpl.UserDAOimpl;

/**
 * Servlet implementation class Adminlogin
 */
public class Adminlogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Adminlogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		Userdetails user = new Userdetails();
		AdminDAOimpl adminDAO = new AdminDAOimpl();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		String type = adminDAO.login(user.getUsername(), user.getPassword());
		HttpSession session = request.getSession();
		session.setAttribute("un", user.getUsername());
		
		if(type.equalsIgnoreCase("Admin")){
			session.setAttribute("un", user.getUsername());
			//session.setMaxInactiveInterval(20);
			
			RequestDispatcher rd = request.getRequestDispatcher("./Admin.html");
			rd.forward(request, response);
		}else{
			out.println("Invalid UserName/ PassWord.Please try again....");
			RequestDispatcher rd = request.getRequestDispatcher("./Adimlogin.html");
			rd.include(request, response);
		}
	}

}
