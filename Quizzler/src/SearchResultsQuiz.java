import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchResultsQuiz extends HttpServlet
{
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		Database database = new Database();
		database.connect();
				
		String quizName = request.getParameter("quiz_name");
		String quizOwner = request.getParameter("quiz_owner");

//		ArrayList<String[]> quizzes = database.viewQuizzes(quizName, quizOwner);
		database.disconnect();
//				
//		request.setAttribute("quizzes", quizzes);
	}
}