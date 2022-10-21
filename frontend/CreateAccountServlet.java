import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateAccountServlet extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//new location to be redirected
		//location goes to "loggedIn.html" (a new web page)
		// (if we wanted to keep the .php file we'd do "name.php" instead)
		String site = new String("CreateAccount.html");
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", site);
	}

}
