import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetFlashcards extends HttpServlet {
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// Get's username from session context
		String username = (String) getServletContext().getAttribute("username");
		
		// Gets the requested set name from the website submit parameter
		String setName = request.getParameter("set_name");

		// Sets the session set name so that it can be accessed by other methods
		getServletContext().setAttribute("memberSet", setName);
		
		// Testing print statement 
		//System.out.println("get setName: " + setName);
		
		// Gets the terms and definitions from the database in an array list of string arrays
		Database database = new Database();
		database.connect();
		ArrayList<String[]> terms = database.viewFlashcardSet(setName, username);
		database.disconnect();
		
		// Sets the terms attribute to the arraylist of string arrays so that it can be accessed by teh front end
		request.setAttribute("terms", terms);
		try {
			// Sends to the next website. The request and response are forwarded so that the attributes stay
			// (I'm only pretty sure that's how that works)
			request.getRequestDispatcher("FlashcardView.jsp").forward(request, response);
		} catch (Exception e) {}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * This method creates flashcards or is supposed, it is currently a little broken I believe
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//doGet(request, response);
		//String setName = request.getParameter("set_name");
		
		// This gets the term, definition, setname, and username so that it has all the information needed to to an sql insert
		String term = request.getParameter("term");
		String definition = request.getParameter("definition");
		String memberSet = (String) getServletContext().getAttribute("memberSet");
		String username = (String) getServletContext().getAttribute("username");
		
		//Testing
		//System.out.println("setName: " + memberSet);
		//System.out.println(term);
		
		//Calls database.java method to create the flashcard with a mysql insert statement
		Database database = new Database();
		database.connect();
		database.createFlashcard(term, definition, memberSet, username);
		
		/* 
		 * This might not be necessary. This basically outputs a success page with a back button.
		 * The reason we have this is so that the back button can do a get action to update the flashcards page.
		 * There's a probably a way to avoid this and just update the flashcards page and return there, but I couldn't figure it out.
		 */
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		String html = "<link rel=\"stylesheet\" type=\"text/css\" href=\"Styling/styles.css\" />\r\n"
				+ "	<body class=\"body\">\r\n"
				+ "    	<div>"
				+ "<h1>Success!</h1>"
				+ "<form method=\"get\" action=\"flashcards\" class=\"buttonList\">\r\n"
		+ "        	<button name=\"getFlashcardName\" type=\"submit\" value=\"getFlashcard\">Back</button>\r\n"
		+ "        </form>";
		
		out.println(html);
	}

}
