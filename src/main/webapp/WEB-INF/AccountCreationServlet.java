import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountCreationServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = request.getParameter("create_username");
		String password = request.getParameter("create_password");
		
		request.setAttribute("username", username);
		
		Login log = new Login();
		
		if (log.addAccount(username, password) == true) {
			//new location to be redirected
			//location goes to "loggedIn.html" (a new web page)
			// (if we wanted to keep the .php file we'd do "name.php" instead)
			String site = new String("loggedIn.jsp");
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", site); 			
		} else {
			PrintWriter out = response.getWriter();
			out.println("login incorrect");			
		}
	}

}