

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeleteSetsServlet
 */
@WebServlet("/DeleteSetsServlet")
public class DeleteSetsServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Gets the info from the submit form
		// TODO: Create isPublic paramater so we can implement the search feature
		String setName = request.getParameter("setName");
		
		// This was for testing
		// System.out.println(setName);
		
		// Gets the session username
		String username = (String) getServletContext().getAttribute("username");
		
		// Creates the flashcard
		Database database = new Database();
		database.connect();
		
		// TODO: Create error message for repeat set names, this is where it will be returned or thrown from
		database.deleteFlashcard(username, setName);
		try {
		request.getRequestDispatcher("SetsView.jsp").forward(request, response);
		} catch(Exception e){}
	}
}
