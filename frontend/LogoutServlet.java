import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutServlet extends HttpServlet {
	
	//this method is for html post stuff 
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// New location to be redirected
		String site = new String("base.html");
		response.setStatus(response.SC_MOVED_TEMPORARILY);
		response.setHeader("Location", site); 
	}
	
	//this is what get methods look like
	public void doGet() {
		
	}

}
