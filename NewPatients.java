package cs3220.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;

import cs3220.model.PatientViewModel;
import cs3220.model.PatientsClass;
import cs3220.model.VaccineClass;

@WebServlet("/NewPatients")
public class NewPatients extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NewPatients() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/NewPatients.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<PatientViewModel> patList = new ArrayList<PatientViewModel>();
		List<VaccineClass> vac = new ArrayList<>();

		LocalDate localDate = LocalDate.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		String Fstring = localDate.format(format);

		String first = request.getParameter("fname");
		String last = request.getParameter("lname");
		String vacID = request.getParameter("vaccineType");

		int tempID = 1;
		Connection c = null;

		// inserting the added Vaccine to the database for it to be stored
		try {

			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu04";
			String username = "cs3220stu04";
			String password = "e9RS5YKhd4Kf";

			c = DriverManager.getConnection(url, username, password);
			Statement stateTwo = c.createStatement();
			ResultSet res2 = stateTwo.executeQuery("select * from vaccines");

			while (res2.next()) {
				vac.add(new VaccineClass(res2.getInt("id"), res2.getString("vaccine_name"), res2.getInt("dosesReq"),
						res2.getInt("betweenDoses"), res2.getInt("dosesRec"), res2.getInt("dosesLeft")));
			}
			stateTwo.close();

			// System.out.println(tempID);
			// System.out.println(vac.size());

			for (int i = 0; i < vac.size(); i++) {
				if (vac.get(i).getName().equals(vacID)) {
					tempID = vac.get(i).getId();
				}

			}

			Statement stmt = c.createStatement();
			stmt.executeUpdate("insert into patients (fname, lname, vaccine_id, firstDose) " + "values ('" + first
					+ "', '" + last + "', " + tempID + " , '" + localDate + "');");
			stmt.close();

			// System.out.println(vac.get(2).getName());

			/*
			 * int dosesDecrement = vac.get(tempID).getRemaining(); dosesDecrement--;
			 * vac.get(tempID).setRemaining(dosesDecrement);
			 */
			Statement newStmt = c.createStatement();
			newStmt.executeUpdate(" update vaccines set dosesLeft = " // + dosesDecrement
					+ " where vaccines.id = " + // tempID +
					";");
			newStmt.close();

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
		getServletContext().setAttribute("vac", vac);
		response.sendRedirect("PatientManagement");

	}

}
