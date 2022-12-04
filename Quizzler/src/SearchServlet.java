import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// A Java servlet class requires both doPost() and doGet() methods.
public class SearchServlet extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{
		// Create a new instance of Database.
		Database database = new Database();
		
		// Extract the flashcard set name from the search if provided.
		if(request.getParameter("setName") != null) {
			String setName = (String)request.getParameter("setName");
			
			// Connect to the database.
			database.connect();

			// Search the flashcard set provided the flashcard set name.
			ArrayList<String[]> resultSet = database.searchFlashcardSet(setName);
			
			request.setAttribute("terms", resultSet);

			// Attempt forwarding the request to SetSearchResults.jsp.
			try{
				request.getRequestDispatcher("SetSearchResults.jsp").forward(request, response);} 
			catch(Exception exception){}
			return;
		}
		
		// Extract the quiz name from the search if provided.
		if(request.getParameter("quizName") != null) {
			String quizName = (String) request.getParameter("quizName");
			
			// Connect to the database.
			database.connect();

			// Search the quiz provided the quiz name.
			ArrayList<String[]> resultSet = database.searchQuiz(quizName);
			
			request.setAttribute("questions", resultSet);

			// Attempt forwarding the request to QuizSearchResults.jsp.
			try{
				request.getRequestDispatcher("QuizSearchResults.jsp").forward(request, response);} 
			catch(Exception exception) {}
			return;
		}
		
		// Disconnect from the database.
		database.disconnect();
			
		// Attempt to forward the HTTP request.
		try {request.getRequestDispatcher("Search.jsp").forward(request, response);} 
		catch(Exception exception) {}			
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException 
	{}
}