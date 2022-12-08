import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuizzesServlet extends HttpServlet {
	
	// Returns a list of all the set names for a given user
	// Doesn't actually return that information but sets the context attribute "setnames"
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Get the session username
		String username = (String) getServletContext().getAttribute("username");
		
		if(request.getParameter("delete") != null) {
			String setName = (String) request.getParameter("delete");
			
			Database database = new Database();
			database.connect();

			database.deleteQuiz(setName, username);
			
			try {
				request.getRequestDispatcher("QuizzesView.jsp").forward(request, response);
			} catch(Exception e){
			}
			
			return;
		}
		
		// Use that database to get the list of set names 
		Database database = new Database();
		database.connect();
		ArrayList<String> quizNames = database.viewQuizzes(username);
		
		// Set the attribute so that the front end can access the set names 
		request.setAttribute("quizNames", quizNames);
		database.disconnect();
		
		try {
			request.getRequestDispatcher("QuizzesView.jsp").forward(request, response);
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
		//all three are always present
		String creationType = request.getParameter("creationType");
		String quizName = request.getParameter("quizName");
		// Gets the session username
		String username = (String) getServletContext().getAttribute("username");
		
		//String setDescription = request.getParameter("quizDescription");
		String isPublic = request.getParameter("isPublic") == null ? "false" : "true";
		
<<<<<<< HEAD
		// Creates the quiz
=======
		// Creates the flashcard
>>>>>>> branch 'main' of https://github.com/Mjpelukas/CS3141-R01-team6.git
		Database database = new Database();
		database.connect();
		
		// TODO: Create error message for repeat set names, this is where it will be returned or thrown from
		database.createQuiz(username, quizName, isPublic);
		getServletContext().setAttribute("quizName", quizName);
		
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
				+ "<form method=\"get\" action=\"quizzes\" class=\"buttonList\">\r\n"
		+ "        	<button name=\"getQuizzes\" type=\"submit\" value=\"getQuizzes\">Back</button>\r\n"
		+ "        </form>";
		
		out.println(html);
	}

}
