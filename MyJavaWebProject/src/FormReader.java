import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RegistrationDAO;

public class FormReader extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String age = request.getParameter("age");

		// Assume all text field values are valid
		boolean check = true;

		// VALIDATE the text fields
		if (!email.contains("@") || !email.contains(".")) {
			check = false;
		}
		if (phone.length() != 10) {
			check = false;
		}

		// If validation passed
		if (check == true) {

			// Convert the String age variable to an integer
			int ageValue = Integer.parseInt(age);

			// STORE THE DATA IN mySQL DATABASE
			RegistrationDAO reg = new RegistrationDAO();
			reg.insertData(fname + " " + lname, email, phone, ageValue);

			response.getWriter().print("<html><h1>Thanks for registering " + fname + " " + lname + "</h1></html>");
		} else {
			response.getWriter().print("<html><h1><font color=red>Sorry Invalid Field Values</font></h1></html>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
