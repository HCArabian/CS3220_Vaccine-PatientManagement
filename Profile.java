package cs3220.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs3220.model.AdminClass;
import cs3220.model.NurseClass;
import cs3220.model.VaccineClass;

@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Profile() {
		super();

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	      if(request.getSession().getAttribute("user")==null)
				request.getRequestDispatcher("/WEB-INF/Profile.jsp").forward(request, response);
	    	  
			else {
				response.sendRedirect("FrontPage");
			}
				
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		String first = request.getParameter("fname");
		String last = request.getParameter("lname");
		
		
		Connection c = null;
		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu04";
			String username = "cs3220stu04";
			String password = "e9RS5YKhd4Kf";

			c = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = c.prepareStatement("update nurse set fname = " + first + ", lname = " + last + ", password = " + pass + " where id = " +  id + " ;");
			
			pstmt.setString(1, first);
			pstmt.setString(1, last);
			pstmt.setString(1, pass);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			
			
			
		} catch (SQLException e) {
			throw new ServletException(e);
		} finally {
			if (c != null)
				try {
					c.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		
		
	}
}