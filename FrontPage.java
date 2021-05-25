package cs3220.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

@WebServlet("/FrontPage")
public class FrontPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontPage() {
		super();

	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		List<AdminClass> admin = new ArrayList<AdminClass>();
		List<NurseClass> nurse = new ArrayList<NurseClass>();
		Connection c = null;
		try {

			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu04";
			String username = "cs3220stu04";
			String password = "e9RS5YKhd4Kf";

			c = DriverManager.getConnection(url, username, password);
			Statement stateOne = c.createStatement();
			ResultSet rs = stateOne.executeQuery("select * from nurse");

			while (rs.next()) {
				nurse.add(new NurseClass(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"),
						rs.getString("username"), rs.getString("password")));

			}
			stateOne.close();

			Statement stateTwo = c.createStatement();
			ResultSet res2 = stateTwo.executeQuery("select * from admin");

			while (res2.next()) {
				admin.add(new AdminClass(res2.getInt("id"), res2.getString("fname"), res2.getString("lname"),
						res2.getString("username"), res2.getString("password")));
			}

			stateTwo.close();

			c.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		getServletContext().setAttribute("admin", admin);
		getServletContext().setAttribute("nurse", nurse);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		List<NurseClass> nurse = (List<NurseClass>) getServletContext().getAttribute("nurse");
		List<AdminClass> admin = (List<AdminClass>) getServletContext().getAttribute("admin");

		String user = request.getParameter("user");
		String pass = request.getParameter("pass");

		for (int i = 0; i < nurse.size(); i++) {
			for (int j = 0; j < admin.size(); j++) {

				if (nurse.get(i).getUsername().equals(user) && nurse.get(i).getPassword().equals(pass)) 
				{
					session.setAttribute("NoA", nurse.get(i).isNurse());
					response.sendRedirect("Nurse");
				} 
				else if (admin.get(j).getUsername().equals(user) && admin.get(j).getPassword().equals(pass)) 
				{
					session.setAttribute("NoA", admin.get(j).isAdmin());
					response.sendRedirect("Admin");
				} 
				else 
				{
					response.sendRedirect("FrontPage");
				}
			}
		}
	}
}
