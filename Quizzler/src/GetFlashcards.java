import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetFlashcards extends HttpServlet { 	//url flashcards

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		//TODO: FIX reset error on cancel during flashcard creation. May just have to send user to sets
		//Going back in browser does successfully cancel tho....
	/*	if (request.getAttribute("createFlashcardCancel") != null) {
			String cancelCheck = "createCancel";
			if(cancelCheck.equals(request.getAttribute("createFlashcardCancel"))){
				try {
					getServletContext().setAttribute("flashcardRefresh", "REFRESH");
					request.getRequestDispatcher("FlashcardsView.jsp").forward(request, response);
					} catch (Exception e) {}
					return;
			}
		}*/
		
		// Get's username from session context
		String username = (String) getServletContext().getAttribute("username");
		
		if(request.getParameter("edit") != null) {
			String setName = (String) request.getParameter("edit");
			Database database = new Database();
			database.connect();

			ArrayList<String> setInfo = database.setInfo(setName, username);
			
			request.setAttribute("setName", setInfo.get(0));
			request.setAttribute("description", setInfo.get(1));
			//request.setAttribute("isPublic", setInfo.get(2));
			try {
			request.getRequestDispatcher("EditSet.jsp").forward(request, response);
			} catch(Exception e){}
			return;
			
		}
		
		if(request.getParameter("delete") != null) {
			String setName = (String) request.getParameter("delete");
			
			Database database = new Database();
			database.connect();

			database.deleteFlashcardSet(setName, username);
			database.disconnect();
			try {
			request.getRequestDispatcher("SetsView.jsp").forward(request, response);
			} catch(Exception e){}
			return;
			
		}
		String setName;
		String currentContext; //to hold the context
		//!!!!! helps do the resets
		if (getServletContext().getAttribute("flashcardRefresh") == null){
			currentContext = "first time eh?";
		}else { 
			currentContext = (String) getServletContext().getAttribute("flashcardRefresh");
		}
		if (currentContext.equals("REFRESH")){ 
			setName = (String) getServletContext().getAttribute("memberSet");
			// sets the current context to something other than "REFRESH"
			getServletContext().setAttribute("flashcardRefresh", "stale af");
		}else {
			// Gets the requested set name from the website submit parameter
			setName = request.getParameter("set_name");
			// Sets the session set name so that it can be accessed by other methods
			getServletContext().setAttribute("memberSet", setName);
		}
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
			request.getRequestDispatcher("FlashcardsView.jsp").forward(request, response);
		} catch (Exception e) {}
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * This method creates flashcards or is supposed, it is currently a little broken I believe
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// This gets the term, definition, setname, and username so that it has all the information needed to to an sql insert
		String term = request.getParameter("term");
		String definition = request.getParameter("definition");
		String memberSet = (String) getServletContext().getAttribute("memberSet");
		String username = (String) getServletContext().getAttribute("username");
		
		
		//Calls database.java method to create the flashcard with a mysql insert statement
		Database database = new Database();
		database.connect();
		database.createFlashcard(term, definition, memberSet, username);
		database.disconnect();
		try {
			getServletContext().setAttribute("flashcardRefresh", "REFRESH");
			doGet(request,response);
		} catch (Exception e) {}

		
	}

}
