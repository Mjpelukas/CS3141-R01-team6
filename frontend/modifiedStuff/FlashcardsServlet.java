import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FlashcardsServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String username = (String) getServletContext().getAttribute("username");
		Database database = new Database();
		database.connect();
		ArrayList<String> setNames = database.viewFlashcardSets(username);
		
		request.setAttribute("setNames", setNames);
		try {
		request.getRequestDispatcher("displaySets.jsp").forward(request, response);
		} catch(Exception e) {}
		database.disconnect();
	}
}
	