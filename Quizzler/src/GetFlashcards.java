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
		String username = (String) getServletContext().getAttribute("username");
		String setName = request.getParameter("set_name");

		getServletContext().setAttribute("memberSet", setName);
		System.out.println("get setName: " + setName);
		Database database = new Database();
		database.connect();
		ArrayList<String[]> terms = database.viewFlashcardSet(setName, username);
		
		request.setAttribute("terms", terms);
		try {
			request.getRequestDispatcher("FlashcardView.jsp").forward(request, response);
		} catch (Exception e) {
		}
		database.disconnect();
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//doGet(request, response);
		//String setName = request.getParameter("set_name");
		String term = request.getParameter("term");
		String definition = request.getParameter("definition");
		String memberSet = (String) getServletContext().getAttribute("memberSet");
		System.out.println("setName: " + memberSet);
		String username = (String) getServletContext().getAttribute("username");
		
		System.out.println(term);
		
		Database database = new Database();
		database.connect();
		database.createFlashcard(term, definition, memberSet, username);
		
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		
		String html = "<link rel=\"stylesheet\" type=\"text/css\" href=\"styles.css\" />\r\n"
				+ "	<body class=\"body\">\r\n"
				+ "    	<div>"
				+ "<h1>Success!</h1>"
				+ "<form method=\"get\" action=\"flashcards\" class=\"buttonList\">\r\n"
		+ "        	<button name=\"getFlashcardName\" type=\"submit\" value=\"getFlashcard\">Back</button>\r\n"
		+ "        </form>";
		
		out.println(html);
	}

}
