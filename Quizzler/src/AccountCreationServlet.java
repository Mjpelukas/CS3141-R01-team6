import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// This class predictably creates accounts
public class AccountCreationServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// Gets the form parameters
		String username = request.getParameter("create_username");
		String password = request.getParameter("create_password");
		String confirmPassword = request.getParameter("confirm_password");
		request.setAttribute("username", username);
		
		Login log = new Login();
		
		// These are all the error messages for incorrectly inputted information
		// TODO: An error message for when the username field is already taken, this will require
		// code all the way through login.java down to database.java
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
				String site = new String("base.jsp");
				response.setStatus(response.SC_MOVED_TEMPORARILY);
				response.setHeader("Location", site); 			
			} else {
				PrintWriter out = response.getWriter();
				out.println("login incorrect");			
			}
		}
		
		// If there was an error, the error is communicated to the front-end through the attributes
		if(!error.equals("")) {
			request.setAttribute("error", error);
			try {
			request.getRequestDispatcher("CreateAccount.jsp").forward(request, response);
			} catch(Exception e) {}
			request.setAttribute("error", error);
		}
	}

}
