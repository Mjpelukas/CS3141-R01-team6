import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchResultsFlashcard extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		// Gets the terms and definitions from the database in an array list of string arrays
		Database database = new Database();
		database.connect();
				
		String setName = request.getParameter("set_name"), 
				setOwner = request.getParameter("set_owner");
		ArrayList<String[]> terms = database.viewFlashcardSet(setName, setOwner);
		database.disconnect();
				
		// Sets the terms attribute to the arraylist of string arrays so that it can be accessed by teh front end
		request.setAttribute("terms", terms);
		
		try {
			// Sends to the next website. The request and response are forwarded so that the attributes stay
			// (I'm only pretty sure that's how that works)
			request.getRequestDispatcher("searchedFlashcardView.jsp").forward(request, response);
		} catch (Exception e) {}
	}
}
