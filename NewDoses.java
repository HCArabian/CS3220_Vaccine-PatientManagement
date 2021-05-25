package cs3220.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs3220.model.VaccineClass;

@WebServlet("/NewDoses")
public class NewDoses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NewDoses() {
		super();

	}

	private VaccineClass getVaccine(String name) {
		List<VaccineClass> vac = (List<VaccineClass>) getServletContext().getAttribute("vac");
		for (VaccineClass vaccine : vac)
			if (vaccine.getName().equals(name))
				return vaccine;
		return null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();

        if((boolean) session.getAttribute("NoA"))
        	request.getRequestDispatcher("/WEB-INF/NewDoses.jsp").forward(request, response);
        else
            response.sendRedirect("Error");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		int newDoses = Integer.parseInt(request.getParameter("AddDose"));
		VaccineClass vaccine = getVaccine(name);

		int vaccinated = newDoses + vaccine.getVaccinated();
		int remaining = newDoses + vaccine.getRemaining();

		Connection c = null;

		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu04";
			String username = "cs3220stu04";
			String password = "e9RS5YKhd4Kf";

			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			stmt.executeUpdate(
					"update vaccines set dosesRec = '" + vaccinated + "' where vaccines.vaccine_name = '" + name + "'");

			Statement stmt2 = c.createStatement();

			stmt2.executeUpdate(
					"update vaccines set dosesLeft = '" + remaining + "' where vaccines.vaccine_name = '" + name + "'");

			stmt.close();
			
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

		response.sendRedirect("ListVaccine");
	}

}