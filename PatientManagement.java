package cs3220.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

import cs3220.model.PatientViewModel;
import cs3220.model.VaccineClass;

@WebServlet("/PatientManagement")
public class PatientManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PatientManagement() {
		super();
	}
	
	private VaccineClass getVaccine(String name) {
		List<VaccineClass> vac = (List<VaccineClass>) getServletContext().getAttribute("vac");
		for (VaccineClass vaccine : vac)
			if (vaccine.getName().equals(name))
				return vaccine;
		return null;
	}


	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		List<PatientViewModel> patList = new ArrayList<PatientViewModel>();
		List<VaccineClass> vac = new ArrayList<>();
		Connection c = null;

		try {

			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu04";
			String username = "cs3220stu04";
			String password = "e9RS5YKhd4Kf";

			c = DriverManager.getConnection(url, username, password);
			Statement stateOne = c.createStatement();
			ResultSet rs = stateOne.executeQuery("select * from patients");

			while (rs.next()) {
				patList.add(new PatientViewModel(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"),
						rs.getInt("vaccine_id"), rs.getDate("firstDose"), rs.getDate("secondDose")));

			}
			stateOne.close();

			Statement stateTwo = c.createStatement();
			ResultSet res2 = stateTwo.executeQuery("select * from vaccines");

			while (res2.next()) {
				vac.add(new VaccineClass(res2.getInt("id"), res2.getString("vaccine_name"), res2.getInt("dosesReq"),
						res2.getInt("betweenDoses"), res2.getInt("dosesRec"), res2.getInt("dosesLeft")));
			}

			for (int i = 0; i < patList.size(); i++) {
				int j = 0;
				while (j < vac.size()) {
					if (patList.get(i).getVaccineId() == vac.get(j).getId()) {
						patList.get(i).setVaccineName(vac.get(j).getName());
						patList.get(i).setVaccineDosesRequired(vac.get(j).getDoses());
						patList.get(i).setVaccineBetweenDoses(vac.get(j).getTime());
						patList.get(i).setVaccineDosesLeft(vac.get(j).getRemaining());
						patList.get(i).setVaccineDosesReceived(vac.get(j).getVaccinated());
					}
					j++;
				}
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

		getServletContext().setAttribute("vac", vac);
		getServletContext().setAttribute("patList", patList);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<PatientViewModel> patList = new ArrayList<PatientViewModel>();
		List<VaccineClass> vac = new ArrayList<>();
		Connection c = null;

		try {

			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu04";
			String username = "cs3220stu04";
			String password = "e9RS5YKhd4Kf";

			c = DriverManager.getConnection(url, username, password);
			Statement stateOne = c.createStatement();
			ResultSet rs = stateOne.executeQuery("select * from patients");

			while (rs.next()) {
				patList.add(new PatientViewModel(rs.getInt("id"), rs.getString("fname"), rs.getString("lname"),
						rs.getInt("vaccine_id"), rs.getDate("firstDose"), rs.getDate("secondDose")));

			}
			stateOne.close();

			Statement stateTwo = c.createStatement();
			ResultSet res2 = stateTwo.executeQuery("select * from vaccines");

			while (res2.next()) {
				vac.add(new VaccineClass(res2.getInt("id"), res2.getString("vaccine_name"), res2.getInt("dosesReq"),
						res2.getInt("betweenDoses"), res2.getInt("dosesRec"), res2.getInt("dosesLeft")));
			}

			for (int i = 0; i < patList.size(); i++) {
				int j = 0;
				while (j < vac.size()) {
					if (patList.get(i).getVaccineId() == vac.get(j).getId()) {
						patList.get(i).setVaccineName(vac.get(j).getName());
						patList.get(i).setVaccineDosesRequired(vac.get(j).getDoses());
						patList.get(i).setVaccineBetweenDoses(vac.get(j).getTime());
						patList.get(i).setVaccineDosesLeft(vac.get(j).getRemaining());
						patList.get(i).setVaccineDosesReceived(vac.get(j).getVaccinated());
					}
					j++;
				}
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

		getServletContext().setAttribute("vac", vac);
		getServletContext().setAttribute("patList", patList);

		request.getRequestDispatcher("/WEB-INF/ListPatients.jsp").forward(request, response);

}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	}

}
