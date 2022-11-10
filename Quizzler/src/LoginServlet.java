import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("login_username");
		String password = request.getParameter("login_password");
		
		
		
		//this is a temporary test, assuming username is "Maxim" and password is "1234"
		//long term, this will call a separate that is in charge of checking if the username and password
		//are in the database, and returning the appropriate information.
		//honestly could also call a .php file for getting the mysql database stuff
		//if (username.equals("Maxim") && password.equals("1234")) {
		
		Login log = new Login();
		
		if (log.loginTest(username, password)) {
			//Sets the global "username" attribute to who's being logged in
			getServletContext().setAttribute("username", username);
			
			//new location to be redirected
			//location goes to "loggedIn.html" (a new web page)
			// (if we wanted to keep the .php file we'd do "name.php" instead)
			//String site = new String("loggedIn.jsp");
			String site = new String("loggedIn.html");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site); 
		} else {
			String site = new String("base.jsp");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site);
			PrintWriter out = response.getWriter();
			out.println("login incorrect");
		}
	}
	
	public void doGet() {
		
	}

}
