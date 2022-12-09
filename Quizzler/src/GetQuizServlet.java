import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetQuizServlet extends HttpServlet {
	//    ."/QuizQuestions"
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String quizID = request.getParameter("quizID");
		request.setAttribute("QID", quizID);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		String quizName = request.getParameter("quizNameText");
		String username = (String) getServletContext().getAttribute("username");
		String courseText = request.getParameter("courseText");
		
		/*
		//if (!request.getParameter("visibility").equals(null)) {
		if (request.getParameter("visibility") != null) {
			Database database = new Database();
			database.connect();
			
			String publicChoice = request.getParameter("visibility");
			
			String temporary = "false";
			//database.createQuiz(quizName, username, courseText, isPublic);
			//database.createQuiz(quizName, username, courseText, temporary);
			//database.createQuiz(username, quizName, isPublic);
			database.createQuiz(username, quizName, publicChoice);	
		}
		*/
		
		Database database = new Database();
		database.connect();
		
		//what happens?
		
		//connect to CreateQuizQuestion.jsp
		
		database.disconnect();
	}

}
