import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetSetsServlet extends HttpServlet {
	
	
	// Returns a list of all the set names for a given user
	// Doesn't actually return that information but sets the context attribute "setnames"
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// Get the session username
		String username = (String) getServletContext().getAttribute("username");
		
		// Use that database to get the list of set names 
		Database database = new Database();
		database.connect();
		ArrayList<String> setNames = database.viewFlashcardSets(username);
		
		// Set the attribute so that the front end can access the set names 
		request.setAttribute("setNames", setNames);
		database.disconnect();
		
		try {
			request.getRequestDispatcher("SetsView.jsp").forward(request, response);
		} catch(Exception e) {}
		
				
		/**
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String table = "<table>\n"
				+ "<tr><th>Names</th><tr>"
				+ "<tbody>";
		for(String name : setNames) {
			table += "<tr><td>" + name + "</td></tr>";
		}
		table += "</tbody></table>";
		
		out.println("<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n" +
		         "<html>\n" +
		            "<head><title>Sets</title></head>\n" +
		            "<body>\n" +
		               "<h1 align = \"center\">Sets</h1>\n" +
		               "<ul>\n" +
		                  table +
		               "</ul>\n" +
		            "</body>" +
		         "</html>");**/
	}
	
	
	// This creates a new set for a specific user
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Gets the info from the submit form
		// TODO: Create isPublic paramater so we can implement the search feature
		String setName = request.getParameter("setName");
		String setDescription = request.getParameter("setDescription");
		
		// This was for testing
		// System.out.println(setName);
		
		// Gets the session username
		String username = (String) getServletContext().getAttribute("username");
		
		// Creates the flashcard
		Database database = new Database();
		database.connect();
		
		// TODO: Create error message for repeat set names, this is where it will be returned or thrown from
		database.createFlashcardSet(username, setName);
		getServletContext().setAttribute("setName", setName);
		
		
		/* 
		 * This might not be necessary. This basically outputs a success page with a back button.
		 * The reason we have this is so that the back button can do a get action to update the sets page.
		 * There's a probably a way to avoid this and just update the sets page and return there, but I couldn't figure it out.
		 */
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String html = "<link rel=\"stylesheet\" type=\"text/css\" href=\"Styling/styles.css\" />\r\n"
				+ "	<body class=\"body\">\r\n"
				+ "    	<div>"
				+ "<h1>Success!</h1>"
				+ "<form method=\"get\" action=\"sets\" class=\"buttonList\">\r\n"
		+ "        	<button name=\"getFlashcardSetsName\" type=\"submit\" value=\"getFlashcardSets\">Back</button>\r\n"
		+ "        </form>";
		
		out.println(html);
	
		
	}

}
