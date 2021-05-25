package cs3220.servlet;

import java.io.IOException;
import java.util.List;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs3220.model.PatientViewModel;
import cs3220.model.VaccineClass;

@WebServlet("/SecondDose")
public class SecondDose extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SecondDose() {
		super();
	}

	private PatientViewModel getListPatients(int id) {
		List<PatientViewModel> patList = (List<PatientViewModel>) getServletContext().getAttribute("patList");
		for (PatientViewModel newpat : patList)
			if (newpat.getId() == id)
				return newpat;
			else {

			}

		return null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		PatientViewModel patList = getListPatients(Integer.parseInt(id));

		LocalDate dates = LocalDate.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String formatString = dates.format(format);
		// patList.setSecondDoseDate(formatString);

		Connection c = null;

		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu04";
			String username = "cs3220stu04";
			String password = "e9RS5YKhd4Kf";

			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			stmt.executeUpdate("update patients set secondDose = '" + dates + "' where patients.id = '" + id + "'");

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

		request.setAttribute("patList", patList);
		response.sendRedirect("PatientManagement");
		// request.getRequestDispatcher("/WEB-INF/ListPatients.jsp").forward(request,
		// response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// response.sendRedirect("PatientManagement");
	}

}
