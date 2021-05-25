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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs3220.model.VaccineClass;
//import cs3220.model.PatientViewModel;

@WebServlet("/NewVac")
public class NewVac extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NewVac() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//List<VaccineClass> vac = (List<VaccineClass>) getServletContext().getAttribute("vac");
		HttpSession session = request.getSession();

        if((boolean) session.getAttribute("NoA"))
            request.getRequestDispatcher("/WEB-INF/NewVac.jsp").forward(request, response);
        else
            response.sendRedirect("Error");
        
       
		//request.getRequestDispatcher("/WEB-INF/NewVac.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String NewName = request.getParameter("name");
		int required = Integer.parseInt(request.getParameter("doses"));
		int timeBW = Integer.parseInt(request.getParameter("time"));

		Connection connect = null;

		// inserting the added Vaccine to the database for it to be stored

		try {

			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu04";
			String username = "cs3220stu04";
			String password = "e9RS5YKhd4Kf";

			connect = DriverManager.getConnection(url, username, password);
			Statement stateTwo = connect.createStatement();
			stateTwo.executeUpdate("insert into vaccines (vaccine_name, dosesReq, betweenDoses, dosesRec, dosesLeft) "
					+ "values ('" + NewName + "', '" + required + "', '" + timeBW + "', '0', '0');");

			connect.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connect != null)
					connect.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		response.sendRedirect("ListVaccine");

	}

}
