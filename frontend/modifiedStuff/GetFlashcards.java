import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetFlashcards extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = (String) getServletContext().getAttribute("username");
		String setName = request.getParameter("set_name");
		Database database = new Database();
		database.connect();
		ArrayList<String[]> terms = database.viewFlashcardSet(setName, username);
		
		
		
		request.setAttribute("terms", terms);
		try {
		request.getRequestDispatcher("FlashcardView.jsp").forward(request, response);
		} catch(Exception e) {}
		database.disconnect();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
