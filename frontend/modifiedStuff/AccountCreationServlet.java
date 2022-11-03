import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountCreationServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("create_username");
		String password = request.getParameter("create_password");
		String confirmPassword = request.getParameter("confirm_password");
		request.setAttribute("username", username);
		
		Login log = new Login();
		String error = "";
		if (username.equals("")) {
			error = "Username is a required field.";
		} else if(username.length() < 3) {
			error = "Username must be at least 3 characters";
		} else if(password.equals("")) {
			error = "Password is a required field.";
		} else if(password.length() < 8) {
			error = "Password must be at least 8 characters.";
		} else if(confirmPassword.equals("")) {
			error = "Confirm Password is a required field.";
		} else if(!password.equals(confirmPassword)) {
			error = "Passwords do not match.";
		} else {
			if (log.addAccount(username, password) == true) {
				//new location to be redirected
				//location goes to "loggedIn.html" (a new web page)
				// (if we wanted to keep the .php file we'd do "name.php" instead)
				String site = new String("base.html");
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site); 			
			} else {
				PrintWriter out = response.getWriter();
				out.println("login incorrect");			
			}
		}
			
		if(!error.equals("")) {
			request.setAttribute("error", error);
			try {
			request.getRequestDispatcher("CreateAccount.jsp").forward(request, response);
			} catch(Exception e) {}
			request.setAttribute("error", error);
		}
	}

}