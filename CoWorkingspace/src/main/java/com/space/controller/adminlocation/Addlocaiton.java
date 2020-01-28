package com.space.controller.adminlocation;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.space.bean.Location;
import com.space.controller.adminbranch.Deletebranch;
import com.space.daoimpl.AdminlocationDAOimpl;

/**
 * Servlet implementation class Addlocaiton
 */
public class Addlocaiton extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Addlocaiton() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    final static Logger logger=Logger.getLogger(Addlocaiton.class);
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			Location l = new Location();
			AdminlocationDAOimpl locationDAO = new AdminlocationDAOimpl();
			l.setLocationname(request.getParameter("locationname"));
			int n=locationDAO.addlocation(l);
			if(n!=0){
				out.println("Successfully One location Inserted.");
				logger.info("sucessfully one location inserted");
				RequestDispatcher rd = request.getRequestDispatcher("./adlocation.jsp");
				rd.include(request, response);
			}else{
				out.println("Record Insertion Failed.Please try again.");
				RequestDispatcher rd = request.getRequestDispatcher("./adlocation.jsp");
				rd.include(request, response);
			}
		}

}
