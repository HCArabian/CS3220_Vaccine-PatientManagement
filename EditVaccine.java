package cs3220.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cs3220.model.VaccineClass;

@WebServlet("/EditVaccine")
public class EditVaccine extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditVaccine() {
		super();

	}

	private VaccineClass getVaccine(int id) {
		List<VaccineClass> vac = (List<VaccineClass>) getServletContext().getAttribute("vac");
		for (VaccineClass myVac : vac)
			if (myVac.getId() == id)
				return myVac;
			else {

			}

		return null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

        if((boolean) session.getAttribute("NoA"))
        {
        	String id = request.getParameter("id");
    		VaccineClass vac = getVaccine(Integer.parseInt(id));
    		request.setAttribute("vac", vac);

    		request.getRequestDispatcher("/WEB-INF/Edit.jsp").forward(request, response);
        }
        else
            response.sendRedirect("Error");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String name = request.getParameter("name");
		int req = Integer.parseInt(request.getParameter("doses"));
		int time = Integer.parseInt(request.getParameter("time"));

		Connection c = null;

		try {
			String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu04";
			String username = "cs3220stu04";
			String password = "e9RS5YKhd4Kf";

			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();
			stmt.executeUpdate("update vaccines set dosesReq = '" + req + "' where vaccines.vaccine_name = '" + name + "'");

			Statement stmt2 = c.createStatement();
			// ResultSet rs2 = stmt2.executeQuery("select * from vaccines");

			stmt2.executeUpdate("update vaccines set betweenDoses = '" + time + "' where vaccines.vaccine_name = '" + name + "'");
					
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